/* Dynamic programming: Time:O(n), Space:O(1)
 * 1. Have a array int[] saving, saving[0] = the max deposit when buy stock once, saving[1] = the max deposit when sell stock once
 * 2. saving[2] = the max deposit when buy stock twice, saving[3] = the max deposit when sell stock twice
 * 3. Rest saving[0] and saving[2] to Integer.MIN_VALUE, which means never but it before
 * 4. Update saving[3] with Math.max(saving[3], saving[2] + prices[i])
 * 5. Update saving[2] with Math.max(saving[2], saving[1] - prices[i]), if saving[1] != 0
 * 6. Update saving[1] with Math.max(saving[1], saving[0] + prices[i])
 * 7. Update saving[0] with Math.max(saving[0], -prices[i])
 * 8. maxProfit = max(maxProfit, saving[1],saving[2])
 */

import java.util.*;

public class Solution{
    public int maxProfit(int[] prices) {
        /*
         * saving[0]: the max deposit when buy stock once
         * saving[1]: the max deposit when sell stock once
         * saving[2]: the max deposit when buy stock twice
         * saving[3]: the max deposit when sell stock twice
         */

        int[] saving = new int[4];
        saving[0] = Integer.MIN_VALUE;
        saving[2] = Integer.MIN_VALUE; 
        int maxProfit = 0;
        
        for(int i = 0; i < prices.length; ++i){
            saving[3] = Math.max(saving[3], saving[2] + prices[i]);
            saving[2] = Math.max(saving[2], saving[1] - prices[i]);
            saving[1] = Math.max(saving[1], saving[0] + prices[i]);
            saving[0] = Math.max(saving[0], -prices[i]);
            maxProfit = Math.max(maxProfit, saving[1]);
            maxProfit = Math.max(maxProfit, saving[3]);
        }
        
        return maxProfit;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] prices = {0,2,1,7,3,6};

        sol = new Solution();

        System.out.println("prices: " + Arrays.toString(prices));
        System.out.println("max profit: " + sol.maxProfit(prices));
    }
}
