package pa1;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T, N> {

    T identifier;           //Identifying feature of vertex (In this case a String, but I made it generic)
    N index;                //Location in the BFS ordered array for the graph

    List<N> incoming;       //List of indices of vertices that link to this vertex
    List<N> outgoing;       //List of indices of vertices that this vertex links to

    public Vertex(T identifier, N index){
        this.identifier = identifier;
        this.index = index;

        incoming = new ArrayList<N>();
        outgoing = new ArrayList<N>();
    }


    public T getIdentifier() {
        return identifier;
    }

    public N getIndex() {
        return index;
    }
    public void setIndex(N index) {
        this.index = index;
    }

    public List<N> getIncoming() {
        return incoming;
    }
    public void setIncoming(List<N> incoming) {
        this.incoming = incoming;
    }
    public void addIncoming(N incoming) {
        this.incoming.add(incoming);
    }

    public List<N> getOutgoing() {
        return outgoing;
    }
    public void setOutgoing(List<N> outgoing) {
        this.outgoing = outgoing;
    }
    public void addOutgoing(N outgoing) {
        this.outgoing.add(outgoing);
    }

}
