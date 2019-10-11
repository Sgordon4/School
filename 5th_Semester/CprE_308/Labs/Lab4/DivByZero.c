#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

void my_routine();

int main() {
    //Capture any SIGFPEs received
    signal(SIGFPE, my_routine);

    //Perform div-by-zero
    printf("Performing division by zero: \n");
    int a = 4, b = 0;
    a = a/b;

    printf("Will never be reached\n");
    return 0;
}
/* will be called asynchronously, even during a sleep */
void my_routine() {
    printf("Caught a SIGFPE\n");
    exit(0);
}
