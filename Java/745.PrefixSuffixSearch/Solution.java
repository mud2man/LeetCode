/* Trie: Time:O(n*l), Space:O(n*l). Leetcode has a better answer
 * https://leetcode.com/problems/prefix-and-suffix-search/discuss/110044/Three-ways-to-solve-this-problem-in-Java
 * 1. Create a trie tree of words
 * 2. Get the root of sub tree by key = prefix
 * 3. Traverse the sub tree, and find the maximum weight 
 */

import java.util.*;

public class Solution{
    private class TrieNode{
        String s;
        TrieNode[] child;
        TrieNode(){s = ""; child = new TrieNode[26];}
    }
    
    HashMap<String, Integer> cache;
    HashMap<String, Integer> weightMap;
    TrieNode root;
    
    public Solution(String[] words) {
        cache = new HashMap<String, Integer>();
        root = new TrieNode();
        weightMap = new HashMap<String, Integer>();
            
        for(int i = 0; i < words.length; ++i){
            insert(root, words[i]);
            weightMap.put(words[i], i);
        }
    }
    
    private void insert(TrieNode root, String word){
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(root.child[c - 'a'] == null){
                root.child[c - 'a'] = new TrieNode();
            }
            root = root.child[c - 'a'];
        }
        root.s = word;
    }
    
    private TrieNode search(TrieNode root, String prefix){
        for(int i = 0; i < prefix.length(); ++i){
            char c = prefix.charAt(i);
            if(root.child[c - 'a'] == null){
                return null;
            }
            root = root.child[c - 'a'];
        }
        return root;
    }
    
    private int getWeight(TrieNode subRoot, String suffix, HashMap<String, Integer> weightMap){
        if(subRoot == null){
            return -1;
        }
        
        int weight = (subRoot.s.length() > 0 && subRoot.s.endsWith(suffix))? weightMap.get(subRoot.s): -1;
        for(int i = 0; i < 26; ++i){
            weight = Math.max(weight, getWeight(subRoot.child[i], suffix, weightMap));
        }
        return weight;
    }

    public int f(String prefix, String suffix) {
        String key = prefix + "," + suffix;
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        else{
            TrieNode subRoot = search(root, prefix);
            int weight = getWeight(subRoot, suffix, weightMap);
            cache.put(key, weight);
            return weight;
        }
    }
 
    public static void main(String[] args){
        Solution sol;
        String[] words = {"apple"};
        String[] prefix = {"a", "b"};
        String[] suffix = {"e", ""};
        
        sol = new Solution(words);
        System.out.println("words[]: " + Arrays.toString(words));
        for(int i = 0; i < prefix.length; ++i){
            System.out.println("prefix:" + prefix[i] + ", suffix:" + suffix[i] + ", weight:" + sol.f(prefix[i], suffix[i]));
        }
    }
}
