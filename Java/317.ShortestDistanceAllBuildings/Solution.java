/* Time:O(n*m*k), Space:O(n*m), where n is row#, m is column#, k is building#
 * 1. Have 2D array passed[y][x] to record the number of building can be reached from the position (y, x)
 * 2. Have 2D array distance[y][x] to record the distance to reach all buildings from the position (y, x)
 * 3. Use BFS from all buildings, and record how many buildings can be reached.
 * 4. If reachCount != targetNumber from building A, we return -1, because there doesn't exist a position can reach A and others
 * 5. Get the minimum distance from the 2D array "distance"
 */         

import java.util.*;

public class Solution {
    private void bfs(int[][] grid, int startY, int startX, int[][] hitCount, int[][] distance){
        int depth = grid.length;
        int width = grid[0].length;
        boolean[][] visited = new boolean[depth][width];
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX});
        int radius = 0;
        int[][] shifts = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while(!queue.isEmpty()){
            radius++;
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int[] curr = queue.pollFirst();
                for(int[] shift: shifts){
                    int y = curr[0] + shift[0];
                    int x = curr[1] + shift[1];
                    if(y >= 0 && y < depth && x >= 0 && x < width && grid[y][x] == 0 && !visited[y][x]){
                        distance[y][x] += radius;
                        hitCount[y][x]++;
                        visited[y][x] = true;
                        queue.add(new int[]{y, x});
                    }
                }
            }
        }
    }
    
    public int shortestDistance(int[][] grid) {
        int depth = grid.length;
        int width = grid[0].length;
        int[][] distance = new int[depth][width];
        int[][] hitCount = new int[depth][width];
        int k = 0;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(grid[y][x] == 1){
                    bfs(grid, y, x, hitCount, distance);
                    ++k;
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(hitCount[y][x] == k){
                    min = Math.min(min, distance[y][x]); 
                }
            }
        }
        return (min == Integer.MAX_VALUE)? -1: min;
    }
 
    public static void main(String[] args){
        int[][] grid = {{1, 0, 2, 0, 1}, 
                        {0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0}};
        Solution sol = new Solution();
        
        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("minimum distance: " + sol.shortestDistance(grid));
    }
}
