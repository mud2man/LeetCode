/* TrieTree: Time:O(C), Space:O(C) where C is total number of characters
 * 1. Have 3 trie tree caseSensitiveTree, caseInsensitiveTree, vowelInsensitiveTree.
 * 2. Insert words to the three trees using getIndex given "type"
 * 3. Call search given "type", and returned the first result
 */     

import java.util.*; // Stack

public class Solution {
    private class TrieNode{
        String word;
        TrieNode[] child;
        TrieNode(String w){ word = w; child = new TrieNode[52];}
    }
    TrieNode caseSensitiveTree = new TrieNode(null);
    TrieNode caseInsensitiveTree = new TrieNode(null);
    TrieNode vowelInsensitiveTree = new TrieNode(null);
    
    //type: 0 -> caseSensitiveTree / 1 -> caseInsensitiveTree / 2 -> vowelInsensitiveTree
    private int getIndex(char c, int type){
        if(type == 0){
            return Character.isUpperCase(c)? (c - 'A' + 26) : (c - 'a');
        }else if (type == 1){
            return Character.isUpperCase(c)? (c - 'A') : (c - 'a');  
        }else{
            c = Character.toLowerCase(c);
            return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')? 0: (c - 'a'); 
        }
    }

    private void insert(String word, int type){
        TrieNode node = (type == 0)? caseSensitiveTree: (type == 1)? caseInsensitiveTree: vowelInsensitiveTree;
        for(char c: word.toCharArray()){
            node.child[getIndex(c, type)] = (node.child[getIndex(c, type)] == null)? 
                new TrieNode(null): node.child[getIndex(c, type)];
            node = node.child[getIndex(c, type)];
        }
        node.word = (node.word == null)? word: node.word; 
    }
    
    private String search(String word, int type){
        TrieNode node = (type == 0)? caseSensitiveTree: (type == 1)? caseInsensitiveTree: vowelInsensitiveTree;
        for(char c: word.toCharArray()){
            node = node.child[getIndex(c, type)];
            if(node == null){
                return null;
            }
        }
        return node.word; 
    }
    
    public String[] spellchecker(String[] wordlist, String[] queries) {
        for(String word: wordlist){
            for(int i = 0; i < 3; ++i){
                insert(word, i);
            }
        }
        
        String[] results = new String[queries.length];
        for(int i = 0; i < queries.length; ++i){
            String query = queries[i];
            String result = null;
            for(int j = 0; j < 3; ++j){
                result = search(query, j);
                if(result != null){
                    break;
                }
            }
            results[i] = (result == null)? "": result;
        }
        return results;
    }
  
    public static void main(String[] args){
        String[] wordlist = {"KiTe", "kite", "hare", "Hare"};
        String[] queries = {"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"};
        Solution sol = new Solution();
        System.out.println("wordlist:" + Arrays.toString(wordlist));
        System.out.println("queries:" + Arrays.toString(queries));
        System.out.println("results:" + Arrays.toString(sol.spellchecker(wordlist, queries)));
    }
}
