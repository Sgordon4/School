#include<stdio.h>
#include<stdlib.h>
#include<string.h>



char *cmd = "openssl passwd -6 -salt xgLS35S6 ";

int main(){

	FILE *fp;
	char *line = NULL;
	size_t len =  0;
	ssize_t read;

	fp = fopen("100k-most-used-passwords-NIST.txt", "r");
	if(fp == NULL)
		exit(EXIT_FAILURE);

	char test[100];
	char buffer[100];

	while((read = getline(&line, &len, fp)) != -1){

		//Strip newline from line
		line[read-1] = 0;

		//Build command
		strcpy(test, cmd);
		strcat(test, line);
		strcat(test, "> /dev/null");

		FILE *file = popen(test, "r");
		fgets(buffer, 100, file);
		printf("Buffer: %s\n", buffer);
	}

	fclose(fp);
	if(line)
		free(line);


	printf("Hello world");
	return 0;
}
