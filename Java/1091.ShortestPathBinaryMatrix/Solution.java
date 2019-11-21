/* BFS: O(n*m), Space:O(n + m)
 */

import java.util.*;

public class Solution{
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1){
            return -1;
        }
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int dis = 1;
        int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int[] curr = queue.pollFirst();
                if(curr[0] == grid.length - 1 && curr[1] == grid[0].length - 1){
                    return dis;
                }
                for(int[] dir: dirs){
                    int y = curr[0] + dir[0];
                    int x = curr[1] + dir[1];
                    if(y >= 0 && y < grid.length && x >= 0 && x < grid[0].length && grid[y][x] == 0){
                        queue.add(new int[]{y, x});
                        grid[y][x] = 1;
                    }
                }
            }
            dis++;
        }
        return -1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] grid = {{0, 1}, {1, 0}};
        System.out.println("array:");
        for(int[] row: grid){    
            System.out.println(Arrays.toString(row));
        }
        System.out.println("shortest past:" + sol.shortestPathBinaryMatrix(grid));
    }
}
