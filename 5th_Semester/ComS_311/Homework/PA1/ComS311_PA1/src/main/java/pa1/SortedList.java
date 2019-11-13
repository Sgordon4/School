package pa1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//An apache.commons TreeList would be more efficient here as this
//list is designed for constant additions at random indices
public class SortedList<T> extends ArrayList<T> {

    private Comparator<T> comparator;

    public SortedList(Comparator<T> comparator) {
        super();
        assert comparator != null;
        this.comparator = comparator;
    }

    /**
     * Uses binary search to keep the list sorted.
     * Sorts in descending order based on the comparator passed on initialization.
     *
     * @param obj Object to add to the list
     * @return true
     */
    @Override
    public boolean add(T obj){
        int index = Collections.binarySearch(this, obj, comparator.reversed());
        if (index < 0) index = ~index;
        super.add(index, obj);
        return true;
    }
}

