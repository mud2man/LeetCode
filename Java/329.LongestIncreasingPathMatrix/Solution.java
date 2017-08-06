/* Dynamic Programming + DFS: O(n*m)
 * 1. dp[y][x] = the longest increasing path starting from (y, x)
 * 2. If dp[y][x] != 0, which means we already visit (y, x), we will NOT invoke dfs again
 * 3. Record maximun length and invoke dfs, where 0 <= y < matrix.length, and 0 <= x < matrix[0].length 
 */

import java.util.*;

public class Solution{
    //left, up, right, down
    private int[][] directions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    
    private boolean isInRange(int[][] matrix, int y, int x){
        return (y >= 0 && y < matrix.length && x >= 0 && x < matrix[0].length) ? true: false;
    }
    
    private void dfs(int[][] matrix, int[][] dp, int y, int x){
        int maxLen = 0;
        
        for(int[] direction: directions){
            int shiftY = y + direction[0];
            int shiftX = x + direction[1];
            
            //increase path
            if(isInRange(matrix, shiftY, shiftX) && matrix[y][x] < matrix[shiftY][shiftX]){
                if(dp[shiftY][shiftX] == 0){
                    dfs(matrix, dp, shiftY, shiftX);
                }
                maxLen = Math.max(dp[shiftY][shiftX] + 1, maxLen);    
            }
            maxLen = Math.max(maxLen, 1);
        }
        dp[y][x] = maxLen;
    } 
    
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxLen = 0;
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                if(dp[y][x] == 0){
                    dfs(matrix, dp, y, x);
                    maxLen = Math.max(maxLen, dp[y][x]);
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] matrix = {{9, 9, 4},
                          {6, 6, 8},
                          {2, 1, 1}};
        int maxLen;

        maxLen = sol.longestIncreasingPath(matrix);
        
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("maxLen: " + maxLen);
    }
}
