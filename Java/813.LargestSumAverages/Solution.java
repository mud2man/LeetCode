/* Dynamic Programming: Time:O(n^2*K), Space:O(n*K)
 * 1. dp[y][x] = the maximum sum of the average ended at index x with y partitions
 * 2. In every iteration, we grouped last ones A [...x - 1, x], and keep the maximum avarage
 * 3. Fill the maximum average into dp[y][x]
 */

import java.util.*; // Stack

public class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        double[][] dp = new double[K][A.length];
        
        double sum = 0.0;
        for(int x = 0; x < A.length; ++x){
            sum += (double)A[x];
            dp[0][x] = sum / (double)(x + 1); 
        }
        
        for(int y = 1; y < K; ++y){
            for(int x = y; x < A.length; ++x){
                sum = 0.0;
                double maxResult = 0.0;
                for(int z = x; z >= y; --z){
                    sum += (double)A[z];
                    maxResult = Math.max(maxResult, (sum / (double)(x - z + 1)) + dp[y - 1][z - 1]);
                }
                dp[y][x] = maxResult;
            }
        }
        
        return dp[K - 1][A.length - 1];
    }

    public static void main(String[] args){
        Solution sol;
        int[] A = {9, 1, 2, 3, 9};
        int K = 3;

        sol = new Solution();
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("K: " + K);
        System.out.println("maximum sum of averages: " + sol.largestSumOfAverages(A, K));
    }
}
