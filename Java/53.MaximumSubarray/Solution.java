/* Dynamic Programming: O(n)
 * 1. dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
 */

import java.util.*; // Stack

public class Solution {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int globalMax = nums[0];
        for(int i = 1; i < nums.length; ++i){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            globalMax = Math.max(dp[i], globalMax);
        }
        return globalMax;
    }

    public static void main(String[] args){
        int[] nums = {2, 1, -3, 4, -1, 2, 1, -5, 4};
        Solution sol = new Solution();
        
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maximum sum of subarray: " + sol.maxSubArray(nums));
    }
}
