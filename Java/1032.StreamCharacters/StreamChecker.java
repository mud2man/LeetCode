/* Trie tree: Time:O(n), Space:O(n). LeetCode has a diferent approach which has better run time
 * 1. Cretea a trie tree given a dictionary "words"
 * 2. Have a queue to store the previous possible position
 * 3. If the current node's isLastChar is true. We return true
 */

import java.util.*;


//Definition for singly-linked list.
public class StreamChecker{
    private class TrieNode{
        boolean isLastChar;
        TrieNode[] child;
        TrieNode(){isLastChar = false; child = new TrieNode[26];}
    }
    TrieNode root;
    Deque<TrieNode> queue;
    
    private void insert(String word){
        TrieNode node = root;
        for(char c: word.toCharArray()){
            node.child[c - 'a'] = (node.child[c - 'a'] == null)? new TrieNode(): node.child[c - 'a'];
            node = node.child[c - 'a'];
        }
        node.isLastChar = true;
    }
    
    public StreamChecker(String[] words) {
        root = new TrieNode();
        queue = new LinkedList<>();
        for(String word: words){
            insert(word);
        }
    }
    
    public boolean query(char letter) {
        queue.add(root);
        int size = queue.size();
        boolean ret = false;
        for(int i = 0; i < size; ++i){
            TrieNode curr = queue.pollFirst();
            if(curr.child[letter - 'a'] != null){
                queue.add(curr.child[letter - 'a']);
                if(curr.child[letter - 'a'].isLastChar){
                    ret = true;
                }
            }
        }
        return ret;
    }
   
    public static void main(String[] args){
        String[] words = {"cd", "f", "kl"};
        StreamChecker sol = new StreamChecker(words);
        System.out.println("words:" + Arrays.toString(words));
        char[] queries = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
        for(char c: queries){
            System.out.println("query(" + c + "):" + sol.query(c));
        }
    }
}
