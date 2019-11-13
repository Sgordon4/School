import api.TaggedVertex;
import pa1.Index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexTest {
    public static void main(String[] args){

        //Set up a fake graph with sample webpages
        List<String> vertexList = new ArrayList<>(Arrays.asList(
                "http://help.websiteos.com/websiteos/example_of_a_simple_html_page.htm",
                "https://www.le.ac.uk/oerresources/bdra/html/page_07.htm",
                "https://www.le.ac.uk/oerresources/bdra/html/page_08.htm",
                "https://www.le.ac.uk/oerresources/bdra/html/page_09.htm"
        ));

        List<TaggedVertex<String>> fakeGraph = new ArrayList<TaggedVertex<String>>(Arrays.asList(
            new TaggedVertex<String>("http://help.websiteos.com/websiteos/example_of_a_simple_html_page.htm", 1),
            new TaggedVertex<String>("https://www.le.ac.uk/oerresources/bdra/html/page_07.htm", 2),
            new TaggedVertex<String>("https://www.le.ac.uk/oerresources/bdra/html/page_08.htm", 3),
            new TaggedVertex<String>("https://www.le.ac.uk/oerresources/bdra/html/page_09.htm", 4)
            ));

        Index index = new Index(fakeGraph);
        index.makeIndex();
        System.out.println("Index build completed");

        System.out.println("\nSearching for single word");
        List<TaggedVertex<String>> searchResults = index.search("tags");

        for(TaggedVertex<String> v : searchResults){
            System.out.println(v.getTagValue()+": "+ v.getVertexData());
        }

        System.out.println("\nSearching for words AND");
        //List<TaggedVertex<String>> searchResults2 = index.searchWithAnd("tags", "document");
        List<TaggedVertex<String>> searchResults2 = index.searchWithAnd("tags", "associated");

        for(TaggedVertex<String> v : searchResults2){
            System.out.println(v.getTagValue()+": "+ v.getVertexData());
        }

        System.out.println("\nSearching for words OR");
        List<TaggedVertex<String>> searchResults3 = index.searchWithOr("viewing", "associated");

        for(TaggedVertex<String> v : searchResults3){
            System.out.println(v.getTagValue()+": "+ v.getVertexData());
        }

        System.out.println("\nSearching for words NOT");
        List<TaggedVertex<String>> searchResults4 = index.searchAndNot("tags", "associated");

        for(TaggedVertex<String> v : searchResults4){
            System.out.println(v.getTagValue()+": "+ v.getVertexData());
        }
    }
}



/*
"tags" -> {SortedList@2345}  size = 4
    key = "tags"
    value = {SortedList@2345}  size = 4
        0 = {TaggedVertex@2606}
            vertexData = "https://www.le.ac.uk/oerresources/bdra/html/page_08.htm"
            tagValue = 9
        1 = {TaggedVertex@2607}
            vertexData = "https://www.le.ac.uk/oerresources/bdra/html/page_09.htm"
            tagValue = 4
        2 = {TaggedVertex@2608}
            vertexData = "https://www.le.ac.uk/oerresources/bdra/html/page_07.htm"
            tagValue = 2
        3 = {TaggedVertex@2609}
            vertexData = "http://help.websiteos.com/websiteos/example_of_a_simple_html_page.htm"
            tagValue = 2



"viewing" -> {SortedList@2309}  size = 2
    key = "viewing"
    value = {SortedList@2309}  size = 2
         0 = {TaggedVertex@2596}
            vertexData = "https://www.le.ac.uk/oerresources/bdra/html/page_09.htm"
            tagValue = 4
         1 = {TaggedVertex@2597}
            vertexData = "https://www.le.ac.uk/oerresources/bdra/html/page_07.htm"
            tagValue = 2



"associated" -> {SortedList@2399}  size = 2
    key = "associated"
    value = {SortedList@2399}  size = 2
        0 = {TaggedVertex@2602}
            vertexData = "https://www.le.ac.uk/oerresources/bdra/html/page_08.htm"
            tagValue = 3
        1 = {TaggedVertex@2603}
            vertexData = "https://www.le.ac.uk/oerresources/bdra/html/page_07.htm"
            tagValue = 2

 */