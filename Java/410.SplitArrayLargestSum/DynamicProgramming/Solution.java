/* Dynamic Programming: Time:O(n^2*m), Space: O(n*m). Leetcode has a batter solution using binary search, but it can handle negative
 * 1. dp[y][x] = the minimum largest sum split nums[0], nums[1], ... nums[x] with y partition
 * 2. Decide the last element "lastSum" first, and get "MinimumMaxSum" from max(lastSum, dp[y - 1][z - 1]). 
 * 3. Then pick the minimum "MinimumMaxSum" among all the combination of lastSum
 * 
 * ex: m = 2, nums = {7, 2, 5, 10, 8}
 *     dp = {{0, 0, 0,  0,  0}
 *           {7, 9, 14, 24, 32}
 *           {0, 7, 7,  14, 18}}
 */         

import java.util.*;

public class Solution {
    public int splitArray(int[] nums, int m) {
        int width = nums.length;
        int depth = m + 1;
        int[][] dp = new int[depth][width];
        
        int sum = 0;
        for(int x = 0; x < width; ++x){
            sum += nums[x];
            dp[1][x] = sum;
        }
        
        for(int y = 2; y < depth; ++y){
            int x;
            int max = nums[0];
            for(x = 0; x < y; ++x){
                max = Math.max(max, nums[x]);   
            }
            
            dp[y][y - 1] = max;
            for(x = y; x < width; ++x){
                int lastSum = 0;
                int MinimumMaxSum = Integer.MAX_VALUE;
                for(int z = x; z >= (y - 1); --z){
                    lastSum += nums[z];
                    if(z > 0){
                        MinimumMaxSum = Math.min(MinimumMaxSum, Math.max(lastSum, dp[y - 1][z - 1]));
                    }
                    else{
                        MinimumMaxSum = Math.min(MinimumMaxSum, lastSum);
                    }
                }
                dp[y][x] = MinimumMaxSum;
            }
        }
        
        return dp[depth - 1][width - 1];
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        
        sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("2:" + m);
        System.out.println("minimum largest sum: " + sol.splitArray(nums, m));
    }
}
