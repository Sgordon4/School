#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <stdlib.h>
#include "ccitt16.h"
#include "utilities.h"
#include "introduceerror.h"


void primary(int sockfd, double ber) {

    char msg[5000];
    char srvReply[150];

	unsigned char packet[PACKET_SIZE];

    int readSize;


    //keep communicating with server
    while(1)
    {
        printf("Enter message : ");

        //Grab all the input and store it in msg
        fgets(msg, (DATA_LENGTH * WINDOW_SIZE)+1, stdin);


        //Begin go-back-n protocol ===========================================

        int msgSize = strlen(msg);
        int numPackets = (msgSize + (DATA_LENGTH - 1)) / DATA_LENGTH;  //Int round up

        int currPacket = 0;     //Current packet to send

        /*
        If we have HELLO,
        msgSize = 6 (HELLO + "\n")
        numPackets = 3
        currPacket goes from 0 -> 2
        */

        while(currPacket < numPackets){
            //# of packets to send this burst
            int count = 0;
            while(count < 3 && currPacket < numPackets){

                //Grab the part of the message to send
                char buff[2];
                memcpy(buff, &msg[currPacket * 2], DATA_LENGTH);
                printf("Data to be sent: %s\n", buff);

                count++;
                currPacket++;
            }

            printf("currPacket: %d\n", currPacket);
            printf("numPackets: %d\n", numPackets);
        }





/*
        char transactionComplete = 0;
        while(!transactionComplete){

            //Send the n packets -------------------------------

            int count = WINDOW_SIZE;
            while(count > 0 && location < msgSize){

                //Grab the part of the message to be packaged
                char buff[DATA_LENGTH];
                memcpy(buff, &msg[location], DATA_LENGTH);
                printf("Data to be sent: %s\n", buff);

                //Build packet and send it off
                buildPacket(packet, DATA_PACKET, buff, (location/DATA_LENGTH));

                if( send(sockfd , packet, PACKET_SIZE, 0) < 0)
                    perror("Send failed");

                count--;
                location += 2;
            }


            //Receive a reply from the server
            if( (readSize = recv(sockfd , srvReply , 149 , 0)) < 0)
                perror("recv failed");

            printf("Server's reply:\n");
		        printPacket(srvReply);

		    transactionComplete = 1;

        }

        /*
        printf("Size: %d\n", strlen(msg));
        int BytesToSend = (strlen(msg) - 1) / WINDOW_SIZE + 1;
        printf("BytesToSend: %d\n", BytesToSend);


        int packets_left = WINDOW_SIZE;
        while(packets_left > 0 &&
        for(i = WINDOW_SIZE; i > 0; i--){
            //Grab the part of the message to be packaged
            char buff[DATA_LENTGH];

            buildPacket(packet, DATA_PACKET, memcpy( buff, &msg[10], DATA_LENTGH ), packetNum++);

        }
        */
    }

}
