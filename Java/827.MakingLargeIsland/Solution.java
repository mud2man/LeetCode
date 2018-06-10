/* DFS: Time:O(n*m), Space:O(n*m)
 * 1. Have "node2Island" to record the node's connected area
 * 2. Use DFS to visited every island, and update node2Island and maxArea
 * 3. DFS will return all the boundry nodes for each island, we use the boundry node to update node2Island and max area
 * 4. Since each node can be at most connect 4 island, so the time complexity is O(4*n*m) = O(n*m)
 */         

import java.util.*;

public class Solution {
    private int dfs(int[][] grid, int y, int x, Set<Integer> boundry, Set<Integer> island){
        if(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length){
            return 0;
        }
        
        int node = y * grid[0].length + x;
        if(grid[y][x] == 0){
            if(!island.contains(node)){
                boundry.add(node);
            }
            return 0;
        }
        
        island.add(node);
        grid[y][x] = 0;
        int area = 1;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] dir: dirs){
            area += dfs(grid, y + dir[0], x + dir[1], boundry, island);
        }
        return area;
    }
    
    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> node2Island = new HashMap<Integer, Integer>();
        int maxArea = 1;
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length; ++x){
                Set<Integer> boundry = new HashSet<Integer>();
                if(grid[y][x] == 1){
                    int area = dfs(grid, y, x, boundry, new HashSet<Integer>());
                    for(int node: boundry){
                        if(node2Island.containsKey(node)){
                            maxArea = Math.max(maxArea, area + node2Island.get(node) + 1);
                            node2Island.put(node, area + node2Island.get(node));
                        }
                        else{
                            maxArea = Math.max(maxArea, area + 1);
                            node2Island.put(node, area);
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args){
        Solution sol= new Solution();
        int[][] grid = {{1, 0}, 
                        {0, 1}};

        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("maximum connected area: " + sol.largestIsland(grid));
    }
}
