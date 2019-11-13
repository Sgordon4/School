#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>



typedef enum{
    TAILNODE = 0,
    CHECKJOB = 1,
    TRANSJOB = 2
} JobType;


/*
//Represents the tail node in the queue
typedef struct{
    struct job *prev;   //Pointer to the previous request
} TailNode;
*/
//Represents the tail node in the queue
struct TailNode{
    struct job *prev;   //Pointer to the previous request
};


//Account-balance-check job
typedef struct{
    int account_ID;     //ID of account to check balance
} CheckJob;


//Individual transaction job
typedef struct {
    int account_ID;     //ID of account to operate on
    int amount;         //Amount to add or remove from account balance
} Trans;
//Holder for multiple transaction jobs, as up to 10 are allowed in a command
typedef struct {
    struct Trans *trans_list;   //List of individual transaction
    int num_trans;              //Number of transactions in list
} TransJob;


//An individual job present in the queue.
//job_type identifies it as one of the unique job types,
//represented by the structs above this one
typedef struct{
    int request_ID;
    JobType job_type;           //Is this job a CheckJob, TransJob, ...?
    struct timeval time_arrival;//Arrival time
    struct timeval time_end;    //End time

    struct CheckJob *check_job; //If this job is a CheckJob, this holds info
    struct TransJob *trans_job; //If this job is a TransJob, this holds info
    struct TailNode *tail_node; //If this job is the TailNode, points to prev job

    struct job *next;           //Pointer to the next request
} Job;



typedef struct{
    struct Job *head, *tail;
    int num_jobs; // number of jobs in current queue
} Queue;




Queue *queue;

int main(int argc, char *argv[])
{
    struct TailNode tailnode = {
        .prev = NULL
    };
    Job tail_job = {
        .job_type = TAILNODE,
        .tail_node = tailnode
    };

    queue = (struct Queue*) malloc(sizeof(struct Queue*));
    queue->head = &tail_node;
    queue->tail = &tail_node;

}
