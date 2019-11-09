/* Use merge sort: O(n)
 * 1. Create a HashMap to record all the positions of every distinct word
 * 2. Use merge sort to find the shortest distence, given the positions list of the two words
 */

import java.util.*;

public class Solution{
    HashMap<String, List<Integer>> indexDictionary; 
    
    public Solution(String[] words) {
        indexDictionary = new HashMap<String, List<Integer>>();
        for(int index = 0; index < words.length; ++index){
            String word = words[index];
            if(!indexDictionary.containsKey(word)){
                indexDictionary.put(word, new ArrayList<Integer>());
            }
            indexDictionary.get(word).add(index);
        }
    }
    
    private int getShortest(List<Integer> word1Indexs, List<Integer> word2Indexs){
        int word1Index = 0;
        int word2Index = 0;
        int minDistance = Integer.MAX_VALUE;
        while(word1Index < word1Indexs.size() && word2Index < word2Indexs.size()){
            minDistance = Math.min(minDistance, Math.abs(word1Indexs.get(word1Index) - word2Indexs.get(word2Index)));
            if(word1Indexs.get(word1Index) > word2Indexs.get(word2Index)){
                word2Index++;
            }else{
                word1Index++;
            }
        }
        return minDistance;
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> word1Indexs = indexDictionary.get(word1);
        List<Integer> word2Indexs = indexDictionary.get(word2);
        return getShortest(word1Indexs, word2Indexs);
    }
 
    public static void main(String[] args){
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        Solution sol = new Solution(words);
        System.out.println("words: " + Arrays.toString(words));
        
        String word1 = "practice";
        String word2 = "coding";
        System.out.println("distence between " + word1 + " and " + word2 + ": " + sol.shortest(word1, word2));
        
        word1 = "makes";
        word2 = "coding";
        System.out.println("distence between " + word1 + " and " + word2 +  ": " + sol.shortest(word1, word2));
    }
}
