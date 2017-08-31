/* Dynamic programming: Complexity: O(n*logn)
 * 1. dp[i] = min(dp[i -j*j] + 1, all j, s.t. j*j <= i) 
 */

import java.util.*; // Stack

public class Solution{
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        
        for(int i = 1; i <= n; ++i){
            dp[i] = i;
            for(int root = 1; (root * root) <= i; root++){
                int square = root * root;
                dp[i] = Math.min(dp[i], dp[i - square] + 1);       
            }
        }
        return dp[n];
    }

    public static void main(String[] args){
        Solution sol;
        int n = 13;
        
        sol = new Solution();
        int squareNum = sol.numSquares(n); 
        
        System.out.println("n: " +  n);
        System.out.println("squareNum: " + squareNum);
    }
}
