#include <stdio.h>
#include<string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/time.h>
#include <pthread.h> 

#include "Bank.h"


typedef enum{
    ERROR = 0,          //Job init failure sentinel value
    CHECKJOB = 1,
    TRANSJOB = 2,
    ENDJOB = 3
} JobType;


typedef struct CheckJob {
    int account_ID;
} CheckJob;

typedef struct Trans{
    int account_ID;     //ID of account to operate on
    int amount;         //Amount to add or remove from account balance
} Trans;
#define MAXTRANS 10
typedef struct TransJob {
    Trans **trans_list;      //List of individual transaction
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
    int size;
} Queue;

Job *newJob(JobType job_type);
CheckJob *newCheckJob(int account_ID);
TransJob *newTransJob();
Trans *newTransaction(int account_ID, int amount);

Queue *createQueue();
void destroyQueue(Queue *q);
int isQueueEmpty(Queue *q);
int enQueue(Queue *q, Job *job);
Job *popQueue(Queue *q);
Job *peekQueue(Queue *q);
void swapJobs(Job *job1, Job *job2);

void *threadDoJobs(void *arg);
Job *stringToJob(char *str);
void sortTransAscending(int numTrans, Trans **trans_list);
int combineDupTrans(int numTrans, Trans **trans_list);
void printTransArr(TransJob *transJob);


Queue *QUEUE;
int REQUESTNUM = 1;

pthread_mutex_t writer_lock;
pthread_mutex_t reader_lock;

int numThreads;
pthread_t *tid;

int numAccounts;
pthread_mutex_t *accLocks;
pthread_mutex_t courseAccLock;
int COARSE_LOCK = 1;

char *outputFileName;
FILE *outputFile;


int main(int argc, char *argv[])
{
    //printf("Hello world\n");

    if(COARSE_LOCK == 0){
        printf("Using non-coarse locking\n");
    }
    else{
        printf("Using coarse locking\n");
    }

    if(argc != 4){
        printf("Incorrect number of input parameters: 4 required\n");
        return 1;
    }

    //----------------------------------------------------------------------------
    //                                 Initialization 
    //----------------------------------------------------------------------------


    //Initialize threads
    numThreads = atoi(argv[1]);
    tid = malloc (numThreads * sizeof (pthread_t)); //Holds all thread IDs

    for(int i = 0; i < numThreads; i++){
        int error = pthread_create(&(tid[i]), NULL, &threadDoJobs, "blarg"); 
        if (error != 0) {
            printf("\nThread creation has failed\n");
            return 1; 
        }
    }


    //Initialize account locks
    numAccounts = atoi(argv[2]);
    accLocks = malloc (numAccounts * sizeof (pthread_mutex_t));

    for(int i = 0; i < numAccounts; i++){
        int error = pthread_mutex_init(&(accLocks[i]), NULL); 
        if (error != 0) {
            printf("\nMutex creation has failed\n"); 
            return 1;
        }
    }
    if(pthread_mutex_init(&courseAccLock, NULL)){
        printf("\nMutex creation has failed\n"); 
        return 1;
    }

    //Build the bank
    initialize_accounts(numAccounts);
    

    //Open the output file
    outputFileName = argv[3];
    outputFile = fopen(outputFileName, "w");
    if(outputFile == NULL){
        printf("Error opening file\n");
        return 1;
    }


    //Initialize queue
    QUEUE = createQueue();
    if (QUEUE == NULL) { 
        printf("Queue not allocated.\n"); 
        return 1;
    }


    //Initialize the mutexes
    if (pthread_mutex_init(&writer_lock, NULL) != 0) { 
        printf("\n Writer mutex init has failed!\n"); 
        return 1; 
    }
    if (pthread_mutex_init(&reader_lock, NULL) != 0) { 
        printf("\n Reader mutex init has failed!\n"); 
        return 1; 
    }


    //----------------------------------------------------------------------------
    //                                  Begin Loop 
    //----------------------------------------------------------------------------


    char line[1024];
    while(1){
        printf("> ");                           //Ask for user input

        char *str = fgets(line, 1024, stdin);   //Grab user input
        if (!str){
            printf("Couldn't read user input\n");
            continue;
        }
        str[strcspn(str, "\n")] = 0;            //Remove the newline char


        //Convert input into a job
        Job *job = stringToJob(str);
        if(job == NULL)
            continue;

        //Print out requestID
        printf("ID %d\n", job->request_ID);


        //Add job to queue
        pthread_mutex_lock(&writer_lock);       //Lock the writer mutex
        //pthread_mutex_lock(&reader_lock);       //Writer is given priority here
        enQueue(QUEUE, job);
        //pthread_mutex_unlock(&reader_lock);
        pthread_mutex_unlock(&writer_lock);

        
        //If the job is an exit job, break loop
        if(job->job_type == ENDJOB)
            break;
    }


    //----------------------------------------------------------------------------
    //                                 Dissasembly 
    //----------------------------------------------------------------------------


    //Reconnect with all threads
    for(int i = 0; i < numThreads; i++){
        pthread_join(tid[i], NULL); 
    }

    //Destroy mutexes
    pthread_mutex_destroy(&writer_lock);
    pthread_mutex_destroy(&reader_lock);

    for(int i = 0; i < numAccounts; i++){
        pthread_mutex_destroy(&accLocks[i]);
    } 
    pthread_mutex_destroy(&courseAccLock);


    fclose(outputFile);
    
    destroyQueue(QUEUE);    //Free Queue
    free(tid);              //Free thread array
    free(accLocks);         //Free account mutex locks array
    free_accounts() ;       //Rob the bank (there are no more locks)
    return 0;
}


void *threadDoJobs(void *arg){
    //printf("Thread created\n");
    //Forever
    while(1){
        //Grab a job
        Job *job = NULL;

        pthread_mutex_lock(&reader_lock);       //Lock mutexes
        pthread_mutex_lock(&writer_lock);       //Writer is given priority here
        if(!isQueueEmpty(QUEUE)){               //If the queue has a job...
            job = peekQueue(QUEUE);             //Look at it
            if(job->job_type != ENDJOB)         //If it's not an end job...
                job = popQueue(QUEUE);          //Gribbidy grab it out
        }
        pthread_mutex_unlock(&writer_lock);
        pthread_mutex_unlock(&reader_lock);

        //If we didn't get a job
        if(job == NULL)
            continue;


        //If the job we looked at was an END job, end the thread
        if(job->job_type == ENDJOB)
            break;

        //Otherwise do the job
        if(job->job_type == CHECKJOB){
            //printf("Grabbed CHECKJOB\n");
            CheckJob *checkJob = job->check_job;
            int ID = (checkJob->account_ID);

            //Lock the account
            if(COARSE_LOCK == 0){
                pthread_mutex_lock(&accLocks[ID-1]);
            }
            else{
                pthread_mutex_lock(&courseAccLock);
            }

            //Read the account balance
            int bal = read_account(ID);

            //Unlock the account
            if(COARSE_LOCK == 0){
                pthread_mutex_unlock(&accLocks[ID-1]);
            }
            else{
                pthread_mutex_unlock(&courseAccLock);
            }
            

            //Save the end time
            gettimeofday(&(job->time_end), NULL);

            //Print things out
            int reqID = job->request_ID;
            struct timeval start = job->time_arrival;
            struct timeval end = job->time_end;
            fprintf(outputFile, "%d BAL %d TIME %ld.%6.ld %ld.%6.ld\n", 
                reqID, bal, start.tv_sec, start.tv_usec, end.tv_sec, end.tv_usec);
        }

//-----------------------------------------------------------------------------

        else if(job->job_type == TRANSJOB){
            //printf("Grabbed TRANSJOB\n");
            TransJob *transJob = job->trans_job;
            Trans **trans_list = job->trans_job->trans_list;


            //If an account doesn't have enough to complete a transaction,
            //this = 1. Used to skip to unlocking accounts
            int failed = 0;

            int balances[MAXTRANS]; //Improves time efficiency by caching balances


            //Grab all of the accounts from least to greatest to avoid deadlock
            if(COARSE_LOCK == 1){
                pthread_mutex_lock(&courseAccLock);
            }

            for(int i = 0; i < transJob->num_trans; i++){
                int ID = trans_list[i]->account_ID;
                int amount = trans_list[i]->amount;

                //Lock the account
                if(COARSE_LOCK == 0){
                    pthread_mutex_lock(&accLocks[ID]);
                }

                //Check the account's balance
                int bal = read_account(ID);
                balances[i] = bal;

                //Make sure the account has enough
                if(bal + amount < 0){
                    printf("< Account %d does not have enough funding to complete "
                        "transactions (needs %d)\n", ID, amount);
                    failed = 1;
                    break;
                }
            }

            //Actually perform transactions and unlock all accounts used
            for(int i = 0; i < transJob->num_trans; i++){

                //If a transaction was invalid, skip all of them
                if(!failed){
                    int ID = trans_list[i]->account_ID;
                    int newBal = balances[i] + trans_list[i]->amount;

                    //Update balance
                    write_account(ID, newBal);
                }

                //Unlock the account
                if(COARSE_LOCK == 0){
                    pthread_mutex_unlock(&accLocks[trans_list[i]->account_ID]);
                }
            }

            if(COARSE_LOCK == 1){
                pthread_mutex_unlock(&courseAccLock);
            }

            //Save the end time
            gettimeofday(&(job->time_end), NULL);

            //Print things out
            //int reqID = job->request_ID;
            struct timeval start = job->time_arrival;
            struct timeval end = job->time_end;

            fprintf(outputFile, "OK TIME %ld.%6.ld %ld.%6.ld\n", 
                start.tv_sec, start.tv_usec, end.tv_sec, end.tv_usec);
        }

        //int requestID = job->request_ID;
        //printf("< ID %d\n", requestID);

    }

    //printf("Coolboi signing out\n");
    return NULL;
}



//=============================================================================


Queue *createQueue(){
    Queue *q = (Queue*)malloc(sizeof(Queue));

    if(q == NULL) return NULL;

    q->head = NULL;
    q->tail = NULL;

    q->size = 0;

    return q;
}

void destroyQueue(Queue *q){
    while(!isQueueEmpty(q)){
        popQueue(q);
    }
    free(q);
}

int isQueueEmpty(Queue *q){
    if(q == NULL)
        return 1;

    return (q->size == 0);
}
int enQueue(Queue *q, Job *job){

    //If the queue or the job are bad
    if(q == NULL || job == NULL)
        return 1;

    
    job->next = NULL;
    
    if(isQueueEmpty(q)){
        
        q->head = job;
        q->tail = job;
        q->size++;
        return 0;
    }
    
    //Otherwise...
    q->tail->next = job;
    q->tail = job;
    q->size++;

    return 0;
}
Job *popQueue(Queue *q){
    if(isQueueEmpty(q))
        return NULL;
    
    Job *job = (q->head);

    q->head = q->head->next;

    if(q->head == NULL){
        q->tail = NULL;
    }
    q->size--;

    return job;
}
Job *peekQueue(Queue *q){
    if(isQueueEmpty(q))
        return NULL;
    
    Job *job = (q->head);
    return job;
}

//Swap the pointers of two jobs, effectively swapping contents
void swapJobs(Job *job1, Job *job2){
    Job temp = *job1;
    *job1 = *job2;
    *job2 = temp;
}


//=============================================================================

/*
Job newJob(JobType job_type){
    struct Job temp = {
        .request_ID = REQUESTNUM++, //Global request number (increment it after)
        .job_type = job_type
    };
    gettimeofday(&(temp.time_arrival), NULL);

    return temp;
}*/
Job *newJob(JobType job_type){
    Job *job = malloc(sizeof(Job*));
    job->request_ID = REQUESTNUM++;
    job->job_type = job_type;
    gettimeofday(&(job->time_arrival), NULL);

    return job;
}

/*
CheckJob newCheckJob(int account_ID){
    CheckJob temp = {
        .account_ID = account_ID
    };
    return temp;
}
*/

CheckJob *newCheckJob(int account_ID){
    CheckJob *checkJob = malloc(sizeof(CheckJob*));
    checkJob->account_ID = account_ID;
    return checkJob;
}

/*
TransJob newTransJob(){
    TransJob transJob = {
        .num_trans = 0
    };
    return transJob;
}*/
TransJob *newTransJob(){
    TransJob *transJob = malloc(sizeof(TransJob*));
    transJob->num_trans = 0;
    transJob->trans_list = malloc(MAXTRANS * sizeof(Trans*));
    return transJob;
}

/*
Trans newTransaction(int account_ID, int amount){
    Trans trans = {
        .account_ID = account_ID,
        .amount = amount
    };
    return trans;
}*/
Trans *newTransaction(int account_ID, int amount){
    Trans *trans = malloc(sizeof(Trans*));
    trans->account_ID = account_ID;
    trans->amount = amount;
    return trans;
}



//=============================================================================



Job *stringToJob(char *str){
    char s[2] = " ";       //Split command on space
    char *jobType = strtok(str, s);


    //------------------------------------------------------------------

    if(!strcmp("CHECK", jobType)){
        //printf("Found a CHECK job\n");
        char *accID = strtok(NULL, s);

        if(!accID) {           //Uneven #, need to come in pairs
            printf("No account number specified\n");
            return NULL;
        }

        int ID = atoi(accID);

        //Make sure the ID is valid
        if(ID <= 0 || ID > numAccounts){
            printf("< ID %d is invalid\n", ID);
            return NULL;
        }

        CheckJob *checkJob = newCheckJob(ID);
        Job *job = newJob(CHECKJOB);

        job->check_job = checkJob;
        return job;
    }

    //------------------------------------------------------------------

    else if(!strcmp("TRANS", jobType)){
        //printf("Found a TRANS job\n");
        TransJob *transJob = newTransJob();

        char *id = "";
        char *amount = "";
        int count = 0;

        while(count < MAXTRANS){
            //------------ Grab the transaction information ------------
            id = strtok(NULL, s);
            if(!id) break;          //If no id, we've reached the end

            amount = strtok(NULL, s);
            if(!amount) {           //Uneven #, need to come in pairs
                printf("Invalid number of arguments\n");
                printf("No amount for ID %s\n", id);
                return newJob(ERROR);
            }

            int ID = atoi(id);
            int amt = atoi(amount);

            //Make sure the ID is valid
            if(ID <= 0 || ID > numAccounts){
                printf("< ID %d is invalid\n", ID);
                return NULL;
            }

            //------------------ Make the transaction -----------------
            Trans *trans = newTransaction(ID, amt);

            //Add to the list
            transJob->trans_list[count++] = trans;
        }
        transJob->num_trans = count;

        //Sort the transaction list
        sortTransAscending(transJob->num_trans, transJob->trans_list);
        //printTransArr(transJob);

        //Combine duplicates in the transaction list
        int n = combineDupTrans(transJob->num_trans, transJob->trans_list);
        transJob->num_trans = n;
        //printTransArr(transJob);

        //Make the main job
        Job *job = newJob(TRANSJOB);
        job->trans_job = transJob;
        return job;
    }

    //------------------------------------------------------------------

    else if(!strcmp("END", jobType)){
        //printf("Found an END job\n");
        Job *job = newJob(ENDJOB);
        return job;
    }

    //------------------------------------------------------------------

    else{
        printf("< Invalid command %s\n", jobType);
        return newJob(ERROR);
    }
}

/**
 * Given an list of transactions, sorts in ascending order by account ID. e.g.
 * [1:5, 7:6, 2:4, 3:9, 2:2] =>
 * [1:5, 2:4. 2:2, 3:9, 7:6]
 */
void sortTransAscending(int numTrans, Trans **trans_list){
    Trans *trans1, *trans2;

    for(int i = 0; i < numTrans; i++){

        for(int j = i+1; j < numTrans; j++){
            trans1 = trans_list[i];
            trans2 = trans_list[j];

            if(trans1->account_ID > trans2->account_ID){
                Trans *temp = trans1;
                trans_list[i] = trans_list[j];
                trans_list[j] = temp;
            }
        }
    }
}

/**
 * Given a sorted array of transaction structs, searches through and removes
 * duplicates, adding their amounts together. e.g.
 * [1:3, 1:5, 1:1, 2:6, 3:7] =>
 * [1:9, 2:6, 3:7]
 */
int combineDupTrans(int numTrans, Trans **trans_list){

    if(numTrans == 0 || numTrans == 1)
        return numTrans;

    Trans *temp[MAXTRANS];   //Max # of transactions is MAXTRANS, this is kinda redundant tho

    //Copy over non-duplicates and add duplicates ---------------------
    int j = 0;
    for(int i = 0; i < numTrans-1; i++){
        Trans *trans1 = trans_list[i];
        Trans *trans2 = trans_list[i+1];

        //If they are not duplicates, add trans1
        if(trans1->account_ID != trans2->account_ID){
            temp[j++] = trans_list[i];
        }
        //If they are duplicates, skip trans1 and add trans1 to trans2
        else{
            trans_list[i+1]->amount = trans1->amount + trans2->amount;
        }
        
    }//---------------------------------------------------------------

    //Store the last element, as it has not been stored yet and is now unique
    temp[j++] = trans_list[numTrans-1];

    //Replace the array and set the new count
    for(int i = 0; i < j; i++){
        trans_list[i] = temp[i];
    }

    return j;
}

void printTransArr(TransJob *transJob){
    printf("Number of transactions: %d\n", transJob->num_trans);
    for(int i = 0; i < transJob->num_trans; i++){
        printf("%d:%d\n", transJob->trans_list[i]->account_ID,
                            transJob->trans_list[i]->amount);
    }
}



