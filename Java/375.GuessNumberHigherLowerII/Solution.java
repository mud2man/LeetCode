/* Dynamic programming: O(n^2) => It brute force, but keep all the sub solution in a 2-D array
 * 1. Let dp[lb][ub] = the minimum guaranteed money to win the game between range lb and ub
 * 2. dp[lb][ub] = min(i + max(dp[lb][i-1], dp[i+1][ub])), where lb <= i <= ub
 * 3. Physically, i means the first position of guessing
 * 4. The solution is dp[1][n]
 */

import java.util.*;
 
public class Solution{
    public int helper(int[][] dp, int lb, int ub){
        int min;
        int sum;
        int i;
        
        if(lb >= ub){
            return 0;
        }
        else if(dp[lb][ub] != 0){
            return dp[lb][ub];
        }
        else{
            min = Integer.MAX_VALUE;
            for(i = lb; i <= ub; ++i){
                sum = i + Math.max(helper(dp, lb, i - 1), helper(dp, i + 1, ub));
                min = Math.min(sum, min);
            }
            dp[lb][ub] = min;
            return dp[lb][ub];
        }
    }
    
    public int getMoneyAmount(int n) {
        int[][] dp;
        
        dp = new int[n + 1][n + 1];
        
        return helper(dp, 1, n); 
    }
 
    public static void main(String[] args){
        Solution sol;
        int n;

        n = 5;
        sol = new Solution();
        
        System.out.println("minimum guaranteed money: " + sol.getMoneyAmount(n));
    }
}
