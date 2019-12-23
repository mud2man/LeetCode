/* Trie tree: O(n*k^2), where n is the length of words, k is the longest length or word. Need to check a shorter solution in LeetCode
 * 1. Construct 2 trie tree "forwardTree" and "backwardTree"
 * 2. Find the right partner, and then find the left partner
 */

import java.util.*;
import java.math.*;

public class Solution{
    private class TrieNode{
        int idx;
        TrieNode[] children;
        TrieNode(int i){idx = i; children = new TrieNode[26];}
    }
    TrieNode forwardTree;
    TrieNode backwardTree;
    
    private void insert(String str, int idx){
        TrieNode node = forwardTree;
        for(int i = 0; i < str.length(); ++i){
            if(node.children[str.charAt(i) - 'a'] == null){
                node.children[str.charAt(i) - 'a'] = new TrieNode(-1);
            }  
            node = node.children[str.charAt(i) - 'a'];
        }
        node.idx = idx;
        node = backwardTree;
        for(int i = str.length() - 1; i >= 0; --i){
            if(node.children[str.charAt(i) - 'a'] == null){
                node.children[str.charAt(i) - 'a'] = new TrieNode(-1);
            }  
            node = node.children[str.charAt(i) - 'a'];
        }
        node.idx = idx;
    }
    
    private boolean isPalodrom(String str, int start, int end){
        while(start < end){
            if(str.charAt(start++) != str.charAt(end--)){
                return false;
            }
        }
        return true;
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        forwardTree = new TrieNode(-1);
        backwardTree = new TrieNode(-1);
        for(int i = 0; i < words.length; ++i){
            insert(words[i], i);
        }
        List<List<Integer>> pairs = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for(int i = 0; i < words.length; ++i){
            String left = words[i]; //find right partner
            TrieNode node = backwardTree;
            for(int j = -1; j < left.length(); ++j){
                if(node == null){
                    break;
                }
                String key = Integer.toString(i) + "." + Integer.toString(node.idx); 
                if(isPalodrom(left, j + 1, left.length() - 1) && node.idx != -1 && i != node.idx && !seen.contains(key)){
                    pairs.add(Arrays.asList(i, node.idx));
                    seen.add(key);
                }
                char c = (j + 1 < left.length())? left.charAt(j + 1): 'a';
                node = node.children[c - 'a'];
            }
            String right = words[i]; //find left partner
            node = forwardTree;
            for(int j = right.length(); j >= 0; --j){
                if(node == null){
                    break;
                }
                String key = Integer.toString(node.idx) + "." + Integer.toString(i); 
                if(isPalodrom(right, 0, j - 1) && node.idx != -1 && i != node.idx && !seen.contains(key)){
                    pairs.add(Arrays.asList(node.idx, i));
                    seen.add(key);
                }
                char c = (j - 1 >= 0)? right.charAt(j - 1): 'a';
                node = node.children[c - 'a'];
            }
        }
        return pairs;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("palindrome pairs: " + sol.palindromePairs(words));
	}
}
