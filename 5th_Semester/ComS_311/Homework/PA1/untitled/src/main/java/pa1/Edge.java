package pa1;

public class Edge<S, E> {
    S start;
    E end;

    public Edge(S start, E end){
        this.start = start;
        this.end = end;
    }


    public S getStart() {
        return start;
    }
    public E getEnd() {
        return end;
    }
}
