# include <stdio.h>
# include <unistd.h>

main() {
	execl("/bin/ls", "ls", NULL);
	printf("What happened?\n");
}
