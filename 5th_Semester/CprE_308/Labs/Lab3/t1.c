/* t1.c 
   use mutex to access critical sections 
   compile use: gcc -o t1 t1.c -lpthread 
*/

#include <stdio.h>
#include <unistd.h>
#include <pthread.h>


/* define two routines called by threads*/
void* increment();	// increment global variable		    
void* decrement();	// decrement global variable

/* global variable: mutex*/
pthread_mutex_t mutex;  				

/*global variable: counter*/
int v;


int main (int argc, char *argv[])
{
  /* thread id or type*/
  pthread_t tid_increment;			
  pthread_t	tid_decrement; 
  
  /*initialize v as 0*/
  v = 0;
  
  /* mutex initialization*/
  pthread_mutex_init(&mutex, NULL);	
  
  /* thread creation */
  pthread_create(&tid_increment, NULL, increment, NULL); 
  pthread_create(&tid_decrement, NULL, decrement, NULL); 
  
  /* main waits for the two threads to finish */
  pthread_join(tid_increment, NULL);  
  pthread_join(tid_decrement, NULL);
  
  printf("v=%d\n",v);
  
  return 0;
}



void* increment()
{   
  int i;
  int a;
  for( i = 0; i < 99; i++) 
  {	
    pthread_mutex_lock(&mutex);
    
    /* critical section */
    a = v + 10;
    usleep(1);
    v = a;
    /* end of critical section */
    
    pthread_mutex_unlock(&mutex);
  }
}


void* decrement()
{	
  int i;
  int a;
  
  for(i = 0; i < 99; i++)
  {	
    pthread_mutex_lock(&mutex);
    
    /* critical section */
    a = v - 10;
    usleep(2);
    v = a;
    /* end of critical section */
    
    pthread_mutex_unlock(&mutex);
  }
}
