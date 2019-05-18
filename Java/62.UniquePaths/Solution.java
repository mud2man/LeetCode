/* Dynamic programming: Time:O(n*m), Space:O(m)
 * 1. dp[y][x] = the path# ending at (y, x)
 * 2. In general, dp[y][x] = dp[y - 1][x] + dp[y][x - 1]
 */

import java.util.*;

public class Solution{
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[2][m];
        dp[0][0] = 1;
        for(int y = 0; y < n; ++y){
            for(int x = 0; x < m; ++x){
                if(y == 0 && x == 0){
                    continue;
                }
                int fromTop = (y > 0)? dp[(y - 1) % 2][x]: 0;
                int fromLeft = (x > 0)? dp[y % 2][x - 1]: 0;
                dp[y % 2][x] = fromTop + fromLeft;
            }
        }
        return dp[(n - 1) % 2][m - 1];
    }
  
    public static void main(String[] args){
        int m = 3;
        int n = 2;
        Solution sol = new Solution();    
        System.out.println("m: " + m + ", n: " + n);
        System.out.println("path#: " + sol.uniquePaths(m, n));
    }
}
