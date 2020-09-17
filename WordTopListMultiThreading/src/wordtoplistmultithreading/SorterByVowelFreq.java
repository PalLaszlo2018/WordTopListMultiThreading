/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtoplistmultithreading;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class can be used to create sorting by vowel frequency
 * @author laszlop
 */
public class SorterByVowelFreq implements WordStore {

    private final Map<String, Double> wordVowelFreq = new HashMap<>();
    private final Set<String> skipWords = new HashSet<>();
    private final Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    
    /**
     * This method adds the got word and its vowel frequency to the Map which contains the found valid words.
     * @param word 
     */

    @Override
    public synchronized void store(String word) {
        if (word.length() > 1 && !skipWords.contains(word) && !wordVowelFreq.containsKey(word)) {
            double vowelFreq = countVowels(word) / (double) word.length();
            wordVowelFreq.put(word, vowelFreq);
        }
    }
    
    /**
     * counts the vowels in the input word
     * @param word
     * @return number of vowels 
     */
    
    private int countVowels(String word) {
        int vowelCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (vowels.contains(word.charAt(i))) {
                vowelCount++;
            }
        }
        return vowelCount;
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
        System.out.println("Full frequency list: " + sortedWordsByFreq());
    }
    
     /**
     * Prints the n-sized top-list of the found words.
     * @param n 
     */

    @Override
    public void print(int n) {
        List<Map.Entry<String, Double>> sortedList = sortedWordsByFreq();
        System.out.print("The " + n + "-sized list of words with highest vowel frequency:");
        for (int i = 0; i < n; i++) {
            DecimalFormat df = new DecimalFormat("###.###");
            System.out.print(" " + sortedList.get(i).getKey() + "=" + df.format(sortedList.get(i).getValue()));
        }
        System.out.println("\n");
    }
    
     /**
     * Creates the sorted List of the entries of the Map.
     * @return 
     */
    
    private List<Map.Entry<String, Double>> sortedWordsByFreq() {
        ArrayList<Map.Entry<String, Double>> sortedList = new ArrayList<>(wordVowelFreq.entrySet());
        Collections.sort(sortedList, new WordVowelFreqComparator());
        return sortedList;
    }
}
