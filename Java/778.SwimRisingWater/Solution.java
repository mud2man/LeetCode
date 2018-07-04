/* Binary search + DFS: Time:O(n^2*logn), Space:O(n^2). 
 * 1. Given elevation, use DFS to see if we can traverse from top-left corner to bottom-right corner
 * 2. Apply binary search to find the boundry from 0 and N*N - 1
 */         

import java.util.*;

public class Solution {
    private boolean dfs(int[][] grid, int y, int x, int elevation, Set<Integer> visited){
        int pos = y * grid[0].length + x;
        if(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length || visited.contains(pos) || grid[y][x] > elevation){
            return false;
        }
        
        visited.add(pos);
        if(visited.contains(grid.length * grid[0].length - 1) && visited.contains(0)){
            return true;
        }
        
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] dir: dirs){
            if(dfs(grid, y + dir[0], x + dir[1], elevation, visited)){
                return true;
            }
        }
        return false;
    }
    
    public int swimInWater(int[][] grid) {
        int lb = 0;
        int hb = grid.length * grid.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int y = 0;
            int x = 0;
            if(dfs(grid, y, x, mid, new HashSet<Integer>())){
                hb = mid - 1;
            }
            else{
                lb = mid + 1;
            }
        }
        return lb;
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        int[][] grid = {{0, 2}, {1, 3}};

        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("least time: " + sol.swimInWater(grid));
    }
}
