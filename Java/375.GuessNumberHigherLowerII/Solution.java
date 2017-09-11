/* Dynamic programming: O(n^3)
 * 1. Let dp[ub][lb] = the minimum guaranteed money to win the game between range ub and lb
 * 2. dp[ub][lb] = min(i + max(dp[ub][i + 1], dp[i - 1][lb])), where lb <= i <= ub
 * 3. Physically, i means the first position of guessing
 * 4. The solution is dp[n][1]
 */

import java.util.*;
 
public class Solution{
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 2][n + 2];
        
        for(int ub = 0; ub <= n; ++ub){
            dp[ub][ub] = 0;
            for(int lb = ub - 1; lb > 0; --lb){
                dp[ub][lb] = Integer.MAX_VALUE;
                for(int i = ub; i >= lb; --i){
                    dp[ub][lb] = Math.min(dp[ub][lb], i + Math.max(dp[ub][i + 1], dp[i - 1][lb]));
                }
            }
        }
        return dp[n][1];
    }
 
    public static void main(String[] args){
        Solution sol;
        int n;

        n = 5;
        sol = new Solution();
        
        System.out.println("minimum guaranteed money: " + sol.getMoneyAmount(n));
    }
}
