/* Trie, DFS and Bacltrack
 * 1. Construct a trie to store the "words"
 * 2. Use DFS to visit the "board" element, and tarck the path by the current TriexNode
 * 3. If there is no children of the current TriexNode, which means the current path contains no word. So return
 * 4. If the current TriexNode's word is not "", hit the available word. Then delete the word from the Trie
 * 5. Visit all directions, statring from the current position
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private class TrieNode{
        TrieNode farther;
        TrieNode[] children;
        String word;
        int size;
        TrieNode(TrieNode f){
            word = "";
            size = 0;
            farther = f; 
            children = new TrieNode[26];
            Arrays.fill(children, null);}
    }
    
    public void dfs(char[][] board, int y, int x, TrieNode farther, List<String> qualifiedWords){
        char c = board[y][x];
        
        if(farther.children[c - 'a'] == null){
            return;
        }
        
        //push
        board[y][x] = '*';
        
        TrieNode child = farther.children[c - 'a'];
        if(child.word.length() > 0){
            qualifiedWords.add(child.word);
            deleteWord(child, child.word, child.word.length() - 1);
        }

        //search up
        if(y > 0 && board[y - 1][x] != '*' ){
            dfs(board, y - 1, x, child, qualifiedWords);
        }
        
        //search down
        if(y < board.length - 1 && board[y + 1][x] != '*'){
            dfs(board, y + 1, x, child, qualifiedWords);
        }
        
        //search left
        if(x > 0 && board[y][x - 1] != '*'){
            dfs(board, y, x - 1, child, qualifiedWords);
        }
        
        //search right
        if(x < board[0].length - 1 && board[y][x + 1] != '*'){
            dfs(board, y, x + 1, child, qualifiedWords);
        }
        
        //pop
        board[y][x] = c;
    }
    
    public void insertWord(TrieNode root, String word){
        for(int idx = 0; idx < word.length(); idx++){
            char c = word.charAt(idx);
            if(root.children[c - 'a'] == null){
                root.children[c - 'a'] = new TrieNode(root);
                root.size++;
            }
            root = root.children[c - 'a'];
        }
        root.word = word;
    }
    
    public void deleteWord(TrieNode node, String word, int idx){
        if(idx < 0){
            return;
        }
        
        if(idx == word.length() - 1){
            node.word = "";
        }
        
        char c = word.charAt(idx);
        if(node.word.equals("") && node.size == 0){
            node.farther.children[c - 'a'] = null;
            node.farther.size--;
            deleteWord(node.farther, word, idx - 1);
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> qualifiedWords = new LinkedList<String>();
        TrieNode root = new TrieNode(null);
        
        //construct trie
        for(String word: words){
            insertWord(root, word);
        }
        
        for(int y = 0; y < board.length; ++y){
            for(int x = 0; x < board[0].length; ++x){
                dfs(board, y, x, root, qualifiedWords);
            }
        }
        return qualifiedWords;
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
