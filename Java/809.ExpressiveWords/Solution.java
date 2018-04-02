/* Two pointers: Time:O(l*m + n), Space:O(1), wwhere l = S length, m = words number, n = characters number of words
 * 1. Use two pointers to check if word is stretchy to S
 * 2. Scan all the words
 */

import java.util.*;

public class Solution{
    private boolean isStretchy(String S, String word){
        int[] indexS = {0, 0};
        int[] indexWord = {0, 0};
        
        if(word.length() > S.length()){
            return false;
        }
        
        while(indexS[0] < S.length() && indexWord[0] < word.length()){
            if(S.charAt(indexS[0]) != word.charAt(indexWord[0])){
                return false;
            }
            char c = S.charAt(indexS[0]);
            while(indexS[1] < S.length() && S.charAt(indexS[1]) == c){indexS[1]++;}
            int lengthS = indexS[1] - indexS[0];
            c = word.charAt(indexWord[0]);
            while(indexWord[1] < word.length() && word.charAt(indexWord[1]) == c){indexWord[1]++;}
            int wordLength = indexWord[1] - indexWord[0];
            if(lengthS < wordLength || (wordLength < lengthS && lengthS <= 2)){
                return false;
            }
            indexS[0] = indexS[1];
            indexWord[0] = indexWord[1];
        }
        return (indexS[0] == S.length() && indexWord[0] == word.length());
    }
    
    public int expressiveWords(String S, String[] words) {
        int count = 0;
        for(String word: words){
            count = isStretchy(S, word)? count + 1: count; 
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol;
        String[] words = {"hello", "hi", "helo"};
        String S = "heeellooo";
        
        sol = new Solution();
        System.out.println("S: " + S);
        System.out.println("words[]: " + Arrays.toString(words));
        System.out.println("stretchy word count: " + sol.expressiveWords(S, words));
    }
}
