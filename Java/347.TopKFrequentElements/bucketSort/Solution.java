/* Bucket sort:Time:O(n), Space:O(n)
 * 1. Collect the frequency hash map "num2Freq" from the input array
 * 2. Get the frequency-to-numbers "freq2Nums" from "num2Freq"
 * 3. Traverse freq from nums.length to 1, and retrieve numbers from frequency-to-numbers
 */

import java.util.*;

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> num2Freq = new HashMap<>();
        for(int num: nums){
            num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
        }
        Map<Integer, List<Integer>> freq2Nums = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry: num2Freq.entrySet()){
            int num = entry.getKey();
            int freq = entry.getValue();
            freq2Nums.putIfAbsent(freq, new ArrayList<>());
            freq2Nums.get(freq).add(num);
        }
        List<Integer> topK = new ArrayList<>();
        for(int i = nums.length; i > 0 && topK.size() < k; --i){
            if(freq2Nums.containsKey(i)){
                List<Integer> numsWithFreqI = freq2Nums.get(i);
                for(int j = 0; j < numsWithFreqI.size() && topK.size() < k; ++j){
                    topK.add(numsWithFreqI.get(j));
                }
            }
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
