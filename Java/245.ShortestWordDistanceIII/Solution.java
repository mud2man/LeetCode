/* O(n)
 * 1. Have a pointer array "maxIndex" to record the maximum index encountering word1 and word2
 * 2. When seeing word1, upadte minDistance with Math.min(minDistance, index - maxIndex[1]), and update maxIndex[0] = index
 * 3. When seeing word2, upadte minDistance with Math.min(minDistance, index - maxIndex[0]), and update maxIndex[1] = index
 * 4. Return minDistance
 */

import java.util.*;

public class Solution{
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int minDistance = words.length;
        if(!word1.equals(word2)){
            int[] maxIndex = {-words.length, words.length};            
            for(int index = 0; index < words.length; ++index){
                maxIndex[0] =words[index].equals(word1)? index: maxIndex[0];
                maxIndex[1] =words[index].equals(word2)? index: maxIndex[1];
                minDistance = Math.min(minDistance, Math.abs(maxIndex[0] - maxIndex[1])); 
            }
        }else{
            int maxIndex = -words.length;
            for(int index = 0; index < words.length; ++index){
                if(words[index].equals(word1)){
                    minDistance = Math.min(minDistance, index - maxIndex); 
                    maxIndex = index;
                }
            }
        }
        return minDistance;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 2;
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "makes";
        String word2 = "coding";

        System.out.println("words: " + Arrays.toString(words));
        System.out.println("word1: " + word1);
        System.out.println("word2: " + word2);
        System.out.println("shortestWordDistance: " + sol.shortestWordDistance(words, word1, word2));
    }
}
