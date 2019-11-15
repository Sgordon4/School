package pa1;

import java.util.*;

import api.TaggedVertex;

/**
 * Implementation of an inverted index for a web graph.
 *
 * @author Sean Gordon (Sgordon4)
 */
public class Index {

    HashMap<String, SortedList<TaggedVertex<String>>> index;
    List<TaggedVertex<String>> urls;

    PolitenessPolicy policy;
    JSoupAPI jSoupAPI;

    Comparator<TaggedVertex<String>> comparator;




    /**
     * Constructs an index from the given list of urls.  The
     * tag value for each url is the indegree of the corresponding
     * node in the graph to be indexed.
     *
     * @param urls information about graph to be indexed
     */
    public Index(List<TaggedVertex<String>> urls) {
        index = new HashMap<>();
        this.urls = urls;

        policy = new PolitenessPolicy();
        jSoupAPI = new JSoupAPI();

        //Comparator for TaggedVertices
        comparator = Comparator.comparingInt(TaggedVertex::getTagValue);
    }
    //Used for testing
    public Index(List<TaggedVertex<String>> urls, JSoupAPI jSoupAPI) {
        index = new HashMap<>();
        this.urls = urls;

        policy = new PolitenessPolicy();
        this.jSoupAPI = jSoupAPI;

        //Comparator for TaggedVertices
        comparator = Comparator.comparingInt(TaggedVertex::getTagValue);
    }

    /**
     * Creates the index.
     */
    public void makeIndex() {

        //For every vertex in our graph...
        for(TaggedVertex<String> vertex : urls){
            //Grab the page data
            String url = vertex.getVertexData();
            int indegree = vertex.getTagValue();

            HashMap<String, Integer> words = jSoupAPI.getWords(url, policy);
            Set<Map.Entry<String, Integer>> entrySet =  words.entrySet();

            //For every word we recieved...
            for(Map.Entry<String, Integer> entry : entrySet){
                String word = entry.getKey();
                Integer freq = entry.getValue();

                //Calculate the weight
                int weight = freq * indegree;

                //Make sure the word is in the index
                index.putIfAbsent(word, new SortedList<>(comparator));

                //Store this information in the index with the word as a key
                //I've created SortedList to automatically sort based on a binary search,
                //so the pages will automatically be in order based on frequency * indegree.
                TaggedVertex<String> taggedVertex = new TaggedVertex<>(url, weight);
                index.get(word).add(taggedVertex);

            }
        }
    }

    /**
     * Searches the index for pages containing keyword w.  Returns a list
     * of urls ordered by ranking (largest to smallest).  The tag
     * value associated with each url is its ranking.
     * The ranking for a given page is the number of occurrences
     * of the keyword multiplied by the indegree of its url in
     * the associated graph.  No pages with rank zero are included.
     *
     * @param w keyword to search for
     * @return ranked list of urls
     */
    public List<TaggedVertex<String>> search(String w) {
        //The pages for a word are pre-sorted according to the word's frequency * indegree,
        //so we just have to grab the list of pages
        return index.get(w);
    }


    /**
     * Searches the index for pages containing both of the keywords w1
     * and w2.  Returns a list of qualifying
     * urls ordered by ranking (largest to smallest).  The tag
     * value associated with each url is its ranking.
     * The ranking for a given page is the number of occurrences
     * of w1 plus number of occurrences of w2, all multiplied by the
     * indegree of its url in the associated graph.
     * No pages with rank zero are included.
     *
     * @param w1 first keyword to search for
     * @param w2 second keyword to search for
     * @return ranked list of urls
     */
    public List<TaggedVertex<String>> searchWithAnd(String w1, String w2) {

        //The pages for a word are pre-sorted according to the word's frequency, so we just have to grab
        //the lists of pages
        List<TaggedVertex<String>> pageList1 = index.get(w1);
        List<TaggedVertex<String>> pageList2 = index.get(w2);

        //To decrease time complexity, throw all urls in pageList2 into a hashmap
        HashMap<String, Integer> mapList2 = new HashMap<>();
        for(TaggedVertex<String> vertex : pageList2){
            mapList2.put(vertex.getVertexData(), vertex.getTagValue());
        }


        List<TaggedVertex<String>> searchResults = new SortedList<>(comparator);

        //For all pages linked to w1, check for the same page linked to w2
        for(TaggedVertex<String> page1 : pageList1){
            String url = page1.getVertexData();

            if(!mapList2.containsKey(url))
                continue;

            //Add the weights together (weight = freq * indegree)
            int weight1 = page1.getTagValue();
            int weight2 = mapList2.get(url);

            //Add it to the list
            TaggedVertex<String> v = new TaggedVertex<>(url, weight1 + weight2);
            searchResults.add(v);
        }

        return searchResults;
    }

    /**
     * Searches the index for pages containing at least one of the keywords w1
     * and w2.  Returns a list of qualifying
     * urls ordered by ranking (largest to smallest).  The tag
     * value associated with each url is its ranking.
     * The ranking for a given page is the number of occurrences
     * of w1 plus number of occurrences of w2, all multiplied by the
     * indegree of its url in the associated graph.
     * No pages with rank zero are included.
     *
     * @param w1 first keyword to search for
     * @param w2 second keyword to search for
     * @return ranked list of urls
     */
    public List<TaggedVertex<String>> searchWithOr(String w1, String w2) {
        //The pages for a word are pre-sorted according to the word's frequency, so we just have to grab
        //the lists of pages
        List<TaggedVertex<String>> pageList1 = index.get(w1);
        List<TaggedVertex<String>> pageList2 = index.get(w2);

        //To decrease time complexity, throw all urls in pageList2 into a hashmap
        HashMap<String, Integer> mapList2 = new HashMap<>();
        for(TaggedVertex<String> vertex : pageList2){
            mapList2.put(vertex.getVertexData(), vertex.getTagValue());
        }


        List<TaggedVertex<String>> searchResults = new SortedList<>(comparator);

        //For all pages linked to w1, check for the same page linked to w2
        for(TaggedVertex<String> page1 : pageList1){
            String url = page1.getVertexData();

            int weight1 = page1.getTagValue();
            int weight2 = 0;

            if(mapList2.containsKey(url)){
                //Remove the used url from the hashmap so it isn't counted twice later, and grab the weight
                weight2 = mapList2.remove(url);
            }

            //Add it to the list
            TaggedVertex<String> v = new TaggedVertex<>(url, weight1 + weight2);
            searchResults.add(v);

        }

        //For everything left in the hashmap
        Set<Map.Entry<String, Integer>> entrySet = mapList2.entrySet();

        for(Map.Entry<String, Integer> entry : entrySet){
            //Add it to the list
            String url = entry.getKey();
            int weight2 = entry.getValue();

            TaggedVertex<String> v = new TaggedVertex<>(url, weight2);
            searchResults.add(v);
        }

        return searchResults;
    }

    /**
     * Searches the index for pages containing keyword w1
     * but NOT w2.  Returns a list of qualifying urls
     * ordered by ranking (largest to smallest).  The tag
     * value associated with each url is its ranking.
     * The ranking for a given page is the number of occurrences
     * of w1, multiplied by the
     * indegree of its url in the associated graph.
     * No pages with rank zero are included.
     *
     * @param w1 first keyword to search for
     * @param w2 second keyword to search for
     * @return ranked list of urls
     */
    public List<TaggedVertex<String>> searchAndNot(String w1, String w2) {
        //The pages for a word are pre-sorted according to the word's frequency, so we just have to grab
        //the lists of pages
        List<TaggedVertex<String>> pageList1 = index.get(w1);
        List<TaggedVertex<String>> pageList2 = index.get(w2);

        //To decrease time complexity, throw all urls in pageList2 into a hashmap
        HashMap<String, Integer> mapList2 = new HashMap<>();
        for(TaggedVertex<String> vertex : pageList2){
            mapList2.put(vertex.getVertexData(), vertex.getTagValue());
        }


        List<TaggedVertex<String>> searchResults = new SortedList<>(comparator);

        //For all pages linked to w1, check for the same page linked to w2
        for(TaggedVertex<String> page1 : pageList1){
            String url = page1.getVertexData();

            if(mapList2.containsKey(url))
                continue;

            //Add the weight to the list (weight = freq * indegree)
            int weight1 = page1.getTagValue();

            TaggedVertex<String> v = new TaggedVertex<>(url, weight1);
            searchResults.add(v);
        }

        return searchResults;
    }
}
