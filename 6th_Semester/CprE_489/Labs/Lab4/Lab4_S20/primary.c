#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <stdlib.h>
#include "ccitt16.h"
#include "utilities.h"
#include "introduceerror.h"


#define MAX(a,b) (((a)>(b))?(a):(b))


long M = 2147483647;

void IntroduceError(char *data, double p)
{
	char c, *pointer = data;
	int i;
	while (*pointer != '\0') {
		c = 0x01;
		for ( i = 0; i < 8; i++) {
			if ((double)random()/M <= p)
				*pointer ^= c;
			c <<= 1;
		}
		pointer++;
	}
}




void primary(int sockfd, double ber) {

    char msg[5000];
    char srvReply[150];

	unsigned char packet[PACKET_SIZE];

    int readSize;
    
    int currPacket = 0;
    int globalPackNum = 0;


    //keep communicating with server
    while(1)
    {
        printf("Enter message : ");

        //Grab all the input and store it in msg
        fgets(msg, sizeof(msg), stdin);


        //Begin go-back-n protocol ===========================================

        int msgSize = strlen(msg);
        int numPackets = (msgSize + (DATA_LENGTH - 1)) / DATA_LENGTH;  //Int round up

        
        //Update the # of global packets sent
        globalPackNum += currPacket;

        currPacket = 0;     //Current packet to send

        /*
        If we have HELLO,
        msgSize = 6 (HELLO + "\n")
        numPackets = 3
        currPacket goes from 0 -> 2
        */

        while(currPacket < numPackets){
        
            //# of packets to send this burst
            int count = 0;
            
            while(count < WINDOW_SIZE && currPacket < numPackets){

                //Grab the part of the message to send
                char buff[DATA_LENGTH+1];
                memset(buff, '\0', sizeof(buff));
                strncpy(buff, msg + (currPacket*DATA_LENGTH), DATA_LENGTH);
                //memcpy(buff, &msg[currPacket * DATA_LENGTH], DATA_LENGTH);

                printf("Packet %d:%d - %d %s\n", count, currPacket, globalPackNum, buff);
                
                //Build packet and send it off
                buildPacket(packet, DATA_PACKET, buff, globalPackNum + currPacket);
                
                //printPacket(packet);
                
                IntroduceError(packet, ber);

                if( send(sockfd , packet, PACKET_SIZE, 0) < 0)
                    perror("Send failed");

                count++;
                currPacket++;
                
                
            }

            printf("Window completed =========================\n");
            
            
            
            //Print every reply
            /**/
            printf("Packets sent: %d\n", count);
            printf("Server's reply: ===============\n");
            
            int maxSeqNumReceived = 0;
            int did_we_get_a_NAK = 0;
            
            
            
            int temp = count;
            for(temp; temp > 0; temp--){
                
                if( (readSize = recv(sockfd , srvReply , PACKET_SIZE , 0)) < 0)
                    perror("recv failed");
	            
	            printPacket(srvReply);
	            int numReceived = srvReply[1];
	            
	            
	            //Find what type the packet is
	            switch(srvReply[0])
	            {
	                //We dont care about the difference between these two
		            
		            case NAK_PACKET:
		                did_we_get_a_NAK = 1;
		            case ACK_PACKET:
			            maxSeqNumReceived = MAX(srvReply[1] - globalPackNum, maxSeqNumReceived);
			            break;
		            default:
			            printf("Who knows what type this packet is. I dont.\n");
	            }
            
            }
            
            
            
            currPacket -= count;
            if(!did_we_get_a_NAK){
                //printf("Final Max: %d ---------------------\n", maxSeqNumReceived);
                currPacket = maxSeqNumReceived;
            }
            
            
            
            
            //printf("Reply length: %d\n", strlen(srvReply));

            
	        
	        //strncpy(buff, msg + (currPacket*DATA_LENGTH), DATA_LENGTH);

            

            //Check last received ack or nak and set currPacket to that.
            //Enables re-sending corrupt packages and similar events.
            
            //Receive a reply from the server
            
		        
        }
    }
}

int whatTypeIsThisPacket(unsigned char packet[]){
    switch(packet[0])
	{
		case DATA_PACKET:
			return 0;
		case ACK_PACKET:
			return 1;
		case NAK_PACKET:
			return 2;
		default:
			printf("Who knows what type this packet is\n");
	}
}






















