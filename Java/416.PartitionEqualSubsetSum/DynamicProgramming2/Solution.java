/* Dynamic Programming (0/1 knapsack): Time:O(sum*n), Space:O(sum), where sum = sum of nums, n = length of nums
 * 1. dp[y] = if sum = y can be added by the nums[0] ~ nums[i]
 * 2. Update dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]]
 */          

import java.util.*;

public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        if(sum % 2 == 1){
            return false;
        }
        
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        dp[0][0] = true;
        if(nums[0] <= target){
            dp[0][nums[0]] = true;
        }
        for(int i = 1; i < nums.length; ++i){
            for(int j = 0; j <= target; ++j){
                //without nums[i]
                dp[i][j] = dp[i - 1][j];
                //with nums[i]
                dp[i][j] |= (j - nums[i] >= 0)? dp[i - 1][j - nums[i]]: false;
            }
        }
        return dp[nums.length - 1][target];
    }
  
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 5, 11, 5};
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can partition equal: " + sol.canPartition(nums));
    }
}
