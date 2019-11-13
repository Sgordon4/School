import api.TaggedVertex;
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


        System.out.println("\n\nTesting mutability and reference shit");

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
        System.out.println("\n");

        System.out.println("Graph contains 'oof': "+graph.getGraph().containsKey("oof"));
        System.out.println("Graph @ 'oof': "+graph.getGraph().get("oof"));


        seed = "http://class.ece.iastate.edu/ee330/";
        seed = "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/a.html";

        System.out.println("\n\nTesting crawler");
        Crawler crawler = new Crawler(seed, 4, 6);
        crawler.crawl();
        System.out.println("Completed");


        /*
        Comparator<TaggedVertex<String>> comp = new Comparator<TaggedVertex<String>>() {
            public int compare(TaggedVertex<String> u1, TaggedVertex<String> u2) {
                return Integer.valueOf(u1.getTagValue()).compareTo(u2.getTagValue());
            }
        };
         */// Identical to below (Lambdas hot damn)
        Comparator<TaggedVertex<String>> comp = Comparator.comparingInt(TaggedVertex::getTagValue);

        System.out.println("\n\nTesting SortedList with TaggedVertex comparator");

        List<TaggedVertex<String>> sortedList = new SortedList<TaggedVertex<String>>(comp);
        TaggedVertex<String> v1 = new TaggedVertex<>("A", 1);
        TaggedVertex<String> v2 = new TaggedVertex<>("B", 2);
        TaggedVertex<String> v0 = new TaggedVertex<>("C", 0);
        TaggedVertex<String> v3 = new TaggedVertex<>("D", 12);
        TaggedVertex<String> v5 = new TaggedVertex<>("E", 9);
        TaggedVertex<String> v6 = new TaggedVertex<>("F", 6);

        sortedList.add(v1);
        sortedList.add(v2);
        sortedList.add(v0);
        sortedList.add(v3);
        sortedList.add(v5);
        sortedList.add(v6);

        for(TaggedVertex<String> v : sortedList){
            System.out.println(v.getVertexData()+": "+v.getTagValue());
        }

        System.out.println("All tests completed!!");

    }
}
