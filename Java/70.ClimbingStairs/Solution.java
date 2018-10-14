/* Dynamic Programming: Time:O(n), Space:O(1)
 * 1. dp[i] = dp[i - 1] + dp[i - 2];
 */

import java.util.*;

public class Solution{
    public int climbStairs(int n) {
        int[] dp = new int[4];
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2; i < n; ++i){
            dp[i % 4] = dp[(i - 1) % 4] + dp[(i - 2) % 4];
        }
        return dp[(n - 1) % 4];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();;
        int n = 3;

        System.out.println("n: " + n);
        System.out.println("stairs: " + sol.climbStairs(n));
    }
}
