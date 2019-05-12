/* Dynamic Programming: Time:O(n^3), Space:O(n^2)
 * 1. dp[i] = maximum partition sum ending at A[i - 1]
 * 2. dp[i] = max(dp[j - 1 + 1] + count * max)), where i - K + 1 <= j <= i, count = (i - j + 1), max = max(A[j], A[j + 1], ..., A[i])
 */

import java.util.*; // Stack

public class Solution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int[] dp = new int[A.length + 1];
        for(int i = 0; i < A.length; ++i){
            int max = 0;
            for(int j = i; (j > i - K) && (j >= 0); --j){
                max = Math.max(max, A[j]);
                int count = i - j + 1;
                dp[i + 1] = Math.max(dp[i + 1], dp[j - 1 + 1] + count * max);
            }
        }
        return dp[A.length];
    }
  
    public static void main(String[] args){
        int[] A = {1, 15, 7, 9, 2, 5, 10};
        int K = 3;
        Solution sol = new Solution();
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("K: " + K);
        System.out.println("Maximum partition sum: " + sol.maxSumAfterPartitioning(A, K));
    }
}
