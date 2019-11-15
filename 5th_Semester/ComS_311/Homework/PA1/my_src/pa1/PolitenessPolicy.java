package pa1;


/**
 * Policy to ensure a time gap between a set amount of request so as to not overload servers
 */
public class PolitenessPolicy {
    int maxRequests;        //Maximum number of requests to allow before sleeping
    int currentRequests;    //Current number of requests since last sleep
    int msSleep;            //Milliseconds to sleep after every maxRequests requests

    public PolitenessPolicy(int maxRequests, int msSleep){
        this.maxRequests = maxRequests;
        this.currentRequests = 0;
        this.msSleep = msSleep;
    }
    public PolitenessPolicy(){
        this.maxRequests = 50;      //50 requests specified in assignment
        this.currentRequests = 0;
        this.msSleep = 3000;        //3000 ms specified in assignment
    }

    /**
     * Increment the counter for number of requests
     * If counter == maxRequests, wait the specified amount of time, and reset the counter.
     */
    public void incrementRequest(){
        this.currentRequests++;
        if(currentRequests >= maxRequests){
            try
            {
                System.out.println("MaxRequests("+maxRequests+") reached: Sleeping for "+msSleep+" milliseconds...");
                Thread.sleep(msSleep);
                currentRequests = 0;
            }
            catch (InterruptedException ignore)
            {
            }
        }
    }

    public int getMaxRequests() {
        return maxRequests;
    }
    public void setMaxRequests(int maxRequests) {
        this.maxRequests = maxRequests;
    }

    public int getCurrentRequests() {
        return currentRequests;
    }
    public void setCurrentRequests(int currentRequests) {
        this.currentRequests = currentRequests;
    }
}
