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
            num2Freq.putIfAbsent(num, 0);
            num2Freq.put(num, num2Freq.get(num) + 1);
        }
        
        Map<Integer, Set<Integer>> freq2Nums = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry: num2Freq.entrySet()){
            int freq = entry.getValue();
            int num = entry.getKey();
            freq2Nums.putIfAbsent(freq, new HashSet<>());
            freq2Nums.get(freq).add(num);
        }
        
        List<Integer> topK = new ArrayList<>();
        for(int i = nums.length; i > 0 && topK.size() < k; --i){
            if(freq2Nums.containsKey(i)){
                for(int num: freq2Nums.get(i)){
                    if(topK.size() < k){
                        topK.add(num);
                    }
                    else{
                        break;
                    }
                }
            }
        }
        return topK;
    }

    public static void main(String[] args){
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
