/* DynamicProgramming: Time:O(n^2 * 2^n), where n is the length of s
 */

import java.util.*;

//Definition for singly-linked list.
public class Solution{
   public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        int maxWordLen = 0;
        for(String word: words){
            maxWordLen = Math.max(maxWordLen, word.length());
        }
        List<List<String>> dp = new ArrayList<>();
        for(int i = 0; i <= s.length(); ++i){
            dp.add(new ArrayList<>());
        }
        dp.get(0).add("");
        
        for(int i = 0; i < s.length(); ++i){
            for(int j = Math.max(0, i - maxWordLen + 1); j <= i; ++j){
                String word = s.substring(j, i + 1);
                if(words.contains(word) && dp.get(j).size() > 0){
                    List<String> sentences = dp.get(j);
                    for(String sentence: sentences){
                        String newSentence = (sentence.length() == 0)? word: sentence + " " + word;
                        dp.get(i + 1).add(newSentence);
                    }
                }
            }
        }
        return dp.get(s.length());
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<String>();
        wordDict.addAll(Arrays.asList("cat", "cats", "and", "sand", "dog")); 
        
        System.out.println("s: " + s);
        System.out.println("wordDict: " + wordDict);
        System.out.println("sentences: " + sol.wordBreak(s, wordDict));
    }
}
