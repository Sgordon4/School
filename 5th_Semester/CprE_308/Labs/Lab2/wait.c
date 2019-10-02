#include <stdlib.h>
#include <stdio.h>

int main() {
	int i=0, j=0, ret;
	ret = fork();
	if (ret == 0) {
		printf("Child starts\n");
		for(i=0; i<500000; i++)
			printf("Child: %d\n", i);
		printf("Child ends\n");
	} else {
		wait(NULL);
		printf("Parent starts\n");
		for(j=0; j<500000; j++)
			printf("Parent: %d\n", j);
		printf("Parent ends\n");
	}
	return 0;
}
