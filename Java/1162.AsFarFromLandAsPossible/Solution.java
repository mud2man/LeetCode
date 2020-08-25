/* BFS: Time:O(n*m), Space:O(n*m)
 * 1.Get the nodes with in-degree = 0
 */     

import java.util.*; // Stack

public class Solution {
    public int maxDistance(int[][] grid) {
        Deque<int[]> queue = new LinkedList<>();
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length; ++x){
                if(grid[y][x] == 1){
                    queue.add(new int[]{y, x});
                }
            }
        }
        
        int dis = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while(!queue.isEmpty()){
            boolean hasWater = false;
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int[] head = queue.pollFirst();
                for(int[] direction: directions){
                    int y = head[0] + direction[0];
                    int x = head[1] + direction[1];
                    if(y >= 0 && y < grid.length && x >= 0 && x < grid[0].length && grid[y][x] == 0){
                        queue.add(new int[]{y, x});
                        grid[y][x] = 1;
                        hasWater = true;
                    }
                }
            }
            dis +=(hasWater)? 1: 0;
        }
        return (dis == 0)? -1: dis;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        System.out.println("grid:" + grid);
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("max distance:" + sol.maxDistance(grid));
    }
}
