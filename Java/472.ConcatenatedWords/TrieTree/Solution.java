/* TrieTree: Time:O(m*n^2), Space:O(n*m), where m = words.length, n = max word length. LeetCode has a DP solution
 * 1. Sort the array "words" by length, and check if the word is prime from left
 * 2. If the word is prime, we insert it to trie tree
 * 3. Otherwise, we put the word into nonPrimes, because if any word behind can be concatenated by it. The word can be concatenated
 *    by the words in the trie tree
 * 4. Have a helper "isNonPrime", can check if the word can be assembled by the trie tree recursively
 */

import java.util.*;

public class Solution{
    private class TrieNode{
        String word;
        TrieNode[] child;
        TrieNode(String w){word = w; child = new TrieNode[26];}
    }
    
    TrieNode root;
    
    private class StringComparator implements Comparator<String>{
        public int compare(String x, String y){
            return x.length() - y.length();
        }
    }
    
    private void insert(TrieNode root, String word){
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(root.child[c - 'a'] == null){
                root.child[c - 'a'] = new TrieNode("");
            }
            root = root.child[c - 'a'];
        }
        root.word = word;
    }
    
    private boolean isNonPrime(String word, int idx, TrieNode node){
        if(node == null){
            return false;
        }

        if(idx == word.length()){
            if(node.word.length() > 0){
                return true;
            }
            else{
                return false;
            }
        }
        
        boolean ret = false;
        ret |= isNonPrime(word, idx + 1, node.child[word.charAt(idx) - 'a']);
        if(node.word.length() > 0 && ret == false){
            ret |= isNonPrime(word, idx, root);
        }
        return ret;
    }
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, new StringComparator());
        
        root = new TrieNode("");
        List<String> nonPrimes = new ArrayList<>();
        for(String word: words){
            if(isNonPrime(word, 0, root)){
                nonPrimes.add(word);   
            }
            else{
                insert(root, word);
            }
        }
        return nonPrimes;
    }    
 
    public static void main(String[] args){
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        Solution sol = new Solution();

        System.out.println("words: " + Arrays.toString(words));
        System.out.println("concatenated words: " + sol.findAllConcatenatedWordsInADict(words));
    }
}
