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
    int[] sub = new int[W+1]
    *Assign each index in sub to infinity

    //Store the indices of the num used for each sum here
    int[] indices = new int[W+1]

    //Sum of 0 will always be an empty set
    sub[0] = 0

    for(int i = 1; i < W; i++){
        for(int j = 0; j < set.length; j++){
            int num = set[j]

            //If the current number can fit
            if(num <= i){
                //Replace current answer if it works better
                int current = sub[i]
                int new = sub[i - num] + 1

                if(new < current){
                    //Update our records
                    sub[i] = new
                    //Save the index of the number that was used
                    indices[i] = j
                }
            }
        }
    }

    //If the last cell is unused, there is no answer
    if(sub[W] == infinity) return []

    //Otherwise use the stored indices to build a list of [w1, ..., wn]
    int[] w = new int[set.length]
    *Assign each index in w to 0

    //Backtrack through our lists
    int i = W
    while(i > 0){
        //Increment the use count (w) of each number used in the end
        int index = indices[i]
        w[index]++

        //Go to the number used before this one
        i -= set[index]
    }

    return w
}
