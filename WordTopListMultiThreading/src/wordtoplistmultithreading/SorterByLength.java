/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class can be used to create sorting by the length of the word
 * @author laszlop
 */
public class SorterByLength implements WordStore {

    private final Set<String> words = new HashSet<>();
    private final Set<String> skipWords = new HashSet<>();
    
    /**
     * This method adds the got word to the Set which contains the found valid words.
     * @param word 
     */

    @Override
    public void store(String word) {
        if (word.length() > 1 && !skipWords.contains(word)) {
            words.add(word);
        }
    }
    
    /**
     * This method adds the got word to the Set which contains the words to be ignored.
     * @param word 
     */

    @Override
    public void addSkipWord(String word) {
        skipWords.add(word);
    }
    
    /**
     * Prints the full list of the found words.
     */

    @Override
    public void print() {
        System.out.println("Full wordlength list: " + sortedWordsByLength());
    }
    
    /**
     * Prints the n-sized top-list of the found words.
     * @param n 
     */

    @Override
    public void print(int n) {
        List<String> sortedList = sortedWordsByLength();
        System.out.print("The " + n + " longest words:");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + sortedList.get(i));
        }
        System.out.println("");
    }
    
    /**
     * Creates the sorted List of the entries of the Map.
     * @return the sorted List
     */

    private List<String> sortedWordsByLength() {
        ArrayList<String> sortedList = new ArrayList<>(words);
        Collections.sort(sortedList, new WordLenComparator());
        return sortedList;
    }

}
