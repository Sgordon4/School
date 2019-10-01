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

    *num_args = argc;
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
        for(i = 0; i < argc; i++){
            debug_print("Arg at %d: ", i);
            debug_print("%s\n", argv[i]);
        }
    }


    int i;
    for(i = start; i < argc; i++){
        //Grab the command (exit, ls, cd, etc...)
        char *command = argv[i];
        debug_print("Command: %s\n", argv[i]);


        if (strcmp(command, "exit") == 0){
            printf("Exiting shell...\n");
            exit(0);
        }
        else if (strcmp(command, "pid") == 0){
            printf("PID: %d\n", getpid());
        }
        else if (strcmp(command, "ppid") == 0){
            printf("PPID: %d\n", getppid());
        }
        else if (strcmp(command, "cd") == 0){
            if((argc-i) < 2){
                chdir(getenv("HOME"));
                return 0;
            }
            chdir(argv[++i]);
            return 0;
        }
        else if (strcmp(command, "pwd") == 0){
            char buffer[100];
            printf("%s\n", getcwd(buffer, sizeof(buffer)));
        }
        else if (strcmp(command, "-p") == 0){
            if((argc-i) < 2){
                printf("Option -p requires an argument!\n");
                return -1;
            }
            strcpy(prompt, argv[++i]);
            debug_print("Changed prompt: %s\n", argv[i]);
        }
        else{

            pid_t  child;
            int    cstatus;  /* Exit status of child. */
            pid_t  c;        /* Pid of child to be returned by wait. */
            char *args[3];   /* List of arguments for the child process. */

            /* Set up arguments to run an exec in the child process.  */
            /* (This example runs the "ls" program with "-l" option.) */
            args[0] = "ls";
            args[1] = "-l";
            args[2] = NULL;   /* Indicates the end of arguments. */
            if ((child = fork()) == 0) { /* Child process. */
                printf("Child: PID of Child = %ld\n", (long) getpid());
                execvp(args[0], args); /* arg[0] has the command name. */


                /* If the child process reaches this point, then  */
                /* execvp must have failed.                       */

                fprintf(stderr, "Child process could not do execvp.\n");
                exit(1);
            }
            else { /* Parent process. */
                if (child == (pid_t)(-1)) {
                    fprintf(stderr, "Fork failed.\n");
                    exit(1);
                }
                else {
                    c = wait(&cstatus); /* Wait for child to complete. */
                    printf("Parent: Child  %ld exited with status = %d\n",(long) c, cstatus);
                }
            }
            return  0;


            /*
            //Try to start a program command
            pid_t child_pid;
            int child_stat;
            pid_t tpid;

            child_pid = fork();
            if(child_pid){
                if(child_pid == (pid_t)(-1)){
                    printf("Fork failed.");
                    return -1;
                }

                printf(">>>[%ld] %s\n", (long)child_pid, command);
                tpid = wait(&child_stat);
                printf(">>>[%ld] %s Exit %d\n", (long)tpid, command, child_stat);

                if(child_stat == 0)
                    continue;
            }
            else{
                //char *execargs[] = {"ls", "-l", NULL};
                //execvp(execargs[0], execargs);

                printArr(argv+i, i, argc-i);
                printf("\n");

                //Send execvp the remaining arguments
                execvp((argv+i)[0], argv+i);
                //If execvp returns, command was not recognized
            }

            printf("Unknown command %s\n", command);
            return -1;
            */
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

    //clrScreen();

    while(1){

        promptUsr(prompt);
        readLine(buffer);

        splitString(buffer, command, sizeof(command), &commandSize);

        parseArgs(commandSize, command, 0);


    }
    return 0;
}
