#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<openssl/sha.h>

int main(){
	printf("Hello");

	unsigned char ibuf[] = "compute sha1";
	unsigned char obuf[20];

	SHA(ibuf, strlen(ibuf), obuf);
	int i;
	for(i=0; i < 20; i++){
		printf("%02x ", obuf[i]);
	}
	printf("\n");

	return 0;
}

//Make read from most used passwords FILE (using cat)
//Try getting the hash of one
//password is actually password with leetcode
