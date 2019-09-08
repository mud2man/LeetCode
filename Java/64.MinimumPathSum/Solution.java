/* Dynamic Programming: Time = O(n*m), Space = O(m)
 * 1. dp[y][x] = the minimum path sum ending at (y, x)
 * 2. dp[y][x] = min(dp[y - 1][x], dp[y][x - 1]), grid[y][x]
 */

import java.util.*;

public class Solution{
    public int minPathSum(int[][] grid) {
        int[][] dp = new int [2][grid[0].length];
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length; ++x){
                if(y == 0 && x == 0){
                    dp[y % 2][x] = grid[y][x];
                }else{
                    int fromTop = (y > 0)? dp[(y - 1) % 2][x] : Integer.MAX_VALUE;
                    int fromLeft = (x > 0)? dp[y % 2][x - 1] : Integer.MAX_VALUE;
                    dp[y % 2][x] = Math.min(fromTop, fromLeft) + grid[y][x];
                }
            }
        }
        return dp[(grid.length - 1) % 2][grid[0].length - 1];
    }
  
    public static void main(String[] args){
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        Solution sol = new Solution();
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Minimum Path Sum: " + sol.minPathSum(grid));
	}
}
