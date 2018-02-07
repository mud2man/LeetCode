/* Hash: Time:O(n), Space:O(1)
 * 1. Accumulate sum with 1 if nums[i] = 1, -1 if nums[i] = 0
 * 2. Have "minIndexMap" to record the minimum index with key = "sum"
 * 3. Have "maxIndexMap" to record the maximum index with key = "sum"
 * 4. Because the sum of the subarray with equal number of 0 and 1 is 0, the length is maxIndexMap.get(sum) - minIndexMap.get(sum)
 * 5. We can update "maxLength" by traversing "minIndexMap"
 * ex: [0, 0, 1, 0] => sum = [-1, -2, -1, -2]
 *     minIndexMap = {-1: 0}, {-2: 1}
 *     maxIndexMap = {-1: 2}, {-2: 3}
 *     maxLength = max((2 - 0), (3 - 1))
 */

import java.util.*;

public class Solution{
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> minIndexMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> maxIndexMap = new HashMap<Integer, Integer>();
        int maxLength = 0;
        
        int sum = 0;
        for(int i = 0; i < nums.length; ++i){
            sum += (nums[i] == 0)? -1: 1;
            maxLength = (sum == 0)? i + 1: maxLength;
            minIndexMap.putIfAbsent(sum, i);
            maxIndexMap.put(sum, i);
        }
        
        for(Map.Entry<Integer, Integer> entry: minIndexMap.entrySet()){
            sum = entry.getKey();
            maxLength = Math.max(maxIndexMap.get(sum) - entry.getValue(), maxLength);
        }
        
        return maxLength;
    }
 
    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> subsets;
        int[] nums = {0, 1, 0};
        
        sol = new Solution();
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("maximum length of contiguous array: " + sol.findMaxLength(nums));
    }
}
