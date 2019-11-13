#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>



typedef enum{
    TAILNODE = 0,
    CHECKJOB = 1,
    TRANSJOB = 2
} JobType;

struct TailNode {
    struct Job* prev;
};
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
    JobType job_type;           //Is this job a CheckJob, TransJob, ...?
    struct timeval time_arrival;//Arrival time
    struct timeval time_end;    //End time

    struct CheckJob *check_job; //If this job is a CheckJob, this holds info
    struct TransJob *trans_job; //If this job is a TransJob, this holds info
    struct TailNode *tail_node; //If this job is the TailNode, points to prev job

    struct Job *next;           //Pointer to the next request
};

struct Queue {
    struct Job *head;
    struct Job *tail;
    int num_jobs;
};


struct Job newJob(JobType job_type);
struct CheckJob newCheckJob(int account_ID);
struct TransJob newTransJob();
int addTransToJob(struct TransJob transJob, struct Trans transaction);
struct Trans newTransaction(int account_ID, int amount);


struct Queue *QUEUE;
int REQUESTNUM = 0;

int main(int argc, char *argv[])
{
    printf("Hello world\n");

    //Initialize queue
    QUEUE = (struct Queue*)malloc(sizeof(struct Queue));

    //Create the tail job
    struct Job tailJob = newJob(TAILNODE);

    struct Job job1 = newJob(CHECKJOB);
    struct timeval time = job1.time_arrival;

    printf("time is: %ld.%06.ld\n", time.tv_sec, time.tv_usec);

    struct CheckJob checkJob1 = newCheckJob(3);
    job1.check_job = &checkJob1;

    struct TailNode tail = {
        .prev = &job1
    };

    printf("EEEEE: %d\n", tail.prev->request_ID);
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
