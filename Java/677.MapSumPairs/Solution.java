/* Trietree + Hashmap: sum=>time:O(prefix length), insert=>time:O(key length), space:O(n)
 * 1. Have a trie tree, and hash map to record all the inserted key-value
 * 2. Insert the difference of new val and old val
 * 
 */         

import java.util.*;

public class Solution {
    private class TrieTree{
        int sum;
        TrieTree[] child;
        TrieTree(int s){sum = s; child = new TrieTree[26];}
    }
    TrieTree tree;
    HashMap<String, Integer> map;
        
    /** Initialize your data structure here. */
    public Solution() {
        tree = new TrieTree(0);
        map = new HashMap<String, Integer>();
    }
    
    public void insert(String key, int val) {
        int prevVal = map.containsKey(key)? map.get(key): 0;
        map.put(key, val);
        val = val - prevVal;
        
        TrieTree node = tree;
        for(int i = 0; i < key.length(); ++i){
            char c = key.charAt(i);
            if(node.child[c - 'a'] == null){
                node.child[c - 'a'] = new TrieTree(0);
            }
            node = node.child[c - 'a'];
            node.sum += val;
        }
    }
    
    public int sum(String prefix) {
        TrieTree node = tree;
        for(int i = 0; i < prefix.length(); ++i){
            char c = prefix.charAt(i);
            if(node.child[c - 'a'] == null){
                return 0;
            }
            node = node.child[c - 'a'];
        }
        return node.sum;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        sol.insert("apple", 3);
        System.out.println("insert(apple, 3)");
        System.out.println("sum(ap):" + sol.sum("ap"));
        sol.insert("apple", 2);
        System.out.println("insert(app, 2)");
        System.out.println("sum(ap):" + sol.sum("ap"));
    }
}
