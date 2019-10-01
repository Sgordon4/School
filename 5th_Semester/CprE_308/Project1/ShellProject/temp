#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFFER_SIZE 1024

static void promptUsr(){
    printf("Prompting user...\n");
}

static void readUsr(char *buffer){
    printf("Reading user...\n");


    if(fgets(buffer, BUFFER_SIZE, stdin) != NULL){
        //Remove newline
        buffer[strcspn(buffer, "\n")] = 0;
        printf("The string is %s", buffer);
    }

    else
        printf("There was an error reading input!\n");
    //printf("%d", sizeof(buffer));

    /*
    //while(1){
        if(fgets(buffer, BUFFER_SIZE, stdin) != NULL){
            //Remove newline if present




            //buffer = strchr(buffer, '\n');   //Remove trailing newline
            printf("%s", buffer);
            printf("..\n");
        }
    //}
    */
}

/*
static int readLine(char *buffer)
{
    while (fgets(buffer, sizeof(buffer), stdin) != 0)
    {
        size_t buf_len = strlen(buffer);
        char *extra = realloc(input, buf_len + cur_len + 1);
        if (extra == 0)
            break;
        input = extra;
        strcpy(input + cur_len, buffer);
        cur_len += buf_len;
    }


    return 0;
}
*/


int main(int argc, char** argv) {
    //Grab passed parameters
    char processName[30];
    strcpy(processName, argv[0]);
    char prompt[16] = "308sh> ";
    //Shell only supports "'-p prompt'"
    if(argc == 3){

    }



    char buff[BUFFER_SIZE];

    int i;
    for(i=0; i<argc; i++)
        printf("Option %d is \"%s\"\n", i, argv[i]);

    printf("Here\n");


    while(1){

        promptUsr();
        readUsr(buff);


    }
    return 0;
}
