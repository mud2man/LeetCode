/* Dynamic programming: O(n^2)
 * 1. Consider the number of trees, with regard to which number is the root. 
 * 2. dp[i] = the number of trees with i nodes
 * 3. dp[0] = 1, dp[1] = 1
 * 4. dp[n] = dp[i] * dp[n - 1 - i], where 0 <= i <= (n - 1)
 *
 * ex: n = 4, dp[0] = 1, dp[1] = 1, dp[2] = 2, dp[3] = 5
 * {1, 2, 3}, 4 => dp[3]*dp[0] = 5
 * {1, 2}, 3, {4} => dp[2]*dp[1] = 2
 * {1}, 2, {3, 4} => dp[1]*dp[2] = 2
 * 1, {2, 3, 4} => dp[1]*dp[2] = 5
 */         

import java.util.*;

public class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2; i <= n; ++i){
            for(int j = i - 1; j >= 0; --j){
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
 
    public static void main(String[] args){
        Solution sol;
        int n;
        
        n = 4;
        sol = new Solution();

        System.out.println("n: " + n);
        System.out.println("number of unique BST's: " + sol.numTrees(n));
    }
}
