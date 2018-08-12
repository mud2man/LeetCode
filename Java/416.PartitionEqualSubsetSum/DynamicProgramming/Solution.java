/* Dynamic Programming (0/1 knapsack): Time:O(sum*n), Space:O(sum), where sum = sum of nums, n = length of nums
 * 1. dp[y][x] = if nums[0], nums[1], ... nums[y] can have sub sum which is equal to x
 * 2. dp[y][x] = dp[y][x] | dp[y - 1][x - num];
 */          

import java.util.*;

public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int max = 0;
        for(int num: nums){
            sum += num;
            max = Math.max(max, num);
        }
        
        if(sum % 2 == 1 || max > sum / 2){
            return false;
        }
        
        int target = sum / 2;
        boolean[][] dp = new boolean[2][target + 1];
        dp[0][0] = true;
        if(nums[0] <= target){
           dp[0][nums[0]] = true; 
        }
        
        for(int y = 1; y < nums.length; ++y){
            int num = nums[y];
            dp[y % 2][0] = true;
            for(int x = 1; x <= target; ++x){
                dp[y % 2][x] = dp[(y - 1) % 2][x];
                if(x - num >= 0){
                   dp[y % 2][x] |= dp[(y - 1) % 2][x - num]; 
                }
            }
            if(dp[y % 2][target] == true){
                return true;
            }
        }
        return false;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 5, 11, 5};
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can partition equal: " + sol.canPartition(nums));
    }
}
