/* Greedy: Time:O(n * m), Space:O(1)
 * 1. The trick situation happens when word1.charAt(word1Idx) == word2.charAt(word2Idx)
 * 2. If happens, we compare sub strings word1{word1Idx + 1:} and word2{word2Idx + 1:}, then use the char with lexicographically larger substring
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public String largestMerge(String word1, String word2) {
        StringBuilder merge = new StringBuilder("");
        int word1Idx = 0;
        int word2Idx = 0;
        while(word1Idx < word1.length() || word2Idx < word2.length()){
            if(word1Idx == word1.length()){
                merge.append(word2.charAt(word2Idx++));
            }else if(word2Idx == word2.length()){
                merge.append(word1.charAt(word1Idx++));
            }else if(word1.charAt(word1Idx) != word2.charAt(word2Idx)){
                if(word1.charAt(word1Idx) > word2.charAt(word2Idx)){
                    merge.append(word1.charAt(word1Idx++));
                }else{
                    merge.append(word2.charAt(word2Idx++));
                }
            }else{
                String subWord1 = word1.substring(word1Idx + 1);
                String subWord2 = word2.substring(word2Idx + 1);
                if(subWord1.compareTo(subWord2) >= 0){
                    merge.append(word1.charAt(word1Idx++));
                }else{
                    merge.append(word2.charAt(word2Idx++));
                }
            }
        }
        return merge.toString();
    }
 
    public static void main(String[] args){
        String word1 = "cabaa";
        String word2 = "bcaaa";
        Solution sol = new Solution();
        System.out.println("word1" + word1 + ", word2:" + word2);
        System.out.println("merge:" + sol.largestMerge(word1, word2));
    }
}
