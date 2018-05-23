/* Dynamic Programming: Time:O(K + W), Space:O(K)
 * 1. dp[i] = the propability of current point = i
 * 2. Caculate dp[i] by sliding window
 * 3. Accumulate ret from dp from the last index to max(0, K - W)
 * 4. Treat dp[i] as the second last step, so next step must reach K and cannot over N
 */         

import java.util.*;

public class Solution {
    public double new21Game(int N, int K, int W) {
        if(N == 0 || K == 0){
            return 1.0;
        }
        
        double[] dp = new double[K];
        double windowSum = 1.0 / (double)W;
        dp[0] = 1.0;
        for(int i = 1; i < K; ++i){
            dp[i] = windowSum;
            windowSum += dp[i] / (double)W;
            if(i >= W){
                windowSum -= dp[i - W] / (double)W;
            }
        }
        
        double ret = 0.0;
        for(int i = K - 1; i >= Math.max(0, K - W); --i){
            //terminated condition
            int lb = K;
            //less than or equal to N
            int hb = Math.min(i + W, N);
            double p = (lb <= hb)? (hb - lb + 1) / (double)W : 0.0;
            ret += p * dp[i];
        }
        
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 6;
        int K = 1;
        int W = 10;
        System.out.println("N: " + N);
        System.out.println("K: " + K);
        System.out.println("W: " + W);
        System.out.println("propability: " + sol.new21Game(N, K, W));
    }
}
