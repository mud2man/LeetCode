/* Trie: O(character# of dict)), LeetCode has an O(1) search using HashMap 
 * 1. Construct a trie with the dictionary
 * 2. Return true only if errorCount == 1
 * 3. Accumulate errorCount if character not match when invoke search
 */

import java.util.*;

public class Solution{
    private class TrieNode{
        String word;
        TrieNode[] child;
        TrieNode(){word = ""; child = new TrieNode[26];}
    }
    TrieNode root; 
    
    /** Initialize your data structure here. */
    public Solution() {
        root = null;
    }
    
    private void insert(TrieNode root, String word){
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(root.child[c - 'a'] == null){
                root.child[c - 'a'] = new TrieNode();
            }
            root = root.child[c - 'a'];
        }
        root.word = word;
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        this.root = new TrieNode();
        
        for(String word: dict){
            insert(this.root, word);
        }
    }
    
    private boolean search(TrieNode root, String word, int index, int errorCount){
        if(errorCount > 1 || root == null || index > word.length()){
            return false;
        }
        
        if(index == word.length() && root.word.length() > 0){
            return (errorCount == 1);
        }
        
        for(int i = 0; i < 26; ++i){
            char c = (index < word.length())? word.charAt(index): 'a';
            if(i == (c - 'a')){
                if(root.child[i] != null && search(root.child[i], word, index + 1, errorCount)){
                    return true;
                }
            }
            else{
                if(root.child[i] != null && search(root.child[i], word, index + 1, errorCount + 1)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return search(this.root, word, 0, 0);
    }

    public static void main(String[] args){
        Solution sol;
        String[] dict = {"hello", "leetcode"};
        sol = new Solution();
        
        sol.buildDict(dict);
        System.out.println("dict: " + Arrays.toString(dict));
        System.out.println("search(\"hello\"): " + sol.search("hello"));
        System.out.println("search(\"hhllo\"): " + sol.search("hhllo"));
        System.out.println("search(\"hell\"): " + sol.search("hell"));
        System.out.println("search(\"leetcoded\"): " + sol.search("leetcoded"));
    }
}
