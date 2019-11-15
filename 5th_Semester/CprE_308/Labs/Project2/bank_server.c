#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>


typedef enum{
    CHECKJOB = 0,
    TRANSJOB = 1,
    ENDJOB = 2
} JobType;


typedef struct CheckJob {
    int account_ID;
} CheckJob;

typedef struct Trans{
    int account_ID;     //ID of account to operate on
    int amount;         //Amount to add or remove from account balance
} Trans;
typedef struct TransJob {
    struct Trans *trans_list[10];//List of individual transaction
    int num_trans;              //Number of transactions in list
} TransJob;


typedef struct Job {
    int request_ID;             //Global ID
    JobType job_type;           //Is this a CheckJob, TransJob, ...?
    struct timeval time_arrival;//Time job is created
    struct timeval time_end;    //Time job is completed

    CheckJob *check_job;        //If this job is a CheckJob, this holds info
    TransJob *trans_job;        //If this job is a TransJob, this holds info

    struct Job *next;
} Job;


typedef struct Queue {
    Job *head;
    Job *tail;
} Queue;

Job newJob(JobType job_type);
struct CheckJob newCheckJob(int account_ID);
struct TransJob newTransJob();
int addTransToJob(struct TransJob transJob, struct Trans transaction);
struct Trans newTransaction(int account_ID, int amount);

int isQueueEmpty();
int enQueue(Job job);
Job popQueue();
void swapJobs(Job *job1, Job *job2);


Queue *QUEUE;
int REQUESTNUM = 0;

int main(int argc, char *argv[])
{
    printf("Hello world\n");

    //Initialize queue
    QUEUE = (struct Queue*)malloc(sizeof(struct Queue));

    Job jobTail = {
        .request_ID = -1,
        .job_type = CHECKJOB
    };
    QUEUE->head = &jobTail;
    QUEUE->tail = &jobTail;

    printf("Empty? - %d\n", isQueueEmpty());

    Job job1 = {
        .request_ID = 1,
        .job_type = CHECKJOB
    };
    Job job2 = {
        .request_ID = 2,
        .job_type = CHECKJOB
    };


    enQueue(job1);



    printf("\n-------\n");
    printf("Job1: %d\n", job1.request_ID);
    printf("Job2: %d\n", job2.request_ID);
    printf("-------\n");
    swapJobs(&job1, &job2);
    printf("Job1: %d\n", job1.request_ID);
    printf("Job2: %d\n", job2.request_ID);
    printf("-------\n");
    swapJobs(&job1, &job2);
    printf("Job1: %d\n", job1.request_ID);
    printf("Job2: %d\n", job2.request_ID);
    printf("-------\n\n");

}


int isQueueEmpty(){
    return (QUEUE->head == QUEUE->tail);
}
int enQueue(Job job){
    //Job *temp = &newJob(CHECKJOB);
    return 0;
}
Job popQueue(){
    return;
}

//Swap the pointers of two jobs, effectively swapping contents
void swapJobs(Job *job1, Job *job2){
    Job temp = *job1;
    *job1 = *job2;
    *job2 = temp;
}




Job newJob(JobType job_type){
    struct Job temp = {
        .request_ID = REQUESTNUM++, //Global request number (increment it after)
        .job_type = job_type
    };
    gettimeofday(&(temp.time_arrival), NULL);

    return temp;
}

CheckJob newCheckJob(int account_ID){
    CheckJob temp = {
        .account_ID = account_ID
    };
    return temp;
}

TransJob newTransJob(){
    TransJob transJob = {
        .num_trans = 0
    };
    return transJob;
}
int addTransToJob(TransJob transJob, Trans transaction){
    int index = transJob.num_trans;
    transJob.trans_list[index] = &transaction;
    transJob.num_trans = transJob.num_trans + 1;

    return index;
}
Trans newTransaction(int account_ID, int amount){
    Trans trans = {
        .account_ID = account_ID,
        .amount = amount
    };
    return trans;
}



/*
int addToQueue(struct Job job){
    printf("AAAA: %d\n", job.request_ID);
    printf("Urg0: %d\n", QUEUE->tail->request_ID);
    swapJobs(&job, QUEUE->tail);
    printf("Urg1: %d\n", QUEUE->tail->request_ID);

    QUEUE->tail->next = &job;   //Job is now the old tail
    printf("Urg2: %d\n", QUEUE->tail->request_ID);
    Job jobTail = {
        .request_ID = -1,
        .job_type = TAILNODE
    };
    QUEUE->tail = &jobTail;
    printf("Urg3: %d\n", QUEUE->tail->request_ID);
    return 0;
}
*/



void thing(){
    Job job1 = {
        .request_ID = 1,
        .job_type = CHECKJOB
    };
    Job job2 = {
        .request_ID = 2,
        .job_type = CHECKJOB
    };
    Job job3 = {
        .request_ID = 3,
        .job_type = CHECKJOB
    };

    QUEUE->head = &job1;
    QUEUE->tail = &job1;
    printf("%d\n", isQueueEmpty());

    QUEUE->head->next = &job2;
    QUEUE->head->next->next = &job3;
    QUEUE->tail = &job3;






    int a = QUEUE->head->request_ID;
    printf("%d:", a);
    int b = QUEUE->head->next->request_ID;
    printf("%d:", b);
    int c = QUEUE->head->next->next->request_ID;
    printf("%d\n", c);


    printf("%d\n", isQueueEmpty());
}


/*
int a = job.request_ID;
int b = QUEUE->head->request_ID;
printf("Job1: %d, Job2: %d\n", a, b);

swapJobs(&job, QUEUE->tail);

a = job.request_ID;
b = QUEUE->head->request_ID;
printf("Job1: %d, Job2: %d\n", a, b);

*/

/*
printf("%d\n", isQueueEmpty());

a = QUEUE->head->request_ID;
b = QUEUE->head->next->request_ID;
int c = QUEUE->head->next->next->request_ID;

printf("%d:%d:%d\n", a, b, c);





printf("%d:%d\n", job1.request_ID, job2.request_ID);
swapJobs(&job1, &job2);
printf("%d:%d\n", job1.request_ID, job2.request_ID);
*/

/*
printf("%d:%d\n", job1.request_ID, job2.request_ID);
swapJobs(&job1, &job2);
printf("%d:%d\n", job1.request_ID, job2.request_ID);
*/
