/* Dynamic programming: Time:O(n*m), Space:O(n*m)
 * 1. It's a Knapsack problem
 * 2. dp[i][j] = the combination count with value j and considering coins[0], coins[1], ... coins[i]
 * 3. dp[i][j] = dp[i - 1][j](whithout coins[i]) + dp[i][j - coin](with al least coins[i])
 *
 * ex: amount = 5,  coins = {1, 2, 5}
 * dp: [0, 1, 1, 1, 1, 1]
 *     [0, 1, 2, 2, 3, 3]
 *     [0, 1, 2, 2, 3, 4]
 */         

import java.util.*;

public class Solution {
    public int change(int amount, int[] coins) {
        if(amount == 0){
            return 1;
        }
        
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i < coins.length; ++i){
            int coin = coins[i];
            for(int j = 0; j <= amount; ++j){
                //combination count whithout coins[i];
                dp[i][j] = (i > 0)? dp[i - 1][j]: 0;
                
                //combination count with at least coins[i];
                if(coin == j){
                    dp[i][j] += 1;
                }
                else if(coin < j){
                    dp[i][j] += dp[i][j - coin];
                }
            }
        }

        return (coins.length > 0)? dp[coins.length - 1][amount]: 0;
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
