/* MinHeap + HashMap: Time = O(nlogk), Space = O(n)
 * 1. Have a hashmap to store the frequency of every word
 * 2. Have a minHeap ordered by the frequency and alphabetical order 
 * 3. Retrieve the answer from the minHeap
 */

import java.util.*;

public class Solution{
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();
        for(String word: words){
            frequencyMap.putIfAbsent(word, 0);
            frequencyMap.put(word, frequencyMap.get(word) + 1);
        } 
        
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((x, y) -> {
            return (x.getValue() != y.getValue())? x.getValue().compareTo(y.getValue()): y.getKey().compareTo(x.getKey());
        });
        for(Map.Entry<String, Integer> entry: frequencyMap.entrySet()){
            minHeap.add(entry);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        
        LinkedList<String> topK = new LinkedList<String>();
        while(!minHeap.isEmpty()){
            Map.Entry<String, Integer> entry = minHeap.poll();
            topK.addFirst(entry.getKey());
        }
        return topK;
    }
  
    public static void main(String[] args){
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        Solution sol = new Solution();
        System.out.println("k: " + k);
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("topK: " + sol.topKFrequent(words, k));
	}
}
