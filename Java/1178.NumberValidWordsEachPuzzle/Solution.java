/* Trie Tree: Time:O(n + m), Space:O(n + m), where n is the character# of words, m is the characetr # of puzzles
 * 1. Have trie node with "counts" for retrieving words contain first char of puzzle, and "child" 
 * 2. Construct trie tree by inserting words
 * 3. Traverse tree for single puzzle, and accumulate count[0] during the search. Then select the next character of puzzle
 * 4. All the word and puzzle are sorted, since the matching rules are order independent 
 */

import java.util.*;

public class Solution {
    private class TrieNode{
        int[] counts;
        TrieNode[] child;
        TrieNode(){counts = new int[26]; child = new TrieNode[26];}
    }
    TrieNode root;
    
    private String compress(String word){
        boolean[] chars = new boolean[26];
        for(char c: word.toCharArray()){
            chars[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < 26; ++i){
            sb.append(chars[i]? (char)(i + (int)('a')): "");
        }
        return sb.toString();
    }
    
    private void insert(String word){
        word = compress(word);
        if(word.length() <= 7){
            TrieNode node = root;
            for(int i = 0; i < word.length(); ++i){
                char c = word.charAt(i);
                node.child[c - 'a'] = (node.child[c - 'a'] == null)? new TrieNode(): node.child[c - 'a'];
                node = node.child[c - 'a'];
            }
            for(int i = 0; i < word.length(); ++i){
                node.counts[word.charAt(i) - 'a']++;
            }
        }
    }
    
    private void search(int idx, String puzzle, TrieNode node, int[] count, char firstChar) {
        if(idx >= puzzle.length() || node == null){
            return;
        }
        char c = puzzle.charAt(idx);
        node = node.child[c - 'a'];
        count[0] += (node != null)? node.counts[firstChar - 'a']: 0;
        for(int i = 1; i < 7; ++i){
            search(idx + i, puzzle, node, count, firstChar);
        }
    } 
    
    private int search(String puzzle){
        char firstChar = puzzle.charAt(0);
        puzzle = compress(puzzle);
        int[] count = {0};
        for(int i = 0; i < 7; ++i){
            search(i, puzzle, root, count, firstChar);
        }
        return count[0];
    }
    
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        root = new TrieNode();
        for(String word: words){
            insert(word);
        }
        List<Integer> ret = new ArrayList<>();
        for(String puzzle: puzzles){
            ret.add(search(puzzle));
        }
        return ret;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        System.out.println("words:" + Arrays.toString(words));   
        System.out.println("puzzles:" + Arrays.toString(puzzles));   
        System.out.println("valid words#:" + sol.findNumOfValidWords(words, puzzles));   
	}
}
