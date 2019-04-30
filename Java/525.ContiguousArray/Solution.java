/* Hash: Time:O(n), Space:O(n)
 * 1. Accumulate sum with 1 if nums[i] = 1, -1 if nums[i] = 0
 * 2. Have "sum2Index" to record the minimum index with key = "sum"
 * 3. Because the sum of the subarray with equal number of 0 and 1 is 0, the length is (i - sum2Index.get(sum))
 * 
 * ex: [0, 0, 1, 0] => sum = [-1, -2, -1, -2]
 */

import java.util.*;

public class Solution{
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> sum2Index = new HashMap<>();
        int maxLength = 0;
        sum2Index.put(0, -1);
        for(int i = 0, sum = 0; i < nums.length; ++i){
            sum += (nums[i] == 0)? -1: 1;
            if(sum2Index.containsKey(sum)){
                int currLength = i - sum2Index.get(sum);
                maxLength = Math.max(maxLength, currLength);
            }
            sum2Index.putIfAbsent(sum, i);
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
