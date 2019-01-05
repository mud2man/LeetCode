/* Dynamic Programming: Time:O(n^3), Space:O(n^3). LeeTCode has Space:O(n^2) solution
 * 1. let dp[y0][x0][y1] be the most cherry can pick by (y0, x0), (y1, x1), where y0 + x0 = y1 + x1. It works because (N -1, N -1) to (0, 0) meet assumption
 * 2. dp[y0][x0][y1] = grid[y0][x0] + grid[y1][x1] + max(dp[y0 + 1][x0][x1], dp[y0 + 1][x0][x1 + 1], dp[y0][x0 + 1][x1], dp[y0][x0 + 1][x1 + 1]), 
 *    because there are only 4 possibility can reach (y0, x0) and (y1, x1)
 * 3. If (y0, x0) == (y1, x1), we need to avoid double count
 *
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public int cherryPickup(int[][] grid) {
        int depth = grid.length;
        int[][][] dp = new int[depth][depth][depth];
        for(int y0 = depth - 1; y0 >= 0; y0--){
            for(int x0 = depth - 1; x0 >= 0; x0--){
                for(int y1 = depth - 1; y1 >= 0; y1--){
                    int x1 = y0 + x0 - y1;
                    if(x1 < 0 || x1 >= depth){
                        continue;
                    }
                    
                    if(grid[y0][x0] == -1 || grid[y1][x1] == -1){
                        dp[y0][x0][x1] = -1;
                        continue;
                    }
                    
                    //Corner case for (N -1, N -1)
                    if(y0 == depth - 1 && x0 == depth - 1){
                        dp[y0][x0][x1] = grid[y0][x0];
                        continue;
                    }
                    
                    dp[y0][x0][x1] = (x0 != x1)? grid[y0][x0] + grid[y1][x1]: grid[y0][x0];
                    int downDown = (y0 < depth - 1 && y1 < depth - 1)? dp[y0 + 1][x0][x1] : -1;
                    int downRight = (y0 < depth - 1 && x1 < depth - 1)? dp[y0 + 1][x0][x1 + 1] : -1;
                    int rightDown = (x0 < depth - 1 && y1 < depth - 1)? dp[y0][x0 + 1][x1] : -1;
                    int rightRight = (x0 < depth - 1 && x1 < depth - 1)? dp[y0][x0 + 1][x1 + 1] : -1;
                    int subOptimal = Math.max(downDown, downRight);
                    subOptimal = Math.max(subOptimal, rightDown);
                    subOptimal = Math.max(subOptimal, rightRight);
                    dp[y0][x0][x1] = (subOptimal == -1)? -1: subOptimal + dp[y0][x0][x1];
                }
            }
        }
        return (dp[0][0][0] == -1)? 0: dp[0][0][0];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] grid = {{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("maximum number of cherry: " + sol.cherryPickup(grid));
    }
}
