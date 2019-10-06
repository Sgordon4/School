#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>



void* thread1() {
    sleep(5);
    printf("Hello from thread 1\n");
    return NULL;
}
void* thread2() {
    sleep(5);
    printf("Hello from thread 2\n");
    return NULL;
}




int main (int argc, char *argv[])
{
    pthread_t t1, t2;

    //Create and launch a new thread, stored in t1 or t2 and executing
    //thread1 or thread2 respectively
    pthread_create(&t1, NULL, thread1, NULL );
    pthread_create(&t2, NULL, thread2, NULL );

    //Wait for the completion of t1 and t2, then continue
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    printf("Hello from the main thread\n");
}
