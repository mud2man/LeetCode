/* TrieTree + backtrack: Time:O(n!^2), Space:O((n*m)^2), where n is words#, m is word's length
 * 1. Use trie tree to store prefix-wordList pair
 * 2. Use backtrack to find all the word square give caculating prefix
 */

import java.util.*;

public class Solution{
    private class TrieNode{
        List<String> words;
        TrieNode[] child;
        TrieNode(){words = new ArrayList<>(); child = new TrieNode[26];}
    }
    TrieNode root;
    
    private void insert(String word){
        TrieNode node = root;
        node.words.add(word);
        for(char c: word.toCharArray()){
            node.child[c - 'a'] = (node.child[c - 'a'] == null)? new TrieNode(): node.child[c - 'a'];
            node = node.child[c - 'a'];
            node.words.add(word);
        }
    }
    
    private List<String> search(StringBuilder prefix){
        TrieNode node = root;
        for(int i = 0; i < prefix.length(); ++i){
            char c = prefix.charAt(i);
            if(node.child[c - 'a'] == null){
                return new ArrayList<>();
            }
            node = node.child[c - 'a'];
        }
        return node.words;
    }
    
    private void backtrack(int remain, Deque<String> path, List<List<String>> squares){
        if(remain == 0){
            squares.add(new LinkedList<>(path));
            return;
        }
        StringBuilder prefix = new StringBuilder("");
        List<String> list = new ArrayList<>(path);
        for(int y = 0; y < list.size(); ++y){  
            prefix.append(list.get(y).charAt(list.size()));
        }
        List<String> nexts = search(prefix);
        for(String next: nexts){
            path.addLast(next);
            backtrack(remain - 1, path, squares);
            path.pollLast();
        }
    }
    
    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();
        for(String word: words){
            insert(word);
        }
        List<List<String>> squares = new ArrayList<>();
        backtrack(words[0].length(), new LinkedList<>(), squares);
        return squares;
    }
  
    public static void main(String[] args){
        String[] words = {"area","lead","wall","lady","ball"};
        Solution sol = new Solution();
        System.out.println("words[]: " + Arrays.toString(words));
        System.out.println("word squares: " + sol.wordSquares(words));
    }
}
