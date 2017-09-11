/* Dynamic programming: O(n^2)
 * 1. dp[i] = the number of possible combinations with sum = i
 * 2. dp[i] = dp[i - nums[0]] + dp[i - nums[1]] + ...
 */

import java.util.*;
 
public class Solution{
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.sort(nums);
        
        dp[0] = 1;
        for(int i = 1; i <= target; ++i){
            for(int num: nums){
                if(i - num >= 0){
                    dp[i] += dp[i - num];
                }
                else{
                    break;
                }
            }
        }
        return dp[target];
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 2, 3};
        int target = 4;

        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums)); 
        System.out.println("target: " + target); 
        System.out.println("combination count: " + sol.combinationSum4(nums, target));
    }
}
