/* Dynamic Programming: Time:O(n^3), Space:O(n^2)
 * 1. dp[i][j] = the maximum coin can earned from the range [j, i]
 * 2. To update, we need to traverse from j to i with index k, where k is the last ballon we burst 
 * 3. Evevry traverse, current coins = ballons[k] * ballons[j - 1] * ballons[i + 1] + dp[k - 1][j](left) + dp[i][k + 1](right)
 * 4. Maintain the maximum value during the traverse
 */         

import java.util.*;

public class Solution {
    public int maxCoins(int[] nums) {
        int[] ballons = new int[nums.length + 2];
        ballons[0] = 1;
        for(int i = 1; i < ballons.length - 1; ++i){
             ballons[i] = nums[i - 1];   
        }
        ballons[ballons.length - 1] = 1;
        
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        for(int i = 1; i < ballons.length - 1; ++i){
            for(int j = i; j > 0; --j){
                int max = 0;
                for(int k = j; k <= i; ++k){
                    int left = (k > j)? dp[k - 1][j]: 0;
                    int right = (k < i)? dp[i][k + 1]: 0;
                    int curr = ballons[k] * ballons[j - 1] * ballons[i + 1] + left + right;
                    max = Math.max(max, curr);
                }
                dp[i][j] = max;
            }
        }
        
        return dp[nums.length][1];
    }
 
    public static void main(String[] args){
        Solution sol= new Solution();
        int[] nums = {3, 1, 5, 8}; 
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maximum coin: " + sol.maxCoins(nums));
    }
}
