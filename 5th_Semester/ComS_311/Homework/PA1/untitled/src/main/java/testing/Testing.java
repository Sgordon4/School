package testing;

import pa1.Crawler;
import pa1.PolitenessPolicy;
import pa1.Vertex;
import pa1.WebGraph;

import java.io.IOException;
import java.util.ArrayList;
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
        crawler.crawl();
        System.out.printf("Completed");

    }
}
