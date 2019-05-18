/* Two pointers: Time:O(n^2), Space:O(1)
 * 1. Have two pointers left and right
 * 2. If (a >= 0), put answer[i] from (size - 1), and compare quadratic value of left and right to determine shift which one. It's like merge sort
 * 3. Otherwise, put answer[i] from 0
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
