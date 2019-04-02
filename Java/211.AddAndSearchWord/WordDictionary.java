/* Trie Tree: Time:O(height), Space:O(n), where n is character#
 */         

import java.util.*;

public class WordDictionary {
    public class TriexNode{
        boolean isWord;
        TriexNode[] children;
        public TriexNode(){isWord = false; children = new TriexNode[26];}
    }
    
    private TriexNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TriexNode();
    }
    
    private void insert(String word, int idx, TriexNode root){
        if(idx == word.length()){
            root.isWord = true;
            return;
        }
        else{
            char c = word.charAt(idx);
            if(root.children[c - 'a'] == null){
                root.children[c - 'a'] = new TriexNode();
            }
            insert(word, idx + 1, root.children[c - 'a']);
        }
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        insert(word, 0, root);
    }
    
    private boolean isExist(String word, int idx, TriexNode root){
        if(root == null){
            return false;
        }
        else if(idx == word.length()){
            return root.isWord;
        }
        else{
            char c = word.charAt(idx);
            if(c == '.'){
                for(int i = 0; i < 26; ++i){
                    if(isExist(word, idx + 1, root.children[i])){
                        return true;
                    }
                }
                return false;
            }
            else{
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
