/* Dynamic Programming: O(n*m)
 * 1. dp[y][x] = the maximum area of square with bottom-rightest corner at (y, x)
 * 2. dp[y][x] = min(dp[y - 1][x] + 1, dp[y][x - 1] + 1, dp[y - 1][x - 1] + 1);
 */

import java.util.*;


public class Solution{
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxLen = 0;
        for(int y = 0; y < matrix.length; ++y){
            for(int x = 0; x < matrix[0].length; ++x){
                char c = matrix[y][x];
                if(c == '0'){
                    dp[y][x] = 0;
                    continue;
                }
                else{
                    if(y > 0 && x > 0){
                        dp[y][x] = Math.min(dp[y - 1][x], dp[y][x - 1]);
                        dp[y][x] = Math.min(dp[y - 1][x - 1], dp[y][x]);
                        dp[y][x]++;
                    }
                    else{
                        dp[y][x] = 1;
                    }
                    maxLen = Math.max(maxLen, dp[y][x]);               
                }
            }
        }
        return maxLen * maxLen;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                           {'1', '0', '1', '1', '1'},
                           {'1', '1', '1', '1', '1'},
                           {'1', '0', '0', '1', '0'}};
        int maxSquare;

        maxSquare = sol.maximalSquare(matrix);
        
        System.out.println("matrix: ");
        for(char[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("maxSquare: " + maxSquare);
    }
}
