/* Dynamic programming: O(n * amount), where n is the number of coin type
 * 1. dp[i] = the minimum number of coins with the amount = i
 * 2. dp[i] = min(dp[i - coin#0] + 1, dp[i - coin#1] + 1, ...) 
 */

import java.util.*;

public class Solution{
    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        for(int i = 1; i <= amount; ++i){
            dp[i] = -1;
            for(int coin: coins){
                if(i - coin >= 0 && dp[i - coin] != -1){
                    dp[i] =(dp[i] == -1)? dp[i - coin] + 1: Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("coins: " + Arrays.toString(coins));
        System.out.println("amount: " + amount);
        System.out.println("fewest number of coins: " + sol.coinChange(coins, amount));
    }
}
