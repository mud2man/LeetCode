/* Trie, DFS and Backtrack
 * 1. Construct a trie to store the "words"
 * 2. Use DFS to visit the "board" element, and put the word into found if the size of word on the node > 0, then reset node child.word = ""
 * 3. Mark the visited char on the board with '*', and restore back before leaving dfs
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private class TrieNode{
        TrieNode[] child;
        String word;
        TrieNode(){child = new TrieNode[26]; word = "";}
    }

    private void insert(TrieNode root, String word){
        for(char c: word.toCharArray()){
            if(root.child[c - 'a'] == null){
                root.child[c - 'a'] = new TrieNode();
            }
            root = root.child[c - 'a'];
        }
        root.word = word;
    }
    
    private void dfs(char[][] board, int y, int x, TrieNode root, List<String> found){
        if(y < 0 || y >= board.length || x < 0 || x >= board[0].length || board[y][x] == '*'){
            return;
        }
        
        char c = board[y][x];
        board[y][x] = '*';
        TrieNode child = root.child[c - 'a'];
        if(child != null){
            if(child.word.length() > 0){
                found.add(child.word);
                child.word = "";
            }
            dfs(board, y - 1, x, child, found);
            dfs(board, y, x - 1, child, found);
            dfs(board, y + 1, x, child, found);
            dfs(board, y, x + 1, child, found);
        }  
        board[y][x] = c;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for(String word: words){
            insert(root, word);
        }
        List<String> found = new ArrayList<>();
        for(int y = 0; y < board.length; ++y){
            for(int x = 0; x < board[0].length; ++x){
                dfs(board, y, x, root, found);
            }
        }
        return found;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] words = {"oath", "pea", "eat", "rain"} ; 
        char[][] board = {{'o', 'a', 'a', 'n'},
                          {'e', 't', 'a', 'e'},
                          {'i', 'h', 'k', 'r'},
                          {'i', 'f', 'l', 'v'}};
        List<String> qualifiedWords;

        System.out.println("words: ");
        for(String word: Arrays.asList(words)){
            System.out.println(word);
        }
        System.out.println("");

        System.out.println("board: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");

        qualifiedWords = sol.findWords(board, words);
        System.out.println("qualifiedWords: " + qualifiedWords);
    }
}
