/* Bit manupulation: O(n^2)
 * 1. Sort string array by their length
 * 2. Caculate their signature
 * 3. Find the maximum product among all pairs, and with smart breaks
 */

import java.util.*;

public class Solution{
    private class StringLengthComparator implements Comparator<String> {
        public int compare(String str0, String str1) {
            return str1.length() - str0.length();
        }
    }
    
    public int maxProduct(String[] words) {
        
        Arrays.sort(words, new StringLengthComparator());
        
        // Create masks for every word
        int[] masks = new int[words.length];
        for(int i = 0; i < words.length; ++i){
            String word = words[i];
            for(int j = 0; j < word.length(); ++j){
                char c = word.charAt(j);
                masks[i] = masks[i] | (1 << (c - 'a'));
            }
        }
        
        //Compare from the longest string, break if the current length <= maximum length
        int maxLen = 0;
        for(int i = 0; i < (words.length - 1); ++i){
            for(int j = i + 1; j < words.length; ++j){
                int currLen = words[i].length() * words[j].length();
                if(currLen <= maxLen){
                    break;
                }
                else{
                    if((masks[i] & masks[j]) == 0){
                        maxLen = Math.max(maxLen, currLen);
                    }
                }
            }
        }
        
        return maxLen;
    }
    public static void main(String[] args){
        Solution sol;
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        int maxProduct;

        System.out.println("words[]: " + Arrays.asList(words));
        
        sol = new Solution();    
        maxProduct = sol.maxProduct(words);

        System.out.println("maximum product: " + maxProduct);
    }
}
