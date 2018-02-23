/* Trie Tree: Time:O(n), Space:O(n), where n is number of characters
 * 1. Have a trie tree, wwhere the node contains "minHeap", and "child" array
 * 2. In the minHeap, the node contains "sentence" and its according hot degree. Also, we need have its comparator
 * 3. In constructor "AutocompleteSystem", insert all the sentecnes into trie tree, and "hotDegreeMap"
 * 4. In input, if character is '#', insert the new sentence into trie tree. Otherwise, retunr the minHeap of the current node
 * 
 */         

import java.util.*;

public class Solution {
    HashMap<String, Integer> hotDegreeMap = new HashMap<String, Integer>();
        
    private class HeapNode{
        String sentence;
        int hotDegree;
        HeapNode(String s, int h){sentence = s; hotDegree = h;}
    }
    
    private class HeapComparator implements Comparator<HeapNode>{
        public int compare(HeapNode x, HeapNode y){
            if(x.hotDegree != y.hotDegree){
                return x.hotDegree - y.hotDegree;
            }
            else{
                return y.sentence.compareTo(x.sentence);
            }
        }
    } 
    
    private class TrieNode{
        char character;
        PriorityQueue<HeapNode> minHeap;
        TrieNode[] child;
        TrieNode(char c){character = c; child = new TrieNode[27]; minHeap = new PriorityQueue<HeapNode>(new HeapComparator());}
    }
    
    TrieNode trieTree;
    
    private void insert(TrieNode root, String sentence, int hotDegree){
        List<TrieNode> path = new ArrayList<TrieNode>();
        for(int i = 0; i < sentence.length(); ++i){
            char c = sentence.charAt(i);
            int position = (c == ' ')? 0: c - 'a' + 1;
            root.child[position] = (root.child[position] == null)? new TrieNode(c): root.child[position];
            root = root.child[position];
            path.add(root);
        }

        HeapNode heapNode = new HeapNode(sentence, hotDegree);
        for(TrieNode node: path){
            HashMap<String, HeapNode> heapNodeMap = new HashMap<String, HeapNode>();
            heapNodeMap.put(sentence, heapNode);
            while(!node.minHeap.isEmpty()){
                HeapNode n = node.minHeap.poll();
                heapNodeMap.putIfAbsent(n.sentence, n);
            }
            
            for(Map.Entry<String, HeapNode> entry: heapNodeMap.entrySet()){
                node.minHeap.add(entry.getValue());
            }
            
            if(node.minHeap.size() > 3){
               node.minHeap.poll(); 
            }
        }
    }
    
    public Solution(String[] sentences, int[] times) {
        trieTree = new TrieNode(' ');
        for(int i = 0; i < sentences.length; ++i){
            String sentence = sentences[i];
            int hotDegree = times[i];
            insert(trieTree, sentence, hotDegree);
            hotDegreeMap.put(sentence, hotDegree);
        }
        newSentence = new StringBuilder("");
        currentNode = trieTree;
    }
    
    StringBuilder newSentence;
    TrieNode currentNode;
    
    public List<String> input(char c) {
        if(c == '#'){
            String s = newSentence.toString();
            hotDegreeMap.putIfAbsent(s, 0);
            hotDegreeMap.put(s, hotDegreeMap.get(s) + 1);
            insert(this.trieTree, s, hotDegreeMap.get(s));
            currentNode = trieTree;
            newSentence = new StringBuilder("");
            return new ArrayList<String>();
        }
        else{
            int position = (c == ' ')? 0: c - 'a' + 1;
            newSentence.append(c);
            currentNode.child[position] = (currentNode.child[position] == null)? new TrieNode(c): currentNode.child[position];
            currentNode = currentNode.child[position];
            LinkedList<String> top3 = new LinkedList<String>();
            PriorityQueue<HeapNode> newHeap = new PriorityQueue<HeapNode>(new HeapComparator());
            PriorityQueue<HeapNode> previousHeap = currentNode.minHeap;
            while(!previousHeap.isEmpty()){
                HeapNode heapNode = previousHeap.poll();
                top3.addFirst(heapNode.sentence);
                newHeap.add(heapNode);
            }
            currentNode.minHeap = newHeap;
            return top3;
        }
    }

    public static void main(String[] args){
        Solution sol;
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        
        sol = new Solution(sentences, times);
        System.out.println("sentences:" + Arrays.toString(sentences));
        System.out.println("times:" + Arrays.toString(times));
        System.out.println("input('i'): " + sol.input('i'));
        System.out.println("input(' '): " + sol.input(' '));
        System.out.println("input('a'): " + sol.input('a'));
        System.out.println("input('#'): " + sol.input('a'));
    }
}
