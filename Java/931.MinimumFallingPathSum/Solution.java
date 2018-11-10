/* Dynamic programming: Time:O(n^2), Space:O(1)
 * 1. Let dp[y][x] = the minimum falling path sum starting on A[y][x]
 * 2. In general case, dp[y][x] = min(dp[y + 1][x - 1], dp[y + 1][x], dp[y + 1][x + 1]) + A[y][x]
 */

import java.util.*;

public class Solution{
    public int minFallingPathSum(int[][] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        
        int len = A.length;
        int w = A[0].length;
        int[][] dp = A;
        for(int y = len - 1; y >= 0; --y){
            for(int x = 0; x < w; ++x){
                int left = (y < len - 1 && x > 0)? dp[y + 1][x - 1]: 101;
                int down = (y < len - 1)? dp[y + 1][x]: 101;
                int right = (y < len - 1 && x < w - 1)? dp[y + 1][x + 1]: 101;
                int val = Math.min(A[y][x] + left, A[y][x] + down);
                val = Math.min(val, A[y][x] + right);
                dp[y][x] = (y == len - 1)? A[y][x]: val; 
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int x = 0; x < w; ++x){
            min = Math.min(min, dp[0][x]);
        }
        return min;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] A = {{1, 2, 3},
                     {4, 5, 6},
                     {7, 8, 9}};
        
        System.out.println("A[][]: ");
        for(int i = 0; i < A.length; ++i){
            System.out.println(Arrays.toString(A[i]));
        }
        System.out.println("");
        System.out.println("min: " + sol.minFallingPathSum(A));
    }
}
