/* Dynamic Programming + DFS: O(n*m)
 * 1. dp[y][x] = the longest increasing path starting from (y, x)
 * 2. If dp[y][x] != 0, which means we already visit (y, x), we will NOT invoke dfs again
 * 3. And if (y, x) visited, the answer cannot be changed, since the direction to its neighbor is fixed, the path is not overlapped
 * 3. Record maximun length and invoke dfs, where 0 <= y < matrix.length, and 0 <= x < matrix[0].length 
 */

import java.util.*;

public class Solution{
    private int dfs(int[][] matrix, int y, int x, int[][]dp){
        if(dp[y][x] > 0){
            return dp[y][x];
        }
        
        dp[y][x] = 1;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 
        for(int[] dir: dirs){
            int nextY = y + dir[0];
            int nextX = x + dir[1];
            if(nextY < 0 || nextY >= matrix.length || nextX < 0 || nextX >= matrix[0].length){
                continue;
            }
            if(matrix[y][x] > matrix[nextY][nextX]){
                dfs(matrix, nextY, nextX, dp);
                dp[y][x] = Math.max(dp[y][x], 1 + dp[nextY][nextX]);
            }
        }
        return dp[y][x];
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        int depth = matrix.length;
        int width = (depth > 0)? matrix[0].length: 0;
        int[][] dp = new int[depth][width];
        int max = 0;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                dp[y][x] = dfs(matrix, y, x, dp);
                max = Math.max(max, dp[y][x]);
            }
        }
        return max;
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
