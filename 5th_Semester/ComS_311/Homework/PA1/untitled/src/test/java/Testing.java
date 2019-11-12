import pa1.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Testing {
    public static void main(String[] args) throws IOException {

        PolitenessPolicy policy = new PolitenessPolicy();
        String seed = "https://en.wikipedia.org/wiki/Gouraud_shading";

        /*
        String[] arr = JSoupAPI.getLinks(seed, policy);

        System.out.println("Array length: ");
        System.out.println(arr.length);
        */

        WebGraph<String> graph = new WebGraph<String>(seed);

        Vertex<String, Integer> vertex = new Vertex<>(seed, 0);
        vertex.addIncoming(-1);
        graph.addVertexToGraph(vertex);

        List<Integer> list = new ArrayList<>();


        graph.getGraph().get(vertex.getIdentifier()).addIncoming(1);
        list = (graph.getGraph().get(vertex.getIdentifier()).getIncoming());

        System.out.printf("Trial 1: ");
        for(Integer i : list){
            System.out.print(i+", ");
        }
        System.out.println();


        graph.getGraph().get(vertex.getIdentifier()).addIncoming(2);
        list = (graph.getGraph().get(vertex.getIdentifier()).getIncoming());

        System.out.printf("Trial 2: ");
        for(Integer i : list){
            System.out.print(i+", ");
        }
        System.out.println();


        Vertex<String, Integer> vertex2 = graph.getGraph().get(vertex.getIdentifier());
        vertex2.addIncoming(3);
        list = (graph.getGraph().get(vertex.getIdentifier()).getIncoming());

        System.out.printf("Trial 3: ");
        for(Integer i : list){
            System.out.print(i+", ");
        }
        System.out.println();


        list = vertex.getIncoming();

        System.out.printf("Trial 4: ");
        for(Integer i : list){
            System.out.print(i+", ");
        }
        System.out.println();


        System.out.println(graph.getGraph().get("oof"));
        System.out.println(graph.getGraph().containsKey("oof"));


        Crawler crawler = new Crawler("http://class.ece.iastate.edu/ee330/", 3, 10);
        //crawler.crawl();
        System.out.printf("Completed");


        System.out.println("Testing sorted list \n\n\n");

        ComparableTaggedVertex<String> comp1 = new ComparableTaggedVertex<>("A", 1);
        ComparableTaggedVertex<String> comp2 = new ComparableTaggedVertex<>("A", 2);
        System.out.println(comp1.compareTo(comp1));
        System.out.println(comp1.compareTo(comp2));
        System.out.println(comp2.compareTo(comp1));


        //SortedList<ComparableTaggedVertex<String>> sortedList = new SortedList<>();
        SortedList<Integer> arr = new ArraySortedList<Integer>(new Comparator<ComparableTaggedVertex<String>>() {
            @Override
            public int compare( o1, T o2) {
                return o1.compareTo(o2);
            }
        });


    }
}
