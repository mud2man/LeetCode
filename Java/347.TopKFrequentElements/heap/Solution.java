/* HashMap and minimum heap O(nlogk). LeetCode has O(n) solution
 * 1. Collect the frequency hash map from the input array
 * 2. Use minimum heap to select the top K frequent element among the frequency hash map
 */

import java.util.*;

public class Solution {
    private class FreqComparator implements Comparator <Map.Entry<Integer, Integer>>{
        public int compare(Map.Entry<Integer, Integer> x, Map.Entry<Integer, Integer> y){
            return x.getValue() - y.getValue();
        }    
    }
    
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue(new FreqComparator());
        HashSet<Integer> heap = new HashSet<Integer>();
        
        for(int num: nums){
            frequencyMap.putIfAbsent(num, 0);
            frequencyMap.put(num, frequencyMap.get(num) + 1);
        }
        
        for(Map.Entry<Integer, Integer> entry: frequencyMap.entrySet()){
            if(minHeap.size() < k){
                minHeap.add(entry);
            }
            else{
                if(minHeap.peek().getValue() < entry.getValue()){
                    minHeap.poll();
                    minHeap.add(entry);
                } 
            }
        }
        
        List<Integer> topK = new ArrayList<Integer>();
        for(Map.Entry<Integer, Integer> entry: minHeap){
            topK.add(entry.getKey());
        }
        
        return topK;
    }

    public static void main(String[] args){
        int[] nums = {1, 1, 2, 2, 3};
        int k = 2;
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        List<Integer> topK = sol.topKFrequent(nums , k);
        System.out.println("top" + k + ": " + topK);
    }
}
