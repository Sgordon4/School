package pa1;

import api.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of a basic web crawler that creates a graph of some
 * portion of the world wide web.
 *
 * @author Sean Gordon (Sgordon4)
 */
public class Crawler {
    String seedUrl;
    int maxDepth;
    int maxPages;

    //HashMap<String, String> graph;
    WebGraph<String> graph;

    PolitenessPolicy policy;
    JSoupAPI jSoupAPI;

    /**
     * Constructs a Crawler that will start with the given seed url, including
     * only up to maxPages pages at distance up to maxDepth from the seed url.
     *
     * @param seedUrl
     * @param maxDepth
     * @param maxPages
     */
    public Crawler(String seedUrl, int maxDepth, int maxPages) {
        this.seedUrl = seedUrl;
        this.maxDepth = maxDepth;
        this.maxPages = maxPages;

        //graph = new HashMap<String, String>();
        graph = new WebGraph<String>(seedUrl);

        policy = new PolitenessPolicy();
        jSoupAPI= new JSoupAPI();

    }
    //Used for testing
    public Crawler(String seedUrl, int maxDepth, int maxPages, JSoupAPI jSoupAPI) {
        this.seedUrl = seedUrl;
        this.maxDepth = maxDepth;
        this.maxPages = maxPages;

        //graph = new HashMap<String, String>();
        graph = new WebGraph<String>(seedUrl);

        policy = new PolitenessPolicy();
        this.jSoupAPI = jSoupAPI;

    }


    /**
     * Creates a web graph for the portion of the web obtained by a BFS of the
     * web starting with the seed url for this object, subject to the restrictions
     * implied by maxDepth and maxPages.
     *
     * @return an instance of Graph representing this portion of the web
     */
    public Graph<String> crawl(){

        //Run the proposed modified BFS
        //To keep track of depth, I am using the null sentinel idea here:
        //https://stackoverflow.com/a/31248992/11723253
        Queue<Edge<Integer, String>> linkQueue = new LinkedList<>();
        linkQueue.add(new Edge<>(null, seedUrl));
        linkQueue.add(null);        //Add a null sentinel to mark end of level
        int depth = 0;
        int numPages = 0;

        //There will always be a null in the queue
        //Therefore there when there is only one item left (the null), we have run out of links
        while(linkQueue.size() > 1 && depth <= maxDepth){
            //Grab next link
            Edge<Integer, String> currentEdge = linkQueue.poll();

            //If we have reached the end of this depth/level
            if(currentEdge == null){
                depth++;
                linkQueue.add(null);
                continue;
            }

            String currentLink = currentEdge.getEnd();
            Vertex<String, Integer> vertex;


            //If link is not in the graph
            if(!(graph.getGraph().containsKey(currentLink))){
                //If we have reached max # of unique pages, skip it
                if(numPages == maxPages)
                    continue;

                //Add link and any links it contains to the graph and the queue

                //Add the link to the graph
                int index = graph.vertexListAdd(currentLink);
                vertex = new Vertex<>(currentLink, index);
                graph.addVertexToGraph(vertex);
                numPages++;


                //Grab all links contained in the current link
                String[] links = jSoupAPI.getLinks(currentLink, policy);

                //For every link the current page contains...
                for(String link : links) {
                    Edge<Integer, String> temp = new Edge<>(index, link);
                    linkQueue.add(temp);
                }

                System.out.println("# of links: "+ links.length);

            }
            //Otherwise grab it
            else
                vertex = graph.getGraph().get(currentLink);


            //Update incoming and outgoing edges --------------------------------------


            //If start is null, this is the starting link and there is no incoming node
            //Otherwise update this vertex's incoming edges...
            Integer start = currentEdge.getStart();
            Integer index = vertex.getIndex();

            if(start != null) {
                vertex.addIncoming(start);

                //Update the predecessor's outgoing edges...
                String predURL = graph.getVertexList().get(start);
                Vertex<String, Integer> pred = graph.getGraph().get(predURL);

                pred.addOutgoing(index);
            }
        }

        return graph;
    }
}