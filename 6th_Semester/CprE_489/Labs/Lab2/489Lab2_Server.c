//Sample Server-Side Code in TCP Sockets Programming:
//=======================================================

#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include <netinet/in.h>

#include <sys/sysinfo.h>
#include <linux/unistd.h>

#include <time.h>


int main(){
    
    char *IP_ADDRESS = "192.168.254.7";
    int PORT = 40404;
    
    char *writeBuffer = "Bruuhh";

    
    struct sockaddr_in serveraddr, clientaddr;
    int len = sizeof(clientaddr);
    int sersock, consock;
    
    if ( (sersock = socket(PF_INET, SOCK_STREAM, 0)) < 0 ){
        perror("socket() error:\n");
        exit(1);
    }

    serveraddr.sin_family = PF_INET;
    serveraddr.sin_port = htons(PORT);
    serveraddr.sin_addr.s_addr = inet_addr(IP_ADDRESS);
    // OR: serveraddr.sin_addr.s_addr = htonl(INADDR_ANY);

    bind(sersock, (struct sockaddr *) &serveraddr, sizeof(serveraddr));
    listen(sersock, 10);


    while(1){
        consock = accept(sersock, (struct sockaddr *) &clientaddr, &len);
        // communication between server and client starts here
        
        //Grabbing uptime information -----------------------------------
        
        if(fork() == 0){
        
            char* args[] = {
                "uptime",
                NULL
            };
            
            dup2(consock, 1);
            
            execvp("uptime", args);
            exit(0);
            
        }
        
        
        
        //write(consock, writeBuffer, sizeof(writeBuffer));
        //write(consock, s_info.uptime, sizeof(s_info.uptime));
        
        // communication between server and client ends here
        close(consock);
    }
    close(sersock);


    return 0;
}


/*
//Grab system time:
        time_t rawtime;
        struct tm *info;
        time( &rawtime );
        info = localtime( &rawtime );
        printf("Current local time and date: %s", asctime(info));
  
        //Grab uptime:
        struct sysinfo uptimeInfo;
        sysinfo(&uptimeInfo);
        printf("Uptime = %ld\n", uptimeInfo.uptime);
*/





