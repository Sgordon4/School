#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>


typedef enum{
    TAILNODE = 0,
    CHECKJOB = 1,
    TRANSJOB = 2
} JobType;


typedef struct Job {
    int request_ID;
    JobType job_type;

    struct Job *next;
} Job;

typedef struct Queue {
    Job *head;
    Job *tail;
} Queue;

struct Job newJob(JobType job_type);
struct CheckJob newCheckJob(int account_ID);
struct TransJob newTransJob();
int addTransToJob(struct TransJob transJob, struct Trans transaction);
struct Trans newTransaction(int account_ID, int amount);

int isQueueEmpty();
int addToQueue(struct Job job);
struct Job popQueue();
void swapJobs(Job *job1, Job *job2);


Queue *QUEUE;

int main(int argc, char *argv[])
{
    printf("Hello world\n");

    //Initialize queue
    QUEUE = (struct Queue*)malloc(sizeof(struct Queue));


    Job job1 = {
        .request_ID = 1,
        .job_type = CHECKJOB
    };
    Job job2 = {
        .request_ID = 2,
        .job_type = CHECKJOB
    };

    printf("%d:%d\n", job1.request_ID, job2.request_ID);
    swapJobs(&job1, &job2);
    printf("%d:%d\n", job1.request_ID, job2.request_ID);

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
    int b = QUEUE->head->next->request_ID;
    int c = QUEUE->head->next->next->request_ID;

    printf("%d:%d:%d\n", a, b, c);
    printf("%d\n", isQueueEmpty());


}


int isQueueEmpty(){
    return (QUEUE->head == QUEUE->tail);
}
int addToQueue(struct Job job){
    //swapJobs(&job, &(QUEUE->tail));

    //QUEUE->tail->next = &job;   //Job is now the old tail
    //QUEUE->tail = &job;
    return 0;
}

//Swap the pointers of two jobs, effectively swapping contents
void swapJobs(Job *job1, Job *job2){
    Job temp = *job1;
    *job1 = *job2;
    *job2 = temp;
}
