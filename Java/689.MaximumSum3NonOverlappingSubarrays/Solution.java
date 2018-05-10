/* Dynamic programming: Time:O(n), Space:O(n)
 * 1. dp[i][j] = the maximum sum of i size-k subarray
 * 2. record[i][j] = the starting index of i-th subarray, where has maximum sum considering the nums[0, 1, ..., j]
 * 3. Udpate dp[i][j] if (sum + dp[i - 1][j - k] > dp[i][j - 1])
 * 4. For convience, we use 4D array to avoid special case of first subarray
 * 
 */

import java.util.*;

public class Solution{
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int length = nums.length;
        int[][] dp = new int[4][length];
        int[][] record = new int[4][length];
        
        for(int i = 1; i <= 3; ++i){
            int start = (i - 1) * k;
            int end = i * k;
            int sum = 0;
            for(int j = start; j < end; ++j){
                sum += nums[j];
            }
            
            dp[i][end - 1] = (i > 1)? dp[i - 1][start - 1] + sum: sum; 
            record[i][end - 1] = (i - 1) * k;
            for(int j = end; j < length; ++j){
                sum = sum - nums[j - k] + nums[j];  
                if(sum + dp[i - 1][j - k] > dp[i][j - 1]){
                    dp[i][j] = sum + dp[i - 1][j - k];
                    record[i][j] = j - k + 1;
                }
                else{
                    dp[i][j] = dp[i][j - 1];
                    record[i][j] = record[i][j - 1];
                }
            }
        }
      
        int[] ranges = new int[3];
        int index = length - 1;
        for(int y = 3; y > 0; --y){
            ranges[y - 1] = record[y][index];
            index = record[y][index] - 1;
        }
        
        return ranges;
    }
  
    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> subsets;
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;
        
        sol = new Solution();
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("three subarrays: " + Arrays.toString(sol.maxSumOfThreeSubarrays(nums, k)));
    }
}
