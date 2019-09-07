/* Trie tree: Time:O(h), Space:O(n)
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private class TrieNode{
        String word;
        TrieNode[] child;
        TrieNode(String w){word = w; child = new TrieNode[26];}
    }
    TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode("");
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(node.child[c - 'a'] == null){
                node.child[c - 'a'] = new TrieNode("");
            }
            node = node.child[c - 'a'];
        }
        node.word = word;
    }
    
    private TrieNode searchHelper(String word){
        TrieNode node = root;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(node.child[c - 'a'] == null){
                return null;
            }
            node = node.child[c - 'a'];
        }
        return node;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchHelper(word);
        return (node != null && node.word.length() > 0);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchHelper(prefix);
        return (node != null);
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
