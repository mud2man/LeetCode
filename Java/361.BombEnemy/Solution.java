/* Time:O(m*n), Space:O(n)
 * 1. Have an array cols, where cols[x] = column hit on (y, x), row = row hit on (y, x).
 * 2. Reset col as {-1, -1, ...}
 * 2. Because for every position (y, x), it only contributed on row count or column count once, since we count only in cols[x] == -1 or row == -1
 * 3. Therefore the amortized complexity is O(m*n)
 */

import java.util.*;

public class Solution{
    public int maxKilledEnemies(char[][] grid) {
        int depth = grid.length;
        int width = (depth > 0)? grid[0].length: 0;
        int[] cols = new int[width];
        Arrays.fill(cols, -1);
        int max = 0;
        for(int y = 0; y < depth; ++y){
            int row = -1;
            for(int x = 0; x < width; ++x){
                if(grid[y][x] == 'W'){
                    cols[x] = -1;
                    row = -1;
                }else{
                    if(cols[x] == -1){
                        int count = 0;
                        for(int j = y; j < depth && grid[j][x] != 'W'; ++j){
                            count +=(grid[j][x] == 'E')? 1: 0;
                        }
                        cols[x] = count;
                    }
                    if(row == -1){
                        int count = 0;
                        for(int i = x; i < width && grid[y][i] != 'W'; ++i){
                            count +=(grid[y][i] == 'E')? 1: 0;
                        }
                        row = count;
                    }
                    max = (grid[y][x] == '0')? Math.max(max, row + cols[x]): max;
                }
            }
        }
        return max;
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
