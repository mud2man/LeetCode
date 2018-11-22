/* DFS: Time:O(n*m), Space:O(n*m)
 * 1. Use DFS to visit grid, and reset grid[y][x] to 0 after visiting
 */

import java.util.*;

public class Solution{
    private int dfs(int y, int x, int[][] grid){
        int area = 0;
        if(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length || grid[y][x] == 0){
            return 0;
        }
        grid[y][x] = 0;
        area++;
        int[][] shifts = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] shift: shifts){
            area += dfs(y + shift[0], x + shift[1], grid);
        }
        return area;
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        
        int maxArea = 0;
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length; ++x){
                maxArea = Math.max(dfs(y, x, grid), maxArea);
            }
        }
        return maxArea;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] grid = {{1, 1, 0, 0, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 0, 1, 1},
                        {0, 0, 0, 1, 1}};
        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("max area: " + sol.maxAreaOfIsland(grid));
    }
}
