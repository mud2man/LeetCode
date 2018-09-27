/* Trie tree:
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private class TrieNode{
        boolean isWord;
        TrieNode[] children;
        TrieNode(){isWord = false; children = new TrieNode[26];} 
    }
    
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public Solution() {
        root = new TrieNode();
    }
    
    private void insert(TrieNode root, String word, int idx){
        if(word.length() == idx){
            root.isWord = true;
        }
        else{
            char c = word.charAt(idx);
            if(root.children[c - 'a'] == null){
                root.children[c - 'a'] = new TrieNode();
            }
            insert(root.children[c - 'a'], word, idx + 1);
        }
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(root, word, 0);
    }
    
    private boolean search(TrieNode root, String word, int idx, boolean isPrefix) {
        if(word.length() == idx){
            if(!isPrefix)
                return root.isWord;
            else
                return true;
        }
        else{
            char c = word.charAt(idx);
            if(root.children[c - 'a'] == null){
                return false;
            }
            else{
                return search(root.children[c - 'a'], word, idx + 1, isPrefix);
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(root, word, 0, false);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return search(root, prefix, 0, true);
    }

    public static void main(String[] args){
        Solution sol= new Solution();
        String word = "apple";
        sol.insert(word);
        System.out.println("insert(" + word + ")");
        System.out.println("search(" + word + "): " + sol.search(word));
        word = "app";
        System.out.println("search(" + word + "): " + sol.search(word));
        System.out.println("startsWith(" + word + "): " + sol.startsWith(word));
        sol.insert(word);
        System.out.println("insert(" + word + ")");
        System.out.println("search(" + word + "): " + sol.search(word));
    }
}
