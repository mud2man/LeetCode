/* Dynamic programming: Time:O(n*m), Space:O(n*m)
 * 1. It's a Knapsack problem
 * 
 * ex: amount = 5,  coins = {1, 2, 5}
 */         

import java.util.*;

public class Solution {
    public int change(int amount, int[] coins) {
        if(amount == 0){
            return 1;
        }
        
        int[][] dp = new int[amount + 1][coins.length];
        for(int i = 1; i <= amount; ++i){
            for(int j = 0; j < coins.length; ++j){
                int coin = coins[j];
                if(coin == i){
                    dp[i][j] = (j > 0)? 1 + dp[i][j - 1]: 1;
                }
                else if(coin < i){
                    int remain = i - coin;
                    dp[i][j] = (j > 0)? dp[remain][j] + dp[i][j - 1]: dp[remain][j];
                }
                else{
                    dp[i][j] = (j > 0)? dp[i][j - 1]: 0;
                }
            }
        }

        return (coins.length > 0)? dp[amount][coins.length - 1]: 0;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int amount = 5;
        int[] coins = {1, 2, 5};
        System.out.println("amount: " + amount);
        System.out.println("coins: " + Arrays.toString(coins));
        System.out.println("combinations#: " + sol.change(amount, coins));
    }
}
