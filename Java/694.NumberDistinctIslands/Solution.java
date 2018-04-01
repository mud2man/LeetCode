/* DFS: Time:O(n*m), Space:O(n*m)
 * 1. We use a string to identify the island, and the string is generate by DFS
 * 2. Because the order of DFS's next step is fixed (right, down, left, up), string will be the same if the island's shape is same
 * 3. Reset grid[y][x] to 0 if we visited
 */

import java.util.*;

public class Solution{
        private void dfs(int[][] grid, int y, int x, StringBuilder island, int base){
        if(y == grid.length || x < 0 || x == grid[0].length || y < 0 || grid[y][x] == 0){
            return;
        }
        grid[y][x] = 0;
        island.append(Integer.toString(base) + ",");
        dfs(grid, y, x + 1, island, base + 1);
        dfs(grid, y + 1, x, island, base + grid[0].length);
        dfs(grid, y, x - 1, island, base - 1);
        dfs(grid, y - 1, x, island, base - grid[0].length);
    }
    
    public int numDistinctIslands(int[][] grid) {
        HashSet<String> islands = new HashSet<String>();
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length; ++x){
                if(grid[y][x] == 1){
                    StringBuilder island = new StringBuilder("");
                    dfs(grid, y, x, island, 0);
                    islands.add(island.toString());
                }
            }
        }
        return islands.size();
    }

    public static void main(String[] args){
        Solution sol;
        int[][] grid = {{1, 1, 0, 0, 0},{1, 1, 0, 0, 0},{0, 0, 0, 1, 1},{0, 0, 0, 1, 1}};
        
        sol = new Solution();
        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("distinct island number: " + sol.numDistinctIslands(grid));
    }
}
