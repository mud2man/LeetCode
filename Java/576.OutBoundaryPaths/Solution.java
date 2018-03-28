/* Dynamic Programming: Time:O(N*n*m), Space:O(n*m)
 */

import java.util.*;

public class Solution{
    public int findPaths(int m, int n, int N, int i, int j) {
        if(N == 0){
            return 0;
        }
        
        int[][][] dp = new int[2][m][n];
        dp[0][i][j] = 1;
        int base = 1000000007;
        int count = 0;
        for(int z = 0; z < N; ++z){
            for(int y = 0; y < m; ++y){
                for(int x = 0; x < n; ++x){
                    int currentStep = z % 2;
                    int previousStep = (z - 1) % 2;
                    dp[currentStep][y][x] = (z > 0)? 0: dp[currentStep][y][x];
                    if((y - 1) >= 0 && z > 0){
                        dp[currentStep][y][x] = (dp[currentStep][y][x] + dp[previousStep][y - 1][x]) % base;
                    }
                    
                    if((y + 1) < m && z > 0){
                        dp[currentStep][y][x] = (dp[currentStep][y][x] + dp[previousStep][y + 1][x]) % base;
                    }
                    
                    if((x - 1) >= 0  && z > 0){
                        dp[currentStep][y][x] = (dp[currentStep][y][x] + dp[previousStep][y][x - 1]) % base;
                    }
                    
                    if((x + 1) < n  && z > 0){
                        dp[currentStep][y][x] = (dp[currentStep][y][x] + dp[previousStep][y][x + 1]) % base;
                    }
                    
                    count = ((y - 1) < 0)? (count + dp[currentStep][y][x]) % base: count;
                    count = ((y + 1) == m)? (count + dp[currentStep][y][x]) % base: count;
                    count = ((x - 1) < 0)? (count + dp[currentStep][y][x]) % base: count;
                    count = ((x + 1) == n)? (count + dp[currentStep][y][x]) % base: count;
                }
            }
        }
        
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int m = 2;
        int n = 2;
        int N = 2;
        int i = 0;
        int j = 0;
        sol = new Solution();
        System.out.println("m: " + m + ", n:" + n + ", N:" + N + ", i:" + i + ", j:" + j);
        System.out.println("path count: " + sol.findPaths(m, n, N, i, j));
    }
}
