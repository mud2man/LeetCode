/* Trie Tree: Time:O(height), Space:O(n), where n is character#
 */         

import java.util.*;

public class WordDictionary {
    private class TrieNode{
        String word;
        TrieNode[] children;
        TrieNode(){word = ""; children = new TrieNode[26];}
    }
    
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()){
            if(node.children[c - 'a'] == null){
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.word = word;
    }
    
    private boolean isExist(String word, int idx, TrieNode root){
        if(root == null){
            return false;
        }else if(idx == word.length()){
            return (root.word.length() > 0);
        }else{
            char c = word.charAt(idx);
            if(c == '.'){
                for(int i = 0; i < 26; ++i){
                    if(isExist(word, idx + 1, root.children[i])){
                        return true;
                    }
                }
                return false;
            }else{
                return isExist(word, idx + 1, root.children[c - 'a']);
            }
        }
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return isExist(word, 0, root);
    }
   
    public static void main(String[] args){
        WordDictionary dict = new WordDictionary();
        dict.addWord("namo"); 
        dict.addWord("elsie"); 

        System.out.println("search namo: " + dict.search("namo"));
        System.out.println("search elsie: " + dict.search("elsie"));
        System.out.println("search n.m.: " + dict.search("n.m."));
        System.out.println("search ..sie: " + dict.search("..sie"));
        System.out.println("search ..sii: " + dict.search("..sii"));
    }
}
