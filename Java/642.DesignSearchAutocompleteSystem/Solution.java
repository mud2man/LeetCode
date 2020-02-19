/* Trie Tree: Time:O(nlogm), Space:O(n*m*l), where n is characters#, m is sentence#, l is length of snetence
 * 1. Have a trie tree, where the node contains "maxHeap", and "child" array
 * 2. In the maxHeap, the node contains "sentence" and its according hot degree. Also, we need have its comparator
 * 3. In constructor "AutocompleteSystem", insert all the sentecnes into trie tree, and "hotDegreeMap"
 * 4. In input, if character is '#', insert the new sentence into trie tree. Otherwise, retunr the minHeap of the current node
 */         

import java.util.*;

public class Solution {
    private class HeapNode{
        Integer freq;
        String sentence;
        HeapNode(int f, String s){freq = f; sentence = s;}
    }
    private class WordComparator implements Comparator<HeapNode>{
        public int compare(HeapNode x, HeapNode y){
            return (x.freq != y.freq)? y.freq.compareTo(x.freq): x.sentence.compareTo(y.sentence);
        }
    }
    private class TrieNode{
        TrieNode child[];
        PriorityQueue<HeapNode> maxHeap;
        TrieNode(){child = new TrieNode[27]; maxHeap = new PriorityQueue<>(new WordComparator());}
    }
    TrieNode root;
    TrieNode ptr;
    StringBuilder path;
    Map<String, HeapNode> sentence2HeapNode;
    
    //remove heapNode and add, update sentence2HeapNode
    private void insert(TrieNode root, HeapNode newHeapNode){
        String sentence = newHeapNode.sentence;
        HeapNode oldHeapNode = sentence2HeapNode.getOrDefault(sentence, null);
        for(char c: sentence.toCharArray()){
            int idx = (c != ' ')? c - 'a': 26;
            root.child[idx] =(root.child[idx] == null)? new TrieNode(): root.child[idx];
            root = root.child[idx];
            root.maxHeap.remove(oldHeapNode);
            root.maxHeap.add(newHeapNode);
        }
        sentence2HeapNode.put(sentence, newHeapNode);
    }

    public AutocompleteSystem(String[] sentences, int[] times){
        root = new TrieNode();
        sentence2HeapNode = new HashMap<>();
        for(int i = 0; i < times.length; ++i){
            HeapNode heapNode = new HeapNode(times[i], sentences[i]);
            insert(root, heapNode);
        }
        ptr = root;
        path = new StringBuilder();
    }
    
    public List<String> input(char c) {
        List<String> top3 = new ArrayList<>();
        if(c == '#'){
            String sentence = path.toString();
            path = new StringBuilder();
            HeapNode heapNode = sentence2HeapNode.getOrDefault(sentence, new HeapNode(0, sentence));
            heapNode.freq++;
            insert(root, heapNode);
            ptr = root;
        }else{
            int idx = (c != ' ')? c - 'a': 26;
            path.append(c);
            ptr.child[idx] =(ptr.child[idx] == null)? new TrieNode(): ptr.child[idx];
            ptr = ptr.child[idx];
            Deque<HeapNode> temp = new LinkedList<>();
            PriorityQueue<HeapNode> maxHeap = ptr.maxHeap;
            while(!maxHeap.isEmpty() && top3.size() < 3){
                HeapNode front = maxHeap.poll();
                top3.add(front.sentence);
                temp.add(front);
            }
            while(!temp.isEmpty()){
                maxHeap.add(temp.pollFirst());
            }
        }
        return top3;
    }
 
    public static void main(String[] args){
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        Solution sol = new Solution(sentences, times);
        System.out.println("sentences:" + Arrays.toString(sentences));
        System.out.println("times:" + Arrays.toString(times));
        System.out.println("input('i'): " + sol.input('i'));
        System.out.println("input(' '): " + sol.input(' '));
        System.out.println("input('a'): " + sol.input('a'));
        System.out.println("input('#'): " + sol.input('a'));
    }
}
