/* Dynamaic programming: Time:O(n), Space:O(1)
 * 1. Have 2 arrays oneStockDp and noStockDp
 * 2. oneStockDp[i] = the maximum profit with one stock, when reaching the price[i] so far
 * 3. noStockDp[i] = the maximum profit without stock, when reaching the price[i] so far
 * 4. oneStockDp[i] = max(oneStockDp[i - 1], noStockDp[i - 1] - prices[i])
 * 5. noStockDp[i] = max(noStockDp[i - 1], oneStockDp[i - 1] + prices[i] - fee), because transaction costs "fee"
 * 4. noStockDp[length - 1] is the answer
 *
 * ex: {1, 3, 2, 8, 4, 9}
 * oneStockDp[] = {-1, -1, -1, -1, 1, 1} 
 * noStockDp[]  = { 0,  0,  0,  5, 5, 8} 
 */

import java.util.*;

public class Solution{
    public int maxProfit(int[] prices, int fee) {
        int[] oneStockDp = new int[2];
        int[] noStockDp = new int[2];
        
        oneStockDp[0] = -prices[0];
        noStockDp[0] = 0;
        for(int i = 1; i < prices.length; ++i){
            oneStockDp[i % 2] = Math.max(oneStockDp[(i - 1) % 2], noStockDp[(i - 1) % 2] - prices[i]);
            noStockDp[i % 2] = Math.max(noStockDp[(i - 1) % 2], oneStockDp[(i - 1) % 2] + prices[i] - fee);
        }
        return noStockDp[(prices.length - 1) % 2]; 
    }

    public static void main(String[] args){
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        
        Solution sol = new Solution();
        System.out.println("prices: " + Arrays.toString(prices));
        System.out.println("fee: " + fee);
        System.out.println("maximum profit: " + sol.maxProfit(prices, fee));
    }
}
