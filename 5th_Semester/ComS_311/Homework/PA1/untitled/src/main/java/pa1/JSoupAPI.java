package pa1;

import api.Util;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSoupAPI {

    /**
     * Helper method to pull all useful links from a webpage.
     * Useful links are defined by those that are a valid MIME type, and that
     * don't match the ignore-criteria given in Util.
     * Majority of code pulled from JSoupTest.
     *
     * @param url Webpage to search for useful links
     * @return List of all useful links contained in a webpage
     */
    public static String[] getLinks(String url, PolitenessPolicy polPolicy) throws IOException {

        System.out.println("Fetching " + url);
        polPolicy.incrementRequest();
        Document doc = Jsoup.connect(url).get();

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
}
