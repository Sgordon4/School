package pa1;

import api.Util;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class JSoupAPI {

    /**
     * Helper method to pull all useful links from a webpage.
     * Useful links are defined by those that are a valid MIME type, and that
     * don't match the ignore-criteria given in Util.
     * Majority of code pulled from JSoupTest.
     *
     * @param url Webpage to search for useful links
     * @param polPolicy Politeness policy to limit load on servers
     * @return List of all useful links contained in a webpage
     */
    public String[] getLinks(String url, PolitenessPolicy polPolicy){

        System.out.println("Fetching " + url);
        polPolicy.incrementRequest();

        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        }
        catch (IOException e){
            System.out.println("--IOException. Link get failed");
            return new String[] {};
        }

        List<String> validLinks = new ArrayList<>();


        // get the links from the document text
        Elements links = doc.select("a[href]");
        for (Element link : links)
        {
            // get the href in the form of an absolute url
            String v = link.attr("abs:href");
            System.out.println("Found: " + v);

            // make sure it's a non-bookmark link with a valid MIME type
            Document temp = null;
            if (!Util.ignoreLink(url, v))
            {
                try{
                    polPolicy.incrementRequest();
                    temp = Jsoup.connect(v).get();

                    //If successful, add it to the list...
                    validLinks.add(v);
                }
                catch (UnsupportedMimeTypeException e){
                    System.out.println("--unsupported document type, do nothing");
                }
                catch (HttpStatusException e){
                    System.out.println("--invalid link, do nothing");
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else{
                System.out.println("--ignore");
            }
        }
        System.out.println();

        //Turn resulting list into an array
        String validLinksRet[] = new String[validLinks.size()];
        validLinksRet = validLinks.toArray(validLinksRet);

        return validLinksRet;
    }



    /**
     * Helper method to pull all valid words from a webpage.
     * Useful words are defined in the PDF as punctuation stripped text that isn't a stop word.
     *
     * @param url Webpage to parse for words
     * @param polPolicy Politeness policy to limit load on servers (probably not needed here)
     * @return List of all valid words in a webpage
     */
    public HashMap<String, Integer> getWords(String url, PolitenessPolicy polPolicy){
        System.out.println("Fetching " + url);
        polPolicy.incrementRequest();

        //Get the body text from the webpage
        String bodyText;
        try {
            bodyText = Jsoup.connect(url).get().body().text();
        }
        catch (IOException e){
            System.out.println("--IOException. Link get failed");
            return new HashMap<String, Integer>();
        }

        //Parse the body text into individual words
        HashMap<String, Integer> words = new HashMap<>();
        Scanner scanner = new Scanner(bodyText);

        while(scanner.hasNext()){
            String word = scanner.next();

            //Check if the word is not one of the droids we're looking for -------

            //Strip the word of punctuation, and if it is a stop word, skip it
            word = Util.stripPunctuation(word);
            if(Util.isStopWord(word))
                continue;

            //Add the word to the pile
            words.putIfAbsent(word, 0);
            words.put(word, words.get(word) + 1);
        }
        scanner.close();

        //Return the resulting HashMap
        return words;
    }
}


/*
public String[] getWords(String url, PolitenessPolicy polPolicy){
        System.out.println("Fetching " + url);
        polPolicy.incrementRequest();

        //Get the body text from the webpage
        String bodyText;
        try {
            bodyText = Jsoup.connect(url).get().body().text();
        }
        catch (IOException e){
            System.out.println("--IOException. Link get failed");
            return new String[] {};
        }

        //Parse the body text into individual words
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(bodyText);

        while(scanner.hasNext()){
            String word = scanner.next();

            //Check if the word is not one of the droids we're looking for -------

            //Strip the word of punctuation
            word = Util.stripPunctuation(word);

            //If it is a stop word, skip it
            if(Util.isStopWord(word))
                continue;

            //Add the word to the list
            words.add(word);
        }
        scanner.close();

        //Turn resulting list into an array and return it
        String validWords[] = new String[words.size()];
        validWords = words.toArray(validWords);

        return validWords;
    }
 */
