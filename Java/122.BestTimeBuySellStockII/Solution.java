/* Dynamic programming: Time:O(n), Space:O(1)
 * 1. Have two array "cost" and "profit", where cost[i] = max saving with stock, profit[i] = max saving without stock
 * 2. profit[i] = Math.max(profit[i - 1], cost[i - 1] + prices[i])
 * 3. cost[i] = Math.max(cost[i - 1], profit[i - 1] - prices[i])
 */          

import java.util.*; // Stack

public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        
        int[] cost = new int[2];
        cost[0] = -prices[0];
        int[] profit = new int[2];
        for(int i = 1; i < prices.length; ++i){
            profit[i % 2] = Math.max(profit[(i - 1) % 2], cost[(i - 1) % 2] + prices[i]);
            cost[i % 2] = Math.max(cost[(i - 1) % 2], profit[(i - 1) % 2] - prices[i]);
        }
        return profit[(prices.length - 1) % 2];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("prices: " + Arrays.toString(prices));
        System.out.println("max profit: " + sol.maxProfit(prices));
    }
}
