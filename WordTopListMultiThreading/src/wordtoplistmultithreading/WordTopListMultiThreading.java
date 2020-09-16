/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultithreading;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This application allows you to give some URL-s, and it will collect the X most important words of the content of the homepages.
 * You can define various instances of sorter classes (all of them implement the WordStore interface) to define the importance.
 * You can add words to skip, which will not be collected. You can define the value of X as well.
 * @author laszlop
 */
public class WordTopListMultiThreading {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException, IOException
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("WordTopListRecursive application started.");
        List<URL> urlList = new ArrayList<>();
        urlList.add(new URL("https://justinjackson.ca/words.html"));
//        urlList.add(new URL("http://abouthungary.hu/"));
//        urlList.add(new URL("https://www.javatpoint.com/java-tutorial"));
//        urlList.add(new URL("https://www.bbc.com/"));
        System.out.println("Checked URL-s: " + urlList);
        
        WordStore wordStoreFreq = new SorterByFrequency();
              
        WordCollector wordCollectorFreq = new WordCollector(urlList, wordStoreFreq);
        wordCollectorFreq.addSkipWord("an");
        wordCollectorFreq.addSkipWord("and");
        wordCollectorFreq.addSkipWord("by");
        wordCollectorFreq.addSkipWord("for");
        wordCollectorFreq.addSkipWord("if");
        wordCollectorFreq.addSkipWord("in");
        wordCollectorFreq.addSkipWord("is");
        wordCollectorFreq.addSkipWord("it");
        wordCollectorFreq.addSkipWord("of");
        wordCollectorFreq.addSkipWord("on");
        wordCollectorFreq.addSkipWord("that");
        wordCollectorFreq.addSkipWord("the");
        wordCollectorFreq.addSkipWord("to");
        wordCollectorFreq.addSkipWord("with");
        
        wordCollectorFreq.processURLs();
        wordCollectorFreq.print(10);
        
        System.out.println("\n");
        
        WordStore wordCollectorLen = new SorterByLength();     
        WordCollector wordStoringLen = new WordCollector(urlList, wordCollectorLen);
        wordStoringLen.addSkipWord("an");
        
        wordStoringLen.processURLs();
        wordStoringLen.print(10);
        
        System.out.println("\n");
        
        WordStore wordCollectorVowel = new SorterByVowelFreq();     
        WordCollector wordStoringVowel = new WordCollector(urlList, wordCollectorVowel);
        wordStoringVowel.addSkipWord("an");
         
        wordStoringVowel.processURLs();
        wordStoringVowel.print(10);
    }

}
