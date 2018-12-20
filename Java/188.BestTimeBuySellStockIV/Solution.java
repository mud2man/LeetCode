/* Dynamic Programming: Time:O(n*k), Space:O(n)
 * 1. Have a 2D array dp, where dp[y][x] = the pair (max saving with stock, max saving without stock) considering at most y transaction and prices[0 - x]
 * 2. If k >= (prices.length + 1) / 2, we can call getMaxProfit
 * 3. If not, we update dp[y][x][0] = max(dp[y - 1][x][0], dp[y][x - 1][0], dp[y][x - 1][1] - prices[x])
 * 4. And we update dp[y][x][1] = max(dp[y - 1][x][1], dp[y][x - 1][1], dp[y - 1][x - 1][0] + prices[x])
 *
 * ex: prices = {3, 2, 6, 0, 5, 3}
 *
 *      3       2       6        0      5      3
 * 0:  (-3, 0) (-2, 0) (-2, 0) (0, 0) (0, 0) (0, 0)
 * 1:  (-3, 0) (-2, 0) (-2, 4) (4, 4) (4, 5) (4, 5)
 * 2:  (-3, 0) (-2, 0) (-2, 4) (4, 4) (4, 9) (4, 9)
 */

import java.util.*; // Stack

public class Solution {
    private int getMaxProfit(int[] prices){
        int profit = 0;
        for(int i = 0; i < prices.length - 1; ++i){
            profit += (prices[i + 1] > prices[i])? prices[i + 1] - prices[i]: 0;
        }
        return profit;
    }
    
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        if(k >= (prices.length + 1) / 2){
            return getMaxProfit(prices);
        }
        
        // 2D array of pair (max saving with stock, max saving without stock)
        int[][][] dp = new int[2][prices.length][2];
        int min = Integer.MAX_VALUE;
        for(int x = 0; x < prices.length; ++x){
            min = Math.min(min, prices[x]);
            dp[0][x][0] = -min;
            dp[0][x][1] = 0;
        }
        
        for(int y = 1; y < k + 1; ++y){
            dp[y % 2][0][0] = dp[(y - 1) % 2][0][0];
            dp[y % 2][0][1] = dp[(y - 1) % 2][0][1];
            for(int x = 1; x < prices.length; ++x){
                //get the max saving with stock given at most y transactions
                dp[y % 2][x][0] = Math.max(dp[(y - 1) % 2][x][0], dp[y % 2][x - 1][0]);
                dp[y % 2][x][0] = Math.max(dp[y % 2][x][0], dp[y % 2][x - 1][1] - prices[x]);
                
                //get the max saving without stock given at most y transactions
                dp[y % 2][x][1] = Math.max(dp[(y - 1) % 2][x][1], dp[y % 2][x - 1][1]);
                dp[y % 2][x][1] = Math.max(dp[y % 2][x][1], dp[(y - 1) % 2][x - 1][0] + prices[x]);
            }
        }
        return dp[k % 2][prices.length - 1][1];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] prices = {3, 2, 6, 5, 0, 3};
        int k = 2;
        System.out.println("prices: " + Arrays.toString(prices));
        System.out.println("k: " + k);
        System.out.println("max profit: " + sol.maxProfit(k, prices));
    }
}
