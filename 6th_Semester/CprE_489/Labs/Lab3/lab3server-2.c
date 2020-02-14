#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <netinet/in.h>
#include <linux/unistd.h>
#include <time.h>
	//1370 bytes packet size
int main(int argc, char** argv)
{



	//SERVER

	if (argc == 6){ //Makes sure there are 6 arguments
		char * listener_IP = argv[1];
		char* listener_port = argv[2];
		char* dest_IP = argv[3];
		char* dest_port = argv[4];
		char* loss_rate = argv[5];

		char buffer[4000];
		char cliBuffer[4000];
		
		
		struct sockaddr_in serveraddr, clientaddr, dummy;
	
		int sersock, consosck;

		sersock = socket(PF_INET, SOCK_DGRAM, 0);
		if(sersock < 0){perror("socket: ");}
		
		serveraddr.sin_addr.s_addr = inet_addr(listener_IP);
		u_short listener_port_int = atoi(listener_port);
		u_short dest_port_int = atoi(dest_port);
		

		serveraddr.sin_family = PF_INET;

		u_short loss_rate_int = htons(atoi(loss_rate));
		serveraddr.sin_port = htons(listener_port_int);
		clientaddr.sin_port = htons(dest_port_int);
		clientaddr.sin_addr.s_addr = inet_addr(dest_IP);
		clientaddr.sin_family = PF_INET;
		

		
		








		//CLIENT
		
		int clisock;


		//while(1){
		int cliLen = sizeof(clientaddr);
		
		clisock = socket(PF_INET, SOCK_DGRAM, 0);
		if(clisock < 0){perror("Clisock: ");}
		
		int bindTest = bind(sersock, (struct sockaddr *) &serveraddr, sizeof(serveraddr));
		if (bindTest != 0){ perror("Bind: ");}

		
		int connectNum = connect(clisock, (struct sockaddr *) &serveraddr, sizeof(serveraddr));
		if (connectNum != 0){perror("Connect: ");}

		int sendToSer = sendto(clisock, cliBuffer, sizeof(cliBuffer), 0, (struct sockaddr *)NULL, sizeof(serveraddr));
		if (sendToSer != 0){perror("First send: ");}
		while(1){
		    printf("Attempting to receive...\n");
		    int receive = recvfrom(sersock, buffer, sizeof(serveraddr), 0, NULL, NULL);
		    if (receive !=1){perror("Rec: ");}
		    else if(receive == 1){printf("Received\n");}
		    int randomnum = (rand() % 100) + 1;
		    if (randomnum > loss_rate_int)
		    {
		    
		        int sendNum = sendto(sersock, buffer, sizeof(serveraddr), 0, (struct sockaddr *) &clientaddr, cliLen);
		        if (sendNum != 0){perror("send: ");}
		        int serLen = sizeof(serveraddr);
		        int cliRec = recvfrom(sersock, buffer, sizeof(serveraddr), 0, NULL, NULL);
		        if (cliRec != 0){perror("cliRec: ");}
		
		    }

		
		}
	}
	
	
		
}


//192.168.254.7
