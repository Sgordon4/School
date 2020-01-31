//Sample Client-Side Code in TCP Sockets Programming:
//=======================================================

#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>




int main(int argc, char *argv[]){


    struct sockaddr_in remoteaddr;

    char *SERVER_IP;
    int SERVER_PORT;

    //We're looking for IP address and port only
    if( argc == 3 )
    {
        SERVER_IP = argv[1];
        SERVER_PORT = atoi(argv[2]);
    }
    else{
        
        printf("Yo chief we got a problem\n");
        printf("You only passed %d arguments\n", argc);
        printf("Falling back to default values\n");
        
        SERVER_IP = "192.168.254.1";
        SERVER_PORT = 42069;
    }



    int clisock;
    if ( (clisock = socket(PF_INET, SOCK_STREAM, 0)) < 0 ){
        perror("socket() error:\n");
        exit(1);
    }

    remoteaddr.sin_family = PF_INET;
    remoteaddr.sin_port = htons(SERVER_PORT);
    remoteaddr.sin_addr.s_addr = inet_addr(SERVER_IP);


    connect(clisock, (struct sockaddr *) &remoteaddr, sizeof(remoteaddr));
    // communication between client and server starts here    

    char readBuffer[1000];
    read(clisock, readBuffer, sizeof(readBuffer));

    // communication between client and server ends here
    close(clisock);
    
    printf("%s\n", readBuffer);

    return 0;
}





