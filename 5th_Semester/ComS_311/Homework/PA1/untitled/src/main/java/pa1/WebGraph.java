package pa1;

import api.Graph;
import api.TaggedVertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebGraph<E> implements Graph {
    E seed;

    ArrayList<E> vertexList;
    HashMap<E, Vertex<E, Integer>> graph;

    public WebGraph(E seed){
        this.seed = seed;

        vertexList = new ArrayList<>();
        graph = new HashMap<>();
    }


    @Override
    public ArrayList<E> vertexData() {
        return vertexList;
    }

    @Override
    public ArrayList<TaggedVertex<E>> vertexDataWithIncomingCounts() {
        ArrayList<TaggedVertex<E>> arr = new ArrayList<>();

        for(E url : vertexList){
            Vertex<E, Integer> vertex = graph.get(url);
            TaggedVertex<E> taggedVertex = new TaggedVertex<>(url, vertex.getIncoming().size());

            arr.add(taggedVertex);
        }
        return arr;
    }

    //TODO confirm getNeighbors returns only outgoing edges
    @Override
    public List<Integer> getNeighbors(int index) {
        E url = vertexList.get(index);
        Vertex<E, Integer> vertex = graph.get(url);
        return vertex.getOutgoing();
    }

    @Override
    public List<Integer> getIncoming(int index) {
        E url = vertexList.get(index);
        Vertex<E, Integer> vertex = graph.get(url);
        return vertex.getIncoming();
    }


    public E getSeed(){
        return seed;
    }




    public List<E> getVertexList() {
        return vertexList;
    }
    public void setVertexList(ArrayList<E> vertices) {
        this.vertexList = vertices;
    }
    public int vertexListAdd(E vertex){
        vertexList.add(vertex);
        return vertexList.size()-1;
    }


    public HashMap<E, Vertex<E, Integer>> getGraph() {
        return graph;
    }
    public void setGraph(HashMap<E, Vertex<E, Integer>> graph) {
        this.graph = graph;
    }
    public void addVertexToGraph(Vertex<E, Integer> vertex){
        this.graph.put(vertex.getIdentifier(), vertex);
    }

}
