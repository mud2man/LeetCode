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
        if(coins.length == 0){
            return (amount == 0)? 1: 0;
        }
        
        int[][] dp = new int[coins.length][amount + 1];
        for(int y = 0; y < dp.length; ++y){
            dp[y][0] = 1;
            for(int x = 1; x <= amount; ++x){
                int takeZeroCoinY = (y > 0)? dp[y - 1][x]: 0;
                int takeOneOrMoreCoinY = (x >= coins[y])? dp[y][x - coins[y]]: 0;
                dp[y][x] = takeZeroCoinY + takeOneOrMoreCoinY;
            }
        }
        return dp[coins.length - 1][amount];
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
