/* Dynamic programming: Time:O(n * n^0.5), Space:O(n)
 * 1. dp[i] = if Alice can win starting with i
 * 2. dp[i] = false if all dp[j] = true, where n - (n ^ 0.5 * n ^ 0.5) <= j <=  n - (1 * 1)
 * 3. dp[i] = true if any dp[j] = false, where n - (n ^ 0.5 * n ^ 0.5) <= j <=  n - (1 * 1)
 */     

import java.util.*; // Stack

public class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for(int i = 1; i <= n; ++i){
            int sqrtRoot = (int)Math.sqrt(i);
            boolean canWin = false;
            for(int j = sqrtRoot; j > 0; --j){
                if(dp[i - j * j] == false){
                    canWin = true;
                    break;
                }
            }
            dp[i] = canWin;
        }
        return dp[n];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 17;
        System.out.println("n:" + n);
        System.out.println("can Alice win:" + sol.winnerSquareGame(n));
    }
}
