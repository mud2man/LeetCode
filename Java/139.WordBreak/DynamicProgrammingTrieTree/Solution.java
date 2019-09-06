/* Dynamic Programing + Trie tree: O(max(n*m, k*m)), where n is the length of s, k is the size of wordDict, m is the length of longest word
 * 1. dp[i] = true, means s.substring(0, i + 1) can be seperated
 * 2. Only handle when dp[i -  1] is true, 
 * 3. If dp[size] is true, means s.substring(0, size) can be seperated
 * ex: s: "leetcode", wordDict:["leet", "code"]
 *     dp[] = {1, 0, 0, 0, 1, 0, 0, 0, 1}
 */

import java.util.*;

public class Solution{
    private class TrieNode{
        boolean isLastChar;
        TrieNode[] child;
        TrieNode(){child = new TrieNode[26]; isLastChar = false;}
    }
    
    private void insert(TrieNode root, String word){
        for(char c: word.toCharArray()){
            root.child[c - 'a'] = (root.child[c - 'a'] == null)? new TrieNode(): root.child[c - 'a']; 
            root = root.child[c - 'a'];
        }
        root.isLastChar = true;
    }
    
    private void searchAndMark(TrieNode root, boolean[] dp, int start, String s){
        for(int i = start; i < s.length(); ++i){
            char c = s.charAt(i);
            if(root.child[c - 'a'] == null){
                return;
            }else{
                root = root.child[c - 'a'];
                dp[i] = root.isLastChar? true: dp[i];
            }
        }
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        TrieNode root = new TrieNode();
        for(String word: wordDict){
            insert(root, word);
        }
        
        boolean[] dp = new boolean[s.length()];
        for(int i = 0; i < s.length(); ++i){
            if(i == 0 || dp[i - 1] == true){
                searchAndMark(root, dp, i, s);
            }
        }
        return dp[dp.length - 1];
    }
 
    public static void main(String[] args){
        List<String> wordDict = Arrays.asList("leet", "code");
        String s = "leetcode";
        Solution sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("wrodDict: " + wordDict);
        System.out.println("canBorken: " + sol.wordBreak(s, wordDict));
    }
}
