/* DFS: Time:O(n*m), Space:O(n*m)
 */

import java.util.*; // Stack

public class Solution {
    private boolean dfs(int y, int x, int[][] grid){
        if(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length){
            return false;
        }else if(grid[y][x] == 1){
            return true;
        }else{
            grid[y][x] = 1;
            int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            boolean ret = true;
            for(int[] dir: dirs){
                ret =dfs(y + dir[0], x + dir[1], grid)? ret: false;
            }
            return ret;
        }
        
    }
    
    public int closedIsland(int[][] grid) {
        int count = 0;
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length; ++x){
                count +=(grid[y][x] == 0 && dfs(y, x, grid))? 1: 0;
            }
        }
        return count;
    }
  
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}};
        Solution sol = new Solution();
        System.out.println("grid:");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("closed islands:" + sol.closedIsland(grid));
    }
}
