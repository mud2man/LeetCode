/* Dynamic Programming (0/1 knapsack): Time:O(sum*n), Space:O(sum), where sum = sum of nums, n = length of nums
 * 1. dp[y] = if sum = y can be added by the nums[0] ~ nums[i]
 * 2. Update dp[i] from i = target to i = 0 to avoid interfere, update dp n times given a new number nums[i]
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
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for(int num: nums){
            for(int i = target; i >= 0; --i){
                if(i - num >= 0 && dp[i - num] == true){
                    dp[i] = true;
                }
            }
        }
        return dp[target];
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 5, 11, 5};
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can partition equal: " + sol.canPartition(nums));
    }
}
