/* Dynamaic programming: O(n)
 * 1. Have 2 arrays oneStockProfitDp and noStockProfitDp
 * 2. oneStockProfitDp[i] = the maximum profit with a stock, when reaching the price[i] so far
 * 3. noStockProfitDp[i] = the maximum profit without a stock, when reaching the price[i] so far
 * 4. oneStockProfitDp[i] = Math.max(oneStockProfitDp[i - 1], noStockProfitDp[i - 2] - prices[i]), becuase of cooldown, so (i - 2)
 * 5. noStockProfitDp[i] = Math.max(noStockProfitDp[i - 1], oneStockProfitDp[i - 1] + prices[i]) 
 * 4. noStockProfitDp[length - 1] is the answer
 *
 * ex: {1, 2, 3, 0, 2}
 * oneStockProfitDp[] = {-1, -1, -1, 1, 1} 
 * noStockProfitDp[] = {0, 1, 2, 2, 3} 
 *
 */

import java.util.*;

public class Solution{
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int[] noStockProfitDp = new int[length];
        int[] oneStockProfitDp = new int[length];
        
        if(prices.length < 2){
            return 0;
        }
        
        oneStockProfitDp[0] = -prices[0];
        oneStockProfitDp[1] = Math.max(-prices[0], -prices[1]);
        noStockProfitDp[0] = 0;
        noStockProfitDp[1] = Math.max(0, prices[1] - prices[0]);
        for(int i = 2; i < length; ++i){
            oneStockProfitDp[i] = Math.max(oneStockProfitDp[i - 1], noStockProfitDp[i - 2] - prices[i]);
            noStockProfitDp[i] = Math.max(noStockProfitDp[i - 1], oneStockProfitDp[i - 1] + prices[i]);
        }

        return noStockProfitDp[length - 1];
    }

    public static void main(String[] args){
        Solution sol;
        int[] prices = {1, 2, 3, 0, 2};
        
        sol = new Solution();
        System.out.println("prices: " + Arrays.toString(prices));
        System.out.println("maximum profit: " + sol.maxProfit(prices));
    }
}
