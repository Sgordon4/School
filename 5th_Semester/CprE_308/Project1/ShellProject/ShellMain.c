#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>

#define DEBUG 1
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


/** Given a string, parse it into a command and its options/arguments, then store
    those parts in their respective buffers.

@param buffer   The string to be split
@param cmd      The buffer to store the parsed command
@param params   The array of buffers to store the command's options/arguments
@param paramCount   An int to store the final size of 'params' in
@return         0 on success, -1 on failure (currently no failure)
*/
int parseCmd(char *buffer, char *cmd, char **params, int *paramCount){
    char delim[] = " ";             //Use a space as our delimiter

    //Split the line into keys...
    char *ptr = strtok(buffer, delim);
    strcpy(cmd, ptr);               //The first item is the command
    debug_print("Cmd: %s\n", cmd);

    int i = 0;
	while(ptr != NULL){             //While end of buffer has not been reached...
		ptr = strtok(NULL, delim);  //Grab the next item
        params[i] = ptr;
        debug_print("Params at %d: %s\n", i, params[i]);
        i++;
	}
    *paramCount = i - 1;

    return 0;
}



/** Parses given array of values into options and arguments

@param argc     Count of the number of arguments contained in argv
@param argv     Values of the arguments
@return         0 for success, -1 for error
*/
int parseArgs(int argc, char **argv){

    if(DEBUG){
        int i;
        for(i = 0; i < argc; i++){
            debug_print("Arg at %d: ", i);
            debug_print("%s\n", argv[i]);
        }
    }

    char allOptions[] = "p:c";       //Every available option
                                    // ":x" for no args required

    int option;

    //I'm using getopt for command parsing because its just really really
    //really really really nice. Please God let this be allowed.
    while((option = getopt(argc, argv, allOptions)) != -1){

        debug_print("Option: %c\n", option);
        switch(option)
        {
            case 'p':
                strcpy(prompt, optarg); //Replace prompt with provided one
                break;
            case 'c': printf("mmmmmmm\n");
            break;
            //-------------------------------------------------------------
            case '?':
                /* getopt() automatically prints errors for us :D
                    Gists of what it prints:

                printf("Option -%c requires an argument.\n", optopt);
                printf("Unknown option '-%c'.\n", optopt);
                printf("Unknown option character '\\x%x'.\n", optopt);
                */
                //Continue on to default's return...

            default: return -1;
                    break;
            //-------------------------------------------------------------
        }

        // optind is for the extra arguments
        // which are not parsed
        int index;
        for (index = optind; index < argc; index++)
            printf ("Non-option argument %s\n", argv[index]);

    }

    return 0;
}





/** Final State Machine used to split strings by spaces and quotations marks.
    Modified but initially from https://stackoverflow.com/a/26913698/11723253

@param buffer       The string to be split
@param argv         Buffer to store values split from the string
@param argv_size    Size of the buffer to store values
@param num_args     Total number of values split
@return             0 on success, -1 on failure
*/
size_t splitString(char *buffer, char *argv[], size_t argv_size, size_t *num_args)
{
    char *p, *start_of_word;
    int c;
    enum states { DULL, IN_WORD, IN_STRING } state = DULL;
    size_t argc = 0;

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




int main(int argc, char** argv) {
    char buffer[1024];

    char *command[100];         //ls -l, cd /something, exit, etc...
    size_t commandSize = 0;     //ls -l => 2, exit => 1, etc...


    strcpy(processName, argv[0]);    //Grab the name of this shell
    if(parseArgs(argc, argv))        //If returned -1
        abort();

    //clrScreen();

    while(1){

        promptUsr(prompt);
        readLine(buffer);

        splitString(buffer, command, sizeof(command), &commandSize);

        printf("return: %d\n", parseArgs(commandSize, command));


    }
    return 0;
}
