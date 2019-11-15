package example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import api.Util;

/**
 * Example illustrating basic usage of the jsoup library.
 * This example finds all links in a given url and 
 * identifies links that should be ignored (according to 
 * Util.ignoreLink) and those that
 * link to non-html documents.
 */
public class JSoupTest
{

  public static void main(String[] args) throws IOException
  {
    
    //String url = "http://web.cs.iastate.edu/~smkautz";
    String url = "https://en.wikipedia.org/wiki/Gouraud_shading";
      
    System.out.println("Fetching " + url);
    Document doc = Jsoup.connect(url).get();    
    
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
        try
        {
          temp = Jsoup.connect(v).get();
        }
        catch (UnsupportedMimeTypeException e)
        {
          System.out.println("--unsupported document type, do nothing");
        } 
        catch (HttpStatusException  e)
        {
          System.out.println("--invalid link, do nothing");
        }
      }
      else
      {
        System.out.println("--ignore");
      }

    }
    

    System.out.println();
    
    
    System.out.println("Full document body text excluding links:");
    String text = doc.body().text();
    System.out.println(text);
  }

}
