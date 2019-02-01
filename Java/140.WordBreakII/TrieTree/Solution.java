/* TrieTree + Backtrace + Memorization: Time:O(n*k), where n is the length of s, k is number of sentence
 * 1. Have a trie tree to store the dictionary
 * 2. Have a hash map, where key is index, and value is the string set wich can be assembled from substring(idx) 
 * 3. Call helper recursively, and return answer directly if hashmap has the answer
 */

import java.util.*;

//Definition for singly-linked list.
public class Solution{
    private class TrieNode{
        String word;
        TrieNode[] child;
        TrieNode(){word = ""; child = new TrieNode[96];}
    }
    
    TrieNode tree;
    
    private void insert(TrieNode root, String word){
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(root.child[c - ' '] == null){
                root.child[c - ' '] = new TrieNode();
            }
            root = root.child[c - ' '];
        }
        root.word = word;
    } 
    
    private List<String> search(String s, int idx, TrieNode tree){
        List<String> words = new ArrayList<String>();
        for(int i = idx; i < s.length(); ++i){
            char c = s.charAt(i);
            if(tree.child[c - ' '] != null){
                tree = tree.child[c - ' '];
                if(tree.word.length() > 0){
                    words.add(tree.word);
                }
            }
            else{
                break;
            }
        }
        return words;
    }
    
    private List<String> helper(String s, int idx, TrieNode tree, Map<Integer, List<String>> map){
        if(idx == s.length()){
            return new ArrayList<String>();
        }
        
        if(map.containsKey(idx)){
            return map.get(idx);
        }
        
        List<String> words = search(s, idx, tree);
        List<String> ret = new ArrayList<String>();
        for(String word: words){
            List<String> sentences = helper(s, idx + word.length(), tree, map);
            if((idx + word.length()) == s.length()){
                ret.add(word);
                continue;
            }
            if(sentences == null){
                continue;
            }
            for(String sentence: sentences){
                ret.add(word + " " + sentence);
            }
        }
        map.put(idx, (ret.size() == 0)? null: ret); 
        return map.get(idx);
    }
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        tree = new TrieNode();
        for(String word: wordDict){
            insert(tree, word);
        }
        List<String> ret = helper(s, 0, tree, new HashMap<Integer, List<String>>());
        return (ret == null)? new ArrayList<String>(): ret;
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
