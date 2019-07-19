/* Trie: Time:O(n * l + m * l * logn), Space:O(l * n * n)
 * 1. Insert the processed word to trie tree. e.g., apple => '*apple', 'e*apple', 'le*apple', 'ple*apple', 'pple*apple', 'apple*apple'
 * 2. Process query word e.g.(a , e) => e*a
 * 3. Have a maximum heap in each node, and insert the weight during insert
 * 4. When searching, find the node and do  minHeap.peek() from the node
 */

import java.util.*;

public class Solution{
    private class MaxHeapComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return y.compareTo(x);
        }
    }
    
    private class TrieNode{
        TrieNode[] child;
        PriorityQueue<Integer> minHeap;
        TrieNode(){child = new TrieNode[27]; minHeap = new PriorityQueue<>(new MaxHeapComparator());}   
    }
    
    TrieNode root = new TrieNode();
    public Solution(String[] words) {
        int weight = 0;
        for(String word: words){
            StringBuilder wrap = new StringBuilder("*" + word);
            for(int i = word.length() - 1; i >= -1; --i){
                insert(root, wrap.toString(), weight);
                if(i >= 0){
                    wrap.insert(0, word.charAt(i));
                }
            }
            weight++;
        }
    }
    
    private void insert(TrieNode root, String word, int weight){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            int idx = (c == '*')? 26: c - 'a';
            if(curr.child[idx] == null){
                curr.child[idx] = new TrieNode();
            }
            curr = curr.child[idx];
            curr.minHeap.add(weight);
        }
    }
    
    private int search(TrieNode root, String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            int idx = (c == '*')? 26: c - 'a';
            if(curr.child[idx] == null){
                return -1; 
            }
            curr = curr.child[idx];
        }
        return curr.minHeap.peek();
    }
    
    public int f(String prefix, String suffix) {
        String wrap = suffix + "*" + prefix;
        return search(root, wrap);
    }
  
    public static void main(String[] args){
        String[] words = {"apple"};
        String[] prefix = {"a", "b"};
        String[] suffix = {"e", ""};
        Solution sol = new Solution(words);
        System.out.println("words[]: " + Arrays.toString(words));
        for(int i = 0; i < prefix.length; ++i){
            System.out.println("prefix:" + prefix[i] + ", suffix:" + suffix[i] + ", weight:" + sol.f(prefix[i], suffix[i]));
        }
    }
}
