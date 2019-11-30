/* DFS: Time:O(n^2 * m^2), Space:O(n*m)
 * 1. Use dfs to update the maximum gold given all possible positions
 */

import java.util.*;

public class Solution{
    private int dfs(int y, int x, int[][] grid){
        if(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length || grid[y][x] == 0){
            return 0;
        }
        
        int gold = grid[y][x];
        grid[y][x] = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int max = 0;
        for(int[] dir: dirs){
            max = Math.max(max, gold + dfs(y + dir[0], x + dir[1], grid));
        }
        grid[y][x] = gold;
        return max;
    }
    
    public int getMaximumGold(int[][] grid) {
        int max = 0;
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length; ++x){
                max = Math.max(dfs(y, x, grid), max);
            }
        }
        return max;
    }
 
    public static void main(String[] args){
        int[][] grid = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        Solution sol = new Solution();
        System.out.println("grid:");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("maximum gold:" + sol.getMaximumGold(grid));
    }
}
