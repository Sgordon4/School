package pa1;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

//An apache.commons TreeList would be more efficient here as this
//list is designed for constant additions at random indices
public class ArraySortedList<T> implements SortedList<T> {

    private ArrayList<T> array;
    private Comparator<T> comparator;

    public ArraySortedList(Comparator<T> comparator) {
        array = new ArrayList<T>();
        assert comparator != null;
        this.comparator = comparator;
    }


    /**
     * Insert an eleemnt to the list keeping the list sorted.
     * Using "binary search" to look for the right place.
     */
    @Override
    public void add(T e) {
        int left, right, mid;


        left = 0;
        right = array.size();


        while(left< right)  {
            mid = (left + right)/2;
            int result = comparator.compare(array.get(mid), e);


            if(result > 0) { //If e is lower
                right = mid;
            } else { //If e is higher
                left = mid + 1;
            }
        }

        array.add(left, e);

    }


    //@Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private int index = 0;


        @Override
        public boolean hasNext() {
            return index < array.size();
        }

        @Override
        public T next() {
            return array.get(index++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }
}

//Interface
interface  SortedList<T> {

    /**
     * Inserts an element to the list keeping the list sorted.
     */
    public void add(T element);

}
