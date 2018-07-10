/* TrieTree + backtrack: Time:O(n!), Space:O(n^2)
 * 1. Use trie tree to store prefix-wordList pair
 * 2. Use backtrack to find all the word square give caculating prefix
 */

import java.util.*;

public class Solution{
    private class TrieTree{
        List<String> words;
        TrieTree[] child;
        TrieTree(){words = new ArrayList<>(); child = new TrieTree[26];}
    }
    
    private void insert(TrieTree root, String word){
        root.words.add(word);
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(root.child[c - 'a'] == null){
                root.child[c - 'a'] = new TrieTree();
            }
            root = root.child[c - 'a'];
            root.words.add(word);
        }
    }
    
    private List<String> search(TrieTree root, String prefix){
        for(int i = 0; i < prefix.length(); ++i){
            char c = prefix.charAt(i);
            root = root.child[c - 'a'];
            if(root == null){
                return new ArrayList<>();
            }
        }
        return root.words;
    }
    
    private void backtrack(List<List<String>> squares, LinkedList<String> path, int n, TrieTree root){
        if(path.size() == n){
            squares.add(new ArrayList<>(path));
            return;
        }
        
        StringBuilder prefix = new StringBuilder("");
        for(int i = 0; i < path.size(); ++i){
            prefix.append(path.get(i).charAt(path.size()));
        }
        List<String> nexts = search(root, prefix.toString());
        
        for(String next: nexts){
            path.add(next);
            backtrack(squares, path, n, root);
            path.pollLast();
        }
    }
    
    public List<List<String>> wordSquares(String[] words) {
        TrieTree root = new TrieTree();
        for(String w: words){
            insert(root, w);
        }
        
        List<List<String>> squares = new ArrayList<List<String>>();
        backtrack(squares, new LinkedList<String>(), words[0].length(), root);
        return squares;
    }
  
    public static void main(String[] args){
        Solution sol;
        String[] words = {"area","lead","wall","lady","ball"};
        
        sol = new Solution();
        System.out.println("words[]: " + Arrays.toString(words));
        System.out.println("word squares: " + sol.wordSquares(words));
    }
}
