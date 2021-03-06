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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This application allows you to give some URL-s, and it will collect the X most important words of the content of the homepages.
 * You can define various instances of sorter classes (all of them implement the WordStore interface) to define the importance. You
 * can add words to skip, which will not be collected. You can define the value of X as well.
 *
 * @author laszlop
 */
public class WordTopListMultiThreading {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException, IOException
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("WordTopListRecursive application started.");
        Set<String> skipWords = new HashSet<>(Arrays.asList("an", "and", "by", "for", "if", "is", "in", "it", "of", "on", "that",
                "the", "to", "with"));
        List<URL> urlList = new ArrayList<>();
        urlList.add(new URL("https://justinjackson.ca/words.html"));
        urlList.add(new URL("http://abouthungary.hu/"));
        urlList.add(new URL("https://www.javatpoint.com/java-tutorial"));
        urlList.add(new URL("https://www.bbc.com/"));
        System.out.println("Checked URL-s: " + urlList);
        System.out.println("What do you want to check? \n 1 - most frequent words \n 2 - longest words \n "
                + "3 - words with highest vowel frequency");
        Scanner scanner = new Scanner(System.in);
        char character = scanner.next().charAt(0);
        switch (character) {
            case '1':
                checkFrequency(urlList, skipWords);
                break;
            case '2':
                checkLongest(urlList, skipWords);
                break;
            case '3':
                checkVowelFreq(urlList, skipWords);
                break;
        }
    }
    
    /**
     * Starts the hunting for the most frequent words.
     * @param urlList
     * @param skipWords
     * @throws IOException 
     */
    private static void checkFrequency(List<URL> urlList, Set<String> skipWords) throws IOException { 
        WordStore wordStoreFreq = new SorterByFrequency();
        WordCollection wordCollectionFreq = new WordCollection(urlList, wordStoreFreq, skipWords);
        wordCollectionFreq.createThreads();
        wordCollectionFreq.print(10);
    }
    
    /**
     * Starts the hunting for the longest words.
     * @param urlList
     * @param skipWords
     * @throws IOException 
     */
    private static void checkLongest(List<URL> urlList, Set<String> skipWords) throws IOException {
        WordStore wordStoreLen = new SorterByLength();
        WordCollection wordCollectionLen = new WordCollection(urlList, wordStoreLen, skipWords);
        wordCollectionLen.createThreads();
        wordCollectionLen.print(10);
    }
    
    /**
     * Starts the hunting for the words with highest frequency of vowels.
     * @param urlList
     * @param skipWords
     * @throws IOException 
     */
    private static void checkVowelFreq(List<URL> urlList, Set<String> skipWords) throws IOException {     
        WordStore wordStoreVowel = new SorterByVowelFreq();
        WordCollection wordCollectionVowel = new WordCollection(urlList, wordStoreVowel, skipWords);
        wordCollectionVowel.createThreads();
        wordCollectionVowel.print(10);
    }

}
