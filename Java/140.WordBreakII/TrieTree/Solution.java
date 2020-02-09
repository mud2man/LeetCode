/* TrieTree + Backtrace: Time:O(max(n * m, k)), Space:O(n * m), where n is length of s, m is max length of words, and k is number of sentence
 * 1. Have a trie tree to store words backwardly
 * 2. Have "idx2Words", where key is index and value is the word list wich can cover a substring ending at s.charAt(idx) and idx2Words.containsKey(idx - word.length() - 1)
 * 3. The second condition of above is to ensure we skip cases which cannot find word list to cover s.substring(0, idx - word.length)
 * 4. The we use backtrack to collect all possible solutions
 */

import java.util.*;

//Definition for singly-linked list.
public class Solution{
    private class TrieNode{
        String word;
        TrieNode[] children;
        TrieNode(){word = ""; children = new TrieNode[26];}
    }
    TrieNode tree = new TrieNode();
    
    private void insert(String word){
        TrieNode node = tree;
        for(int i = word.length() - 1; i >= 0; --i){
            char c = word.charAt(i);
            node.children[c - 'a'] = (node.children[c - 'a'] == null)? new TrieNode(): node.children[c - 'a'];
            node = node.children[c - 'a'];
        }
        node.word = word;
    } 
    
    private void search(String s, int end, Map<Integer, List<String>> idx2Words){
        TrieNode node = tree;
        for(int i = end; i >= 0 && node != null; --i){
            char c = s.charAt(i);
            node = node.children[c - 'a'];
            if(node != null && node.word.length() > 0 && idx2Words.containsKey(i - 1)){
                idx2Words.computeIfAbsent(end, key -> new ArrayList<>()).add(node.word);
            }
        }
    }
    
    private void backtarck(String s, int idx, Map<Integer, List<String>> idx2Words, List<String> sentences, String path){
        if(idx == -1){
            sentences.add(path.substring(1));
            return;
        }
        List<String> words = idx2Words.getOrDefault(idx, new ArrayList<>());
        for(String word: words){
            backtarck(s, idx - word.length(), idx2Words, sentences, " " + word + path);
        }
    }
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        for(String word: wordDict){
            insert(word);
        }
        Map<Integer, List<String>> idx2Words = new HashMap<>();
        idx2Words.put(-1, Collections.singletonList(""));
        for(int end = 0; end < s.length(); ++end){
            search(s, end, idx2Words);
        }
        List<String> sentences = new ArrayList<>();
        backtarck(s, s.length() - 1, idx2Words, sentences, "");
        return sentences;
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
