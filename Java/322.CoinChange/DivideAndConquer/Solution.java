/* Divide and Conquer: O(n ^ amount), where n is the number of coin type
 */

import java.util.*;

public class Solution{
    public int coinChange(int[] coins, int amount) {
        if(amount == 0){
            return 0;
        }else if(amount < 0){
            return -1;
        }else{
            int min = Integer.MAX_VALUE;
            for(int coin: coins){
                int count = coinChange(coins, amount - coin);
                if(count >= 0){
                    min = Math.min(min, count + 1);
                }
            }
            return (min != Integer.MAX_VALUE)? min: -1;
        }
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
