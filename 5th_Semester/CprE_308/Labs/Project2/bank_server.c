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


int main(int argc, char *argv[])
{


    printf("Hello world\n");

}
