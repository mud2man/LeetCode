/* Dynamic Programming: Time:O(n^3), Space:O(n^2)
 * 1. dp[end][start] = minimum score to triangulate A[start] ~ A[end]
 * 2. dp[end][start] = min(A[start] * A[end] * A[middle] + dp[end][middle] + dp[middle][start]), where start < middle < end
 */

import java.util.*; // Stack

public class Solution {
    public int minScoreTriangulation(int[] A) {
        int[][] dp = new int[A.length][A.length];
        for(int end = 2; end < A.length; ++end){
            for(int start = end - 2; start >= 0; --start){
                int min = Integer.MAX_VALUE;
                for(int middle = end - 1; middle > start; middle--){
                    min = Math.min(min, A[start] * A[end] * A[middle] + dp[end][middle] + dp[middle][start]);
                }
                dp[end][start] = min;
            }
        }
        return dp[A.length - 1][0];
    }
 
    public static void main(String[] args){
        int[] A = {3, 7, 4, 5};
        Solution sol = new Solution();
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("Minimum score: " + sol.minScoreTriangulation(A));
    }
}
