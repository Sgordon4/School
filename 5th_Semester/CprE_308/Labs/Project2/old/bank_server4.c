#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>



typedef enum{
    TAILNODE = 0,
    CHECKJOB = 1,
    TRANSJOB = 2
} JobType;


struct CheckJob {
    int account_ID;
};
struct Trans{
    int account_ID;     //ID of account to operate on
    int amount;         //Amount to add or remove from account balance
};
struct TransJob {
    struct Trans *trans_list[10];//List of individual transaction
    int num_trans;              //Number of transactions in list
};

struct Job {
    int request_ID;
    JobType job_type;           //Is this job a CheckJob, TransJob, QueueHead?
    struct timeval time_arrival;//Arrival time
    struct timeval time_end;    //End time

    struct CheckJob *check_job; //If this job is a CheckJob, this holds info
    struct TransJob *trans_job; //If this job is a TransJob, this holds info

    struct Job *next;           //Pointer to the next request
};

struct Queue {
    struct Job *head;
    struct Job *tail;
};


struct Job newJob(JobType job_type);
struct CheckJob newCheckJob(int account_ID);
struct TransJob newTransJob();
int addTransToJob(struct TransJob transJob, struct Trans transaction);
struct Trans newTransaction(int account_ID, int amount);

int isQueueEmpty();
int addToQueue(struct Job job);
struct Job popQueue();
int swapJobs(struct Job **job1, struct Job **job2);







struct Queue *QUEUE;
int REQUESTNUM = 0;

int main(int argc, char *argv[])
{
    printf("Hello world\n");

    //Initialize queue
    QUEUE = (struct Queue*)malloc(sizeof(struct Queue));

    //Create the head node
    struct Job tailJob = newJob(TAILNODE);

    //Add it to the queue
    QUEUE->head = &tailJob;
    QUEUE->tail = &tailJob;

    struct Job job1 = newJob(CHECKJOB);
    struct timeval time = job1.time_arrival;
    printf("time is: %ld.%6.ld\n", time.tv_sec, time.tv_usec);

    struct Job job2 = newJob(CHECKJOB);
    struct Job job3 = newJob(CHECKJOB);
    struct Job job4 = newJob(CHECKJOB);
    printf("%d\n", job1.request_ID);
    printf("%d\n", job2.request_ID);
    printf("%d\n", job3.request_ID);
    printf("%d\n", job4.request_ID);

    //Swap job1 and job2
    //struct Job *temp = &job1;
    //job2 = &job2;

    struct Job *job5 = {
        .request_ID = 5
    };
    swapJobs(&job5, &job2);


    addToQueue(job1);
    addToQueue(job2);
    addToQueue(job3);
    addToQueue(job4);

    struct CheckJob checkJob1 = newCheckJob(3);
    job1.check_job = &checkJob1;

    printf("Q head: %d\n", QUEUE->head->request_ID);
    printf("Q 1: %d\n", QUEUE->head->next->request_ID);
    printf("Q 2: %d\n", QUEUE->head->next->next->request_ID);
    printf("Q 3: %d\n", QUEUE->head->next->next->next->request_ID);

    /*
    while(!isQueueEmpty()){
        struct Job popped = popQueue();
        int requestID = popped.request_ID;
        JobType type = popped.job_type;

        printf("Job %d: %d\n", requestID, type);
    }
    */

}




int isQueueEmpty(){
    return (QUEUE->head == QUEUE->tail);
}
//Adding to the queue is thread safe
int addToQueue(struct Job job){
    //swapJobs(&job, &(QUEUE->tail));

    //QUEUE->tail->next = &job;       //Job is now the old tail
    //QUEUE->tail = &job;
    /*
    struct Job *tailPointer = (QUEUE->tail);
    job.next = tailPointer;
    *QUEUE->tail = job;
    QUEUE->tail = tailPointer;
    */
    return 0;
}
//Popping from the queue is NOT thread safe
struct Job popQueue(){
    struct Job temp;
    temp = *(QUEUE->head);
    QUEUE->head = QUEUE->head->next;
    return temp;
}
int swapJobs(struct Job **job1, struct Job **job2){
    struct Job *temp = *job1;
    *job1 = *job2;
    *job2 = temp;

    return 0;
}



struct Job newJob(JobType job_type){
    struct Job temp = {
        .request_ID = REQUESTNUM++, //Global request number (increment it after)
        .job_type = job_type
    };
    gettimeofday(&(temp.time_arrival), NULL);

    return temp;
}

struct CheckJob newCheckJob(int account_ID){
    struct CheckJob temp = {
        .account_ID = account_ID
    };
    return temp;
}

struct TransJob newTransJob(){
    struct TransJob transJob = {
        .num_trans = 0
    };
    return transJob;
}
int addTransToJob(struct TransJob transJob, struct Trans transaction){
    int index = transJob.num_trans;
    transJob.trans_list[index] = &transaction;
    transJob.num_trans = transJob.num_trans + 1;

    return index;
}
struct Trans newTransaction(int account_ID, int amount){
    struct Trans trans = {
        .account_ID = account_ID,
        .amount = amount
    };
    return trans;
}











/*
struct Job *newJob(int request_ID, JobType job_type){
    struct Job *temp = NULL;
    temp = (struct Job*)malloc(sizeof(struct Job));

    temp->request_ID = request_ID;
    temp->job_type = job_type;

    gettimeofday(&(temp->time_arrival), NULL);

    return temp;
}
*/
