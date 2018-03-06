/* Dynamic programming: Time:O(n^2), Space:O(n^2)
 * 1. Have 2D array "sums" to record the sum of nums[x], nums[x + 1], ... nums[y]
 * 2. Let dp[y][x] = the best socre of the first player taking nums[x, x + 1, x + 2, ...y]
 * 3. dp[y][x] = max(nums[x] + (sums[y][x + 1] - dp[y][x + 1]), nums[y] + (sums[y - 1][x] - dp[y - 1][x]));
 */

import java.util.*;

public class Solution{
    public boolean PredictTheWinner(int[] nums) {
        int length = nums.length;
        int[][] sums = new int[length][length];
        
        for(int y = 0; y < length; y++){
            int sum = 0;
            for(int x = y; x >= 0; x--){
                sum += nums[x];
                sums[y][x] = sum;
            }
        }
        
        //dp[y][x] = the best socre of the first player taking nums[x, x + 1, x + 2, ...y]
        int[][] dp = new int[length][length];
        for(int y = 0; y < length; y++){
            dp[y][y] = nums[y];
            for(int x = y - 1; x >= 0; x--){
                dp[y][x] = Math.max(nums[x] + (sums[y][x + 1] - dp[y][x + 1]), nums[y] + (sums[y - 1][x] - dp[y - 1][x]));
            }
        }
        
        return dp[length - 1][0] >= (sums[length - 1][0] - dp[length - 1][0]);
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 5, 233, 7};
        
        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can I win ?: " + sol.PredictTheWinner(nums));
    }
}
