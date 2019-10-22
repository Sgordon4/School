void first_come_first_served(process *proc)
{
    int sorted[NUM_PROCESSES];  //Order to run processes

    //Sort proc by earliest arrival time:
    int i, j;
    for (i = 0; i < NUM_PROCESSES; i++){
        for (j = 0; j < NUM_PROCESSES; j++){

            if (proc[j].arrivaltime > proc[i].arrivaltime){
                //Instead of sorting proc, put the sorted order in "sorted"
                sorted[i] = j;

                /*
                process tmp = proc[i];
                proc[i] = proc[j];
                proc[j] = tmp;
                */
            }
        }
        printf("%d, ", sorted[i]);
    }
    printf("\n");

    int time = 1;       // time stamp
    int t = 1;          // Length of time slot
    int finished = 0;   // number of finished processes

    int current = 0;

    while (finished < NUM_PROCESSES) // while there are still unfinished processes
    {
        i = sorted[current];
        current++;

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

    printf("Average time from arrival to finish is %d seconds\n", avgWaitTime);
}







//------------------------------------------------------------------------------


for (int i = 0; i < NUM_PROCESSES; i++)                     //Loop for ascending ordering
{
    for (int j = 0; j < NUM_PROCESSES; j++)             //Loop for comparing other values
    {
        if (proc[j].arrivaltime > proc[i].arrivaltime)                //Comparing other array elements
        {
            process tmp = proc[i];         //Using temporary variable for storing last value
            proc[i] = proc[j];            //replacing value
            proc[j] = tmp;             //storing last value
        }
    }
}
printf("\n\nAscending : ");                     //Printing message
for (int i = 0; i < NUM_PROCESSES; i++)                     //Loop for printing array data after sorting
{
    printf("%d: %d, ", i, proc[i].arrivaltime);
}
