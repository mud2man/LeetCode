/* Dynamic programming: Time:O(n), Space:O(n)
 * 1. dp[i][0] = number of tiling of case0: ---|
 *                                          ---|
 *                                          |__
 *
 * 2. dp[i][1] = number of tiling of case1: |---
 *                                          |---|
 *                                           ___|
 *
 * 3. dp[i][2] = number of tiling of case2:  ---|
 *                                           ---|
 *                                           ___|
 * 
 * 4. dp[i][j] can be deduced by dp[i - 1]and dp[i - 2]
 */

import java.util.*;

public class Solution{
    public int numTilings(int N) {
        if(N == 1){
            return 1;
        }
        
        long[][] dp = new long[N][3];
        dp[0][2] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 2;
        long modulo = 1000000007;
        
        for(int i = 2; i < N; ++i){
            dp[i][0] = (dp[i - 2][2] + dp[i - 1][1]) % modulo;
            dp[i][1] = (dp[i - 2][2] + dp[i - 1][0]) % modulo;
            dp[i][2] = (dp[i - 1][2] + dp[i - 2][2] + dp[i - 1][1] + dp[i - 1][0]) % modulo;
        }
        
        return (int)dp[N - 1][2];
    }

    public static void main(String[] args){
        Solution sol;
        int N = 3;

        sol = new Solution();
        System.out.println("N: " + N);
        System.out.println("tiling nimber: " + sol.numTilings(N));
    }
}
