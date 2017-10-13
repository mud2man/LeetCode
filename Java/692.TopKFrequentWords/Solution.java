/* MinHeap + HashMap: time = O(nlogk), space = O(n)
 * 1. Have a hashmap to store the frequency of every word
 * 2. Have a minHeap ordered by the frequency and alphabetical order 
 * 3. Retrieve the answer from the minHeap
 */

import java.util.*;

public class Solution{
    public List<String> topKFrequent(String[] words, int k) {
        LinkedList<String> topK = new LinkedList<String>();
        HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<Map.Entry<String, Integer>>(
            new Comparator<Map.Entry<String, Integer>>(){    
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                    if(o1.getValue() != o2.getValue()){
                        return o1.getValue() - o2.getValue();
                    }
                    else{
                        return o2.getKey().compareTo(o1.getKey());
                    }
                }
            }
        );
        
        for(String word: words){
            if(!frequencyMap.containsKey(word)){
                frequencyMap.put(word, 0);
            }
            frequencyMap.put(word, frequencyMap.get(word) + 1);
        }
        
        for(Map.Entry<String, Integer> entry: frequencyMap.entrySet()){
            minHeap.add(entry);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        
        while(!minHeap.isEmpty()){
            Map.Entry<String, Integer> entry = minHeap.poll();
            topK.addFirst(entry.getKey());
        }
        
        return topK;
    }

    public static void main(String[] args){
        Solution sol;
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;

        sol = new Solution();
        System.out.println("k: " + k);
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("topK: " + sol.topKFrequent(words, k));
	}
}
