/* Time:O(n*m*k), Space:O(n*m), where n is row#, m is column#, k is building#
 * 1. Have 2D array passed[y][x] to record the number of building can be reached from the position (y, x)
 * 2. Have 2D array distance[y][x] to record the distance to reach all buildings from the position (y, x)
 * 3. Use BFS from all buildings, and record how many buildings can be reached.
 * 4. If reachCount != targetNumber from building A, we return -1, because there doesn't exist a position can reach A and others
 * 5. Get the minimum distance from the 2D array "distance"
 */         

import java.util.*;

public class Solution {
    private boolean bfs(int[] building, int targetNumber, int[][] grid, int[][] distance, int[][] passed){
        int depth = grid.length;
        int width = (depth > 0)? grid[0].length: 0; 
        boolean[][] visited = new boolean[depth][width];
        visited[building[0]][building[1]] = true;
        LinkedList<int[]> queue = new LinkedList<int[]>();
        queue.add(building);
        int reachCount = 1;
        int dist = 0;
        int[][] shifts = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        
        while(!queue.isEmpty()){
            int size = queue.size();
            dist++;
            for(int i = 0; i < size; ++i){
                int[] current = queue.pollFirst();
                for(int[] shift: shifts){
                    int[] next = new int[]{current[0] + shift[0], current[1] + shift[1]};
                    if(next[0] < 0 || next[0] >= depth || next[1] < 0 || next[1] >= width){
                        continue;
                    }
                    
                    if(visited[next[0]][next[1]] == true){
                        continue;
                    }
                    
                    if(grid[current[0]][current[1]] == 0 && grid[next[0]][next[1]] == 1){
                        visited[next[0]][next[1]] = true;;
                        reachCount++;   
                    }
                    else if(grid[next[0]][next[1]] == 2){
                        continue;
                    }
                    else if(grid[next[0]][next[1]] == 0){
                        visited[next[0]][next[1]] = true;
                        distance[next[0]][next[1]] += dist;
                        passed[next[0]][next[1]]++;
                        queue.add(next);
                    }
                }
            }
        }
        return (reachCount == targetNumber);
    }
    
    public int shortestDistance(int[][] grid) {
        List<int[]> buildings = new ArrayList<int[]>();
        int depth = grid.length;
        int width = (depth > 0)? grid[0].length: 0;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(grid[y][x] == 1){
                    buildings.add(new int[]{y, x});
                }
            }
        }
        
        int[][] distance = new int[depth][width];
        int[][] passed = new int[depth][width];
        for(int[] building: buildings){
            if(!bfs(building, buildings.size(), grid, distance, passed)){
                return -1;
            }
        }
        
        int minDistance = Integer.MAX_VALUE;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(distance[y][x] > 0 && passed[y][x] == buildings.size()){
                    minDistance = Math.min(minDistance, distance[y][x]);
                }
            }
        }
        return (minDistance == Integer.MAX_VALUE)? - 1: minDistance;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[][] grid = {{1, 0, 2, 0, 1}, 
                        {0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0}};
        sol = new Solution();
        
        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("minimum distance: " + sol.shortestDistance(grid));
    }
}
