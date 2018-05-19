/* Time:O(m*n), Space:O(m*n)
 * 1. Have a 3D array dp, where dp[y][x][0] = row hit on (y, x), dp[y][x][1] = column hit on (y, x)
 * 2. Because for every position (y, x), it only contributed on row count or column count once.
 * 3. Therefore the amortized complexity is O(m*n)
 */

import java.util.*;

public class Solution{
    public int maxKilledEnemies(char[][] grid) {
        int depth = grid.length;
        int width = (depth > 0)? grid[0].length: 0;
        int[][][] dp = new int[depth][width][2];
        int maxHit = 0;
        
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                char c = grid[y][x];
                if(c == 'E' || c == '0'){
                    if(dp[y][x][0] == 0){
                        int rowHit = 0;
                        for(int i = x; i < width && grid[y][i] != 'W'; ++i){
                            rowHit += (grid[y][i] == 'E')? 1: 0;
                        }
                        
                        for(int i = x; i < width && grid[y][i] != 'W'; ++i){
                            dp[y][i][0] = rowHit;
                        }
                    }
                    
                    if(dp[y][x][1] == 0){
                        int columnHit = 0;
                        for(int i = y; i < depth && grid[i][x] != 'W'; ++i){
                            columnHit += (grid[i][x] == 'E')? 1: 0;
                        }
                        
                        for(int i = y; i < depth && grid[i][x] != 'W'; ++i){
                            dp[i][x][1] = columnHit;
                        }
                    }
                    
                    if(c == '0'){
                        maxHit = Math.max(dp[y][x][0] + dp[y][x][1], maxHit);
                    }
                }
            }
        }
        return maxHit;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] grid = {{'0', 'E', '0', '0'},
                         {'E', '0', 'W', 'E'},
                         {'0', 'E', '0', '0'}};

        System.out.println("grid: ");
        for(char[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        int maxCount = sol.maxKilledEnemies(grid);
        System.out.println("maximum count: " + maxCount);
    }
}
