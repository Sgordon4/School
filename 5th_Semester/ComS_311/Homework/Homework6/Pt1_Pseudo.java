int[] doThing(set, W){
    int[] list = new int[set.length]
    return combo(set, W, list, 0)
}

/**
 * list is to keep track of the weights used at each index
 * listSum is the sum of the contents of list
 */
int[] combo(int[] set, int remaining, int[] list, int listSum){

    if(remainder < 0) return -1     //Current subset won't work
    if(remainder == 0) return 0

    int min = infinity
    for(int i = 0; i < set; i++){
        int num = set[i]

        int res = combo(set, remaining - num, list, listSum)

        if(res == -1) continue
        if(res < min) {
            min = 1 + res
        }
    }

}






int[] doThing(set, W){
    int[] list = new int[set.length]
    return combo(set, W, list)
}

/**
 * list is to keep track of the weights used at each index
 * this also isn't finished and won't work anyway lol
 */
int[] combo(int[] set, int remaining, int[] list){

    if(remainder < 0) return null   //Current subset won't work
    if(remainder == 0) return new int[set.length]   //Empty list

    int min = infinity
    int minIndex = 0
    for(int i = 0; i < set.length; i++){
        int num = set[i]

        int[] res = combo(set, remaining - num, list)

        if(res == null) continue
        if(res < min) {
            min = 1 + res
            minIndex = i
        }
    }

}

//I don't need to finish this, already have the recurrence:
Recurrence:
combo = null if remainder < 0
combo = [] if remainder == 0
combo = min( from(i = 0->n) {combo(set, remaining - set[i], list)} )



int[] comboIter(set, W){

    //Store sub problem answers here
    int[] arr = new int[W+1]
    *Assign each index in arr to infinity

    //Sum of 0 will always be an empty set
    arr[0] = 0

    for(int i = 1; i < W; i++){
        for(int j = 0; j < set.length; j++){
            int num = set[j]

            //If the current number can fit
            if(num <= i){
                //Test if this will add less to
                num
            }
        }
    }

}
