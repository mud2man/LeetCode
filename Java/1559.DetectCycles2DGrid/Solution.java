/* DFS: Time:O(n * m), Space:O(n * m)
 * 1. Visit every cell and use DFS to detect if there is cycle
 * 2. For DFS, a cycle was detected if the current color is gray
 */

import java.util.*; // Stack


public class Solution {
    private boolean dfs(int prevY, int prevX, int y, int x, int[][] color, char[][] grid){
        if(color[y][x] == 2){
            return false;
        }else if(color[y][x] == 1){
            return true;
        }else{
            color[y][x] = 1;
            int[][] dirs = {{0 ,1}, {0, -1}, {1, 0}, {-1, -0}}; 
            for(int[] dir: dirs){
                int nextY = y + dir[0];
                int nextX = x + dir[1];
                if(nextY >= grid.length || nextY < 0 || nextX >= grid[0].length || nextX < 0){
                    continue;
                }
                if(nextY == prevY && nextX == prevX){
                    continue;
                }
                if(grid[nextY][nextX] == grid[y][x] && dfs(y, x, nextY, nextX, color, grid)){
                    return true;
                }
            }
            color[y][x] = 2;
            return false;
        }
    }
    
    public boolean containsCycle(char[][] grid) {
        int[][] color = new int[grid.length][grid[0].length]; //0:white, 1:gray, 2:black
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                if(dfs(-1, -1, y, x, color, grid)){
                    return true;
                }
            }
        }
        return false;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] grid = {{'a', 'a', 'a', 'a'}, {'a', 'b', 'b', 'a'}, {'a', 'b', 'b', 'a'}, {'a', 'a', 'a', 'a'}};
        System.out.print("grid:");
        for(char[] row: grid){
            System.out.print(Arrays.toString(row) + ",");
        }
        System.out.println("\ncycle detected:" + sol.containsCycle(grid));
    }
}
