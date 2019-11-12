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


        //If the seed vertex has no incoming edges it will never show up in search results, so we must add a fake one
        //If the array isn't empty and the seed vertex has no incoming edges...
        if(!arr.isEmpty()){
            //The seed vertex will always be the first
            TaggedVertex<E> first = arr.get(0);

            //If the seed vertex has no incoming edges...
            if(first.getTagValue() == 0){
                //Add a fake edge
                first = new TaggedVertex<>(first.getVertexData(), 1);

                //Re-add it
                arr.set(0, first);
            }
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
