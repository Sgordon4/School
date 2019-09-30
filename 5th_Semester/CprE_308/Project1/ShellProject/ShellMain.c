#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFFER_SIZE 1024

static void promptUsr(){
    printf("Prompting user...\n");
}

static void readUsr(char *buffer){
    printf("Reading user...\n");
    while(1){
        if(fgets(buffer, sizeof(buffer), stdin) != NULL)
            //buffer = strchr(buffer, '\n');   //Remove trailing newline
            printf("%s", buffer);

    }
}


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



int main(int argc, char** argv) {
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
