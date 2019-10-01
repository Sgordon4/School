#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>

#define DEBUG 0
#define debug_print(fmt, ...) \
            do { if (DEBUG) fprintf(stderr, fmt, __VA_ARGS__); } while (0)


#define BUFFER_SIZE 1024

char processName[100];
char prompt[] = "308sh> ";



void clrScreen(){
    printf("\e[1;1H\e[2J");
}

void promptUsr(char *prompt){
    printf("%s", prompt);
}


/** Reads input from the user and stores it in the given buffer.

@param buffer   The buffer in which to store the read data
@return         0 for success, -1 for failure
*/
int readLine(char *buffer){
    if(fgets(buffer, BUFFER_SIZE, stdin) != NULL){
        buffer[strcspn(buffer, "\n")] = 0;  //Remove newline

        debug_print("Read string: %s\n", buffer);
        return 0;
    }

    printf("There was an error reading user input!\n");
    return -1;
}


/** Final State Machine used to split strings by spaces and quotations marks.
    Modified but initially from https://stackoverflow.com/a/26913698/11723253

@param buffer       The string to be split
@param argv         Buffer to store values split from the string
@param argv_size    Size of the buffer to store values
@param num_args     Total number of values split
@return             0 on success, -1 on failure
*/
size_t splitString(char *buffer, char *argv[], size_t argv_size, int *num_args)
{
    char *p, *start_of_word;
    int c;
    enum states { DULL, IN_WORD, IN_STRING } state = DULL;
    int argc = 0;

    for (p = buffer; argc < argv_size && *p != '\0'; p++) {
        c = (unsigned char) *p;
        switch (state) {
        case DULL:
            if (isspace(c)) {
                continue;
            }

            if (c == '"') {
                state = IN_STRING;
                start_of_word = p + 1;
                continue;
            }
            state = IN_WORD;
            start_of_word = p;
            continue;

        case IN_STRING:
            if (c == '"') {
                *p = 0;
                argv[argc++] = start_of_word;
                state = DULL;
            }
            continue;

        case IN_WORD:
            if (isspace(c)) {
                *p = 0;
                argv[argc++] = start_of_word;
                state = DULL;
            }
            continue;
        }
    }

    if (state != DULL && argc < argv_size)
        argv[argc++] = start_of_word;


    argv[argc] = NULL;  //Smack a null pointer on the end
    *num_args = argc;   //Update our personal array-size pointer

    return 0;
}




void printArr(char **argv, int start, int argc){
    int i;
    for(i = start; i < argc; i++){
        printf("%s ", argv[i]);
    }
}



/** Parses given array of values into options and arguments

@param argc     Count of the number of arguments contained in argv
@param argv     Values of the arguments
@param start    Index to start parsing from
@return         0 for success, -1 for error
*/
int parseArgs(int argc, char **argv, int start){

    if(DEBUG){
        debug_print("Size: %d\n",  argc);

        int i;
        for(i = 0; i <= argc; i++){
            debug_print("Arg at %d: ", i);
            debug_print("%s\n", argv[i]);
        }
    }


    int i;
    for(i = start; i < argc; i++){
        //Grab the command (exit, ls, cd, etc...)
        char *command = argv[i];
        debug_print("Command: %s\n", argv[i]);


        if (strcmp(command, "exit") == 0){//------------------------------------
            printf("Exiting shell...\n");
            exit(0);
        }
        if (strcmp(command, "clear") == 0){//------------------------------------
            clrScreen();
        }
        else if (strcmp(command, "pid") == 0){//--------------------------------
            printf("PID: %d\n", getpid());
        }
        else if (strcmp(command, "ppid") == 0){//-------------------------------
            printf("PPID: %d\n", getppid());
        }
        else if (strcmp(command, "cd") == 0){//---------------------------------
            if((argc-i) < 2){
                chdir(getenv("HOME"));
                return 0;
            }
            chdir(argv[++i]);
            return 0;
        }
        else if (strcmp(command, "pwd") == 0){//--------------------------------
            char buffer[100];
            printf("%s\n", getcwd(buffer, sizeof(buffer)));
        }
        else if (strcmp(command, "-p") == 0){//---------------------------------
            if((argc-i) < 2){
                printf("Option -p requires an argument!\n");
                return -1;
            }
            strcpy(prompt, argv[++i]);
            debug_print("Changed prompt: %s\n", argv[i]);
        }
        else{//-----------------------------------------------------------------


            //Fork observer to keep track of child process, allowing main to be
            //unblocked in case of background process

            //Check to see if a background process is requested, eg
            //If the last item in argv == "&"
            int background = 0;

            if(strcmp(argv[argc-1], "&") == 0){
                argv[argc-1] = NULL;
                background = 1;
            }



            pid_t   observer;   //PID of observer
            int     obs_status; //Exit status of observer
            pid_t   tobs;       //PID of observer to be returned by wait()

            //Launch observer
            observer = fork();

            //Observer ---------------------------------------------------------
            if(observer == 0){
                if(DEBUG) printf("Launching observer\n");

                pid_t   worker;     //PID of worker
                int     wrk_status; //Exit status of worker
                pid_t   twrk;       //PID of worker to be returned by wait()


                //Launch worker
                worker = fork();

                //Worker -------------------------------------------------------
                if(worker == 0){
                    printf(">>>[%ld] %s\n", (long)getpid(), command);
                    execvp(command, (argv+i));

                    //execvp() does not return, must have failed
                    fprintf(stderr, "Cannot exec %s: No such file or directory\n", command);
                    kill(getpid(), SIGTERM);    //Destroy worker
                }
                else{   //Observer
                    if (worker == (pid_t)(-1)) {
                        fprintf(stderr, "Worker fork failed.\n");
                        return -1;
                    }
                    twrk = wait(&wrk_status);   //Wait for worker to complete
                    printf(">>>[%ld] %s Exit %d\n", (long)twrk, command, wrk_status);
                    kill(getpid(), SIGTERM);    //Destroy observer
                }//-------------------------------------------------------------

            }
            else{   //Main
                if (observer == (pid_t)(-1)) {
                    fprintf(stderr, "Observer fork failed.\n");
                    return -1;
                }
                //If this is not a background process, wait for observer
                if(!background)
                    //tobs = wait(&obs_status);   //Wait for worker to complete
                    wait(&obs_status);

            }//-----------------------------------------------------------------

            //For now, so we don't have to parse strings,
            //just assume that was the last command and return
            return 0;
        }
    }


    return 0;
}










int main(int argc, char** argv) {
    char buffer[1024];

    char *command[100];         //ls -l, cd /something, exit, etc...
    int commandSize = 0;        //ls -l => 2, exit => 1, etc...

    strcpy(processName, argv[0]+2);    //Grab the name of this shell, trim off the './'

    if(parseArgs(argc, argv, 1))        //If returned -1
        abort();

    clrScreen();

    while(1){

        promptUsr(prompt);
        readLine(buffer);

        splitString(buffer, command, sizeof(command), &commandSize);

        parseArgs(commandSize, command, 0);


    }
    return 0;
}
