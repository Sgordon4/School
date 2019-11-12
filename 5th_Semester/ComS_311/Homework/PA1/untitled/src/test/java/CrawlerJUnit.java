import api.Graph;
import api.TaggedVertex;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import pa1.Crawler;
import pa1.JSoupAPI;
import pa1.PolitenessPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CrawlerJUnit {

    @Mock
    JSoupAPI jsoupMock;

    List<String> vertexList;
    List<String[]> internet;

    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);

        //Set up our "internet" with its linked webpages
        //This is pulled directly from the PDF: 4.1 Crawler
        vertexList = new ArrayList<>(Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
        ));
        internet = new ArrayList<>();
        internet.add(0, new String[]{"B", "C", "D"});       //A
        internet.add(1, new String[]{"C", "I", "J"});       //B
        internet.add(2, new String[]{"E", "F", "B", "D"});  //C
        internet.add(3, new String[]{"G", "H", "A"});       //D
        internet.add(4, new String[]{"A"});                 //E
        internet.add(5, new String[]{});                    //F
        internet.add(6, new String[]{});                    //G
        internet.add(7, new String[]{});                    //H
        internet.add(8, new String[]{});                    //I
        internet.add(9, new String[]{});                    //J

        //Replace actual JSoupAPI calls to real webpages with our fake stuff
        when(jsoupMock.getLinks(anyString(), any(PolitenessPolicy.class))).thenAnswer(
                (Answer<String[]>) invoc -> getFakeLinkLists((String) invoc.getArguments()[0]));
    }

    @Test
    public void crawlerTestDepth6(){

        String seed = "A";
        Crawler crawler = new Crawler(seed, 4, 6, jsoupMock);

        List<String> finalGraph = new ArrayList<>(Arrays.asList(
                "A", "B", "C", "D", "I", "J"
        ));


        Graph<String> graph = crawler.crawl();
        assertEquals(graph.vertexData(), finalGraph);

        //Check incoming edges
        assertEquals(graph.getIncoming(0), new ArrayList<>(Arrays.asList(
                3
        )));    //A incoming edges should be D
        assertEquals(graph.getIncoming(1), new ArrayList<>(Arrays.asList(
                0, 2
        )));    //B incoming edges should be A, C
        assertEquals(graph.getIncoming(2), new ArrayList<>(Arrays.asList(
                0, 1
        )));    //C incoming edges should be A, B
        assertEquals(graph.getIncoming(3), new ArrayList<>(Arrays.asList(
                0, 2
        )));    //D incoming edges should be A, C
        assertEquals(graph.getIncoming(4), new ArrayList<>(Arrays.asList(
                1
        )));    //I incoming edges should be B
        assertEquals(graph.getIncoming(5), new ArrayList<>(Arrays.asList(
                1
        )));    //J incoming edges should be B


        //Check outgoing edges
        assertEquals(graph.getNeighbors(0), new ArrayList<>(Arrays.asList(
                1, 2, 3
        )));    //A outgoing edges should be B, C, D
        assertEquals(graph.getNeighbors(1), new ArrayList<>(Arrays.asList(
                2, 4, 5
        )));    //B outgoing edges should be C, I, J
        assertEquals(graph.getNeighbors(2), new ArrayList<>(Arrays.asList(
                1, 3
        )));    //C outgoing edges should be B, D
        assertEquals(graph.getNeighbors(3), new ArrayList<>(Arrays.asList(
                0
        )));    //D outgoing edges should be A
        assertEquals(graph.getNeighbors(4), new ArrayList<>(Arrays.asList(

        )));    //I outgoing edges should be []
        assertEquals(graph.getNeighbors(5), new ArrayList<>(Arrays.asList(

        )));    //J outgoing edges should be []

        System.out.println("Tagged vertex shit: ");
        ArrayList<TaggedVertex<String>> tagged = graph.vertexDataWithIncomingCounts();
        for(TaggedVertex<String> v : tagged){
            System.out.print(v.getTagValue()+", ");
        }
        System.out.println("\n\n\n");


    }

    @Test
    public void crawlerTestDepth1(){

        String seed = "A";
        Crawler crawler = new Crawler(seed, 1, 6, jsoupMock);

        List<String> finalGraph = new ArrayList<>(Arrays.asList(
                "A", "B", "C", "D"
        ));


        Graph<String> graph = crawler.crawl();
        assertEquals(graph.vertexData(), finalGraph);

        //Check incoming edges (As depth = 1, they should only be from A)
        assertEquals(graph.getIncoming(0), new ArrayList<>(Arrays.asList(

        )));    //A incoming edges should be []
        assertEquals(graph.getIncoming(1), new ArrayList<>(Arrays.asList(
                0
        )));    //B incoming edges should be A
        assertEquals(graph.getIncoming(2), new ArrayList<>(Arrays.asList(
                0
        )));    //C incoming edges should be A
        assertEquals(graph.getIncoming(3), new ArrayList<>(Arrays.asList(
                0
        )));    //D incoming edges should be A


        //Check outgoing edges
        assertEquals(graph.getNeighbors(0), new ArrayList<>(Arrays.asList(
                1, 2, 3
        )));    //A outgoing edges should be B, C, D
        assertEquals(graph.getNeighbors(1), new ArrayList<>(Arrays.asList(

        )));    //B outgoing edges should be []
        assertEquals(graph.getNeighbors(2), new ArrayList<>(Arrays.asList(

        )));    //C outgoing edges should be []
        assertEquals(graph.getNeighbors(3), new ArrayList<>(Arrays.asList(

        )));    //D outgoing edges should be []


        System.out.println("Tagged vertex shit: ");
        ArrayList<TaggedVertex<String>> tagged = graph.vertexDataWithIncomingCounts();
        for(TaggedVertex<String> v : tagged){
            System.out.print(v.getTagValue()+", ");
        }
        System.out.println("\n\n\n");
    }


    /**
     * Hacky for loop to return our fake internet links.
     * Given a 'url', return a list of all outgoing urls.
     * @param link url to return outgoing edges for
     * @return String[] of outgoing edges
     */
    public String[] getFakeLinkLists(String link){
        System.out.println("Asking for links from "+link);

        //Do a search through our tiny setup to find the rest of the links
        int i;
        for(i = 0; i < vertexList.size(); i++){
            if(vertexList.get(i).equals(link))
                break;
        }

        if(i == vertexList.size()){
            System.out.println("Asked for string not within vertexList!");
            return new String[]{};
        }

        return internet.get(i);
    }
}
