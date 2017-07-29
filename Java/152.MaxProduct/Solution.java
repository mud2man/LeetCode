/* Dynamic programming: O(n)
 * 1. Use dp[][], where dp[i][0] is the minimum product containing nums[i], dp[i][1] is the maximum product containing nums[i]
 * 2. Traverse nums and keeping update dp[i][0] and dp[i][1]
 */

import java.util.*;


public class Solution {
	public int maxProduct(int[] nums) {
        int[][] dp = new int[nums.length][2];
        int globalMax = nums[0];
        int num0, num1, num2, localMax, localMin;
        
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for(int i = 1; i < nums.length; ++i){
            num0 = nums[i];
            num1 = nums[i] * dp[i - 1][0];
            num2 = nums[i] * dp[i - 1][1];
            localMax = Math.max(num2, Math.max(num0, num1));
            localMin = Math.min(num2, Math.min(num0, num1));
            globalMax = Math.max(localMax, globalMax);
            dp[i][0] = localMin;
            dp[i][1] = localMax;
        }
        return globalMax;
    }

    public static void main(String[] args){
        Solution sol;
        int maximum;
        int[] nums = {2, 3, -2, 4};

        sol = new Solution();
        
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maximum product:" + sol.maxProduct(nums));
    }
}
