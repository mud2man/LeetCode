/* HashMap and minimum heap O(nlogk)
 * 1. Collect the frequency hash map from the input array
 * 2. Use minimum heap to select the top K frequent element among the frequency hash map
 */

import java.util.*;

class FreqComparator implements Comparator<Map.Entry<Integer, Integer>>{
    
    @Override
    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2 ){
        return o1.getValue() - o2.getValue();
    }
}

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> countMap;
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap;
        List<Integer> topK;
        
        countMap = new HashMap<Integer, Integer>();
        minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(new FreqComparator());
        topK = new ArrayList<Integer>();
        
        for(int num: nums){
            if(countMap.containsKey(num)){
               countMap.put(num, countMap.get(num) + 1); 
            }
            else{
               countMap.put(num, 1);  
            }
        }
        
        for(Map.Entry<Integer, Integer> entry: countMap.entrySet()){
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
        
        if(minHeap.size() < k){
            return null;
        }
        
        while(!minHeap.isEmpty()){
            topK.add(minHeap.poll().getKey());
        }
        
        return topK;
    }

    public static void main(String[] args)
    {
        Solution sol;
        List<Integer> topK;
        int[] nums = {1, 1, 2, 2, 3};
        int k;

        k = 2;
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));

        topK = sol.topKFrequent(nums , k);
        
        System.out.println("top" + k + ": " + topK);
	}
}
