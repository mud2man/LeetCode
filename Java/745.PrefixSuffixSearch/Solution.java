/* Trie: Time:O(l + n), Space:O(n^2 * l). Leetcode has a better answer https://leetcode.com/problems/prefix-and-suffix-search/discuss/110044/Three-ways-to-solve-this-problem-in-Java
 * 1. Create 2 trie tree prefixTree and suffixTree(reverse of words)
 * 2. In query "f", get the index list of prefix and that of suffix
 * 3. Use merge-sort like method to find the first smae index in both prefix and suffix index list
 * 4. Since the index list in TrieNode is strictly decending, the first matched index is the biggest
 */

import java.util.*;

public class Solution{
    private class TrieNode{
        List<Integer> index;
        TrieNode[] child;
        TrieNode(){index = new ArrayList<>(); child = new TrieNode[26];}
    }
    HashMap<String, Integer> cache;
    TrieNode prefixTree;
    TrieNode suffixTree;
    
    public WordFilter(String[] words) {
        cache = new HashMap<String, Integer>();
        prefixTree = new TrieNode();
        suffixTree = new TrieNode();
        for(int i = words.length - 1; i >= 0; --i){
            //System.out.println("word:" + words[i] + ", i:" + i);
            insert(prefixTree, words[i], i);
            insert(suffixTree, new StringBuilder(words[i]).reverse().toString(), i);
        }
    }
    
    private void insert(TrieNode root, String word, int index){
        for(int i = 0; i < word.length(); ++i){
            root.index.add(index);
            char c = word.charAt(i);
            if(root.child[c - 'a'] == null){
                root.child[c - 'a'] = new TrieNode();
            }
            root = root.child[c - 'a'];
        }
        root.index.add(index);
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

    public int f(String prefix, String suffix) {
        String key = prefix + "," + suffix;
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        else{
            TrieNode prefixRoot = search(prefixTree, prefix);
            List<Integer> prefixIndex = (prefixRoot != null)? prefixRoot.index: new ArrayList<>();
            TrieNode suffixRoot = search(suffixTree, new StringBuilder(suffix).reverse().toString());
            List<Integer> suffixIndex = (suffixRoot != null)? suffixRoot.index: new ArrayList<>();
            int ptr0 = 0;
            int ptr1 = 0;
            while(ptr0 < prefixIndex.size() && ptr1 < suffixIndex.size()){
                int prefixWeight = prefixIndex.get(ptr0);
                int suffixWeight = suffixIndex.get(ptr1);
                if(prefixWeight == suffixWeight){
                    cache.put(key, prefixWeight);
                    return prefixWeight;
                }
                else if(prefixWeight > suffixWeight){
                    ptr0++;
                }
                else{
                    ptr1++;
                }
            }
            cache.put(key, -1);
            return -1;
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
