/* BFS: Time:O(n * m), Space:O(n * m)
 * 1. Use grid[y][x] to record if (y, x) be visited
 * 2. Use BFS to propagate the reachable area from (0, 0) while apply dfs in each loop
 * 3. We need to remove (y, x) from nextQueue if it can be rechable while putting the unreachable non-visited(grid[y][x] == -1) neighbors to nextQueue
 * 4. Repeat step2 and step3 until grid[depth - 1][width - 1] == -1
 */     

import java.util.*; // Stack

public class Solution {
    private void dfs(int curr, Set<Integer> nextQueue, int[][] grid){
        int depth = grid.length;
        int width = grid[0].length;
        int y = curr / width;
        int x = curr % width;
        if(grid[y][x] == -1){
            return;
        }
        nextQueue.remove(curr);
        int directionType = grid[y][x];
        grid[y][x] = -1;
        int[][] shifts = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int i = 1; i < 5; ++i){
            int nextY = y + shifts[i][0];
            int nextX = x + shifts[i][1];
            if(nextY >= 0 && nextY < depth && nextX >= 0 && nextX < width){
                if(i == directionType){
                    dfs(nextY * width + nextX, nextQueue, grid);
                }else{
                    if(grid[nextY][nextX] != -1){
                        nextQueue.add(nextY * width + nextX);
                    }       
                }
            }
        }
    }
    
    public int minCost(int[][] grid) {
        int depth = grid.length;
        int width = grid[0].length;
        Set<Integer> queue = new HashSet<>();
        queue.add(0);
        int distance = 0;
        while(!queue.isEmpty()){
            Set<Integer> nextQueue = new HashSet<>();
            for(int curr: queue){
                dfs(curr, nextQueue, grid);
                if(grid[depth - 1][width - 1] == -1){
                    return distance;
                }
            }
            distance++;
            queue = nextQueue;
        }
        return distance - 1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] grid = {{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}};
        System.out.println("grid:");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("min cost:" + sol.minCost(grid));
    }
}
