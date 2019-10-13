/* Dynamic Programming: Time:O(n^2), Space:O(n). LeetCode has a O(n) solution using math theory
 * 1. dp[i] = the maximum product of the integer i breaks
 * 2. For each dp[i], we can pick the last number j between 1 and i - 1, ane update dp[i] = max(dp[i - j] * j, (i - j) * j)
 * 3. dp[n] is our answer
 */

import java.util.*; // Stack

public class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for(int i = 3; i <= n; ++i){
            for(int j = 1; j < i; ++j ){
                dp[i] = Math.max(dp[i], dp[i - j] * j);
                dp[i] = Math.max(dp[i], (i - j) * j);
            }
        }
        return dp[n];
    }
 
    public static void main(String[] args){
        int n = 10;
        Solution sol = new Solution();
        System.out.println("n:" + n);
        System.out.println("integerBreak(" + n + "):" + sol.integerBreak(n));
    }
}
