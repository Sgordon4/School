/*******************************************************************************
*
* CprE 308 Scheduling Lab
*
* scheduling.c
*******************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define NUM_PROCESSES 20

typedef struct process
{
  /* Values initialized for each process */
  int arrivaltime;  /* Time process arrives and wishes to start */
  int runtime;      /* Time process requires to complete job */
  int priority;     /* Priority of the process */

  /* Values algorithm may use to track processes */
  int starttime;
  int endtime;
  int flag;
  int remainingtime;
} process;

/* Forward declarations of Scheduling algorithms */
void first_come_first_served(process *proc);
void shortest_remaining_time(process *proc);
void round_robin(process *proc);
void round_robin_priority(process *proc);

int main()
{
  int i;
  process proc[NUM_PROCESSES],      /* List of processes */
            proc_copy[NUM_PROCESSES]; /* Backup copy of processes */

  /* Seed random number generator */
  /*srand(time(0));*/  /* Use this seed to test different inputs */
  srand(0xC0FFEE);     /* Use this seed to test fixed input */

  /* Initialize process structures */
  for(i=0; i<NUM_PROCESSES; i++)
  {
    proc[i].arrivaltime = rand()%100;
    proc[i].runtime = (rand()%30)+10;
    proc[i].priority = rand()%3;

    proc[i].starttime = 0;
    proc[i].endtime = 0;
    proc[i].flag = 0;
    proc[i].remainingtime = 0;
  }

  /* Show process values */
  printf("Process\tarrival\truntime\tpriority\n");
  for(i=0; i<NUM_PROCESSES; i++)
    printf("%d\t%d\t%d\t%d\n", i, proc[i].arrivaltime, proc[i].runtime,
           proc[i].priority);

  /* Run scheduling algorithms */
  printf("\n\n--- First come first served\n");
  memcpy(proc_copy, proc, NUM_PROCESSES * sizeof(process));
  first_come_first_served(proc_copy);

  printf("\n\n--- Shortest remaining time\n");
  memcpy(proc_copy, proc, NUM_PROCESSES * sizeof(process));
  shortest_remaining_time(proc_copy);

  printf("\n\n--- Round Robin\n");
  memcpy(proc_copy, proc, NUM_PROCESSES * sizeof(process));
  round_robin(proc_copy);

  printf("\n\n--- Round Robin with priority\n");
  memcpy(proc_copy, proc, NUM_PROCESSES * sizeof(process));
  round_robin_priority(proc_copy);

  return 0;
}

void first_come_first_served(process *proc)
{

    //Sort proc by earliest arrival time:
    int i, j;
    for (i = 0; i < NUM_PROCESSES; i++){
        for (j = 0; j < NUM_PROCESSES; j++){

            if (proc[j].arrivaltime > proc[i].arrivaltime){
                process tmp = proc[i];
                proc[i] = proc[j];
                proc[j] = tmp;
            }
        }
    }

    int time = 1;       // time stamp
    int t = 1;          // Length of time slot
    int finished = 0;   // number of finished processes

    i = 0;

    while (finished < NUM_PROCESSES) // while there are still unfinished processes
    {

        // Assuming the process to run is at index "i" of proc array
        if (proc[i].starttime == 0) // this is the first time this process gets to run
        {
            proc[i].starttime = time;
            proc[i].remainingtime = proc[i].runtime;
            printf("Process %d started at time %d\n", i, proc[i].starttime);
        }
        /*TODO: determine how many time slots to give this process
        HINT: in FCFS, the process should be given as much as it needed;
        in other algorithms, you can assign only one time slot and re-evaluate
        in the next iteration*/

        // Assuming we are giving this process "t" times
        // Assign time to this process and update time
        proc[i].remainingtime -= t;
        time += t;

        // If this process finished, announce it
        if (proc[i].remainingtime==0)
        {
            proc[i].endtime = time; // mark the end time
            proc[i].flag = 1; // mark process as done
            finished++;
            printf("Process %d finished at time %d\n", i, proc[i].endtime);

            i = finished;   //i == finished, just kept it as i for readability
        }
    }

    /* TODO: At the end, loop through *proc and calculate the average wait time.
    For each process, wait time = endtime - arrivaltime. */

    int avgWaitTime = 0;
    for(i = 0; i < NUM_PROCESSES; i++){
        avgWaitTime += proc[i].starttime - proc[i].arrivaltime;
    }
    //Or, as proc is sorted
    //int avgWaitTime = time - proc[NUM_PROCESSES].runtime;

    printf("Average time from arrival to finish is %d seconds\n", avgWaitTime);
}

void shortest_remaining_time(process *proc)
{
  /* TODO: Implement scheduling algorithm here */
}

void round_robin(process *proc)
{
  /* TODO: Implement scheduling algorithm here */
}

void round_robin_priority(process *proc)
{
  /* TODO: Implement scheduling algorithm here */
}
