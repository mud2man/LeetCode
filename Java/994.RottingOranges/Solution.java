/* BFS: Time:O(n*m, Space:(n*m)
 * 1. Use BFS to get the minimum of minutes to rotten all oranges
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    public int orangesRotting(int[][] grid) {
        Deque<int[]> queue = new LinkedList<>();
        Set<Integer> rottenOranges = new HashSet<>();
        int freshCount = 0;
        int width = grid[0].length;
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length; ++x){
                if(grid[y][x] == 2){
                    queue.add(new int[]{y ,x});
                    rottenOranges.add(y * width + x);
                }else if(grid[y][x] == 1){
                    freshCount++;
                }
            }
        }
        int minutes = 0;
        boolean polluted = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            polluted = true;
            for(int i = 0; i < size; ++i){
                int[] front = queue.pollFirst();
                int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                for(int[] dir: dirs){
                    int y = front[0] + dir[0];
                    int x = front[1] + dir[1];
                    int hash = y * width + x;
                    if(y >= 0 && y < grid.length && x >= 0 && x < width && grid[y][x] == 1 && !rottenOranges.contains(hash)){
                        rottenOranges.add(hash);
                        queue.add(new int[]{y, x});
                        freshCount--;
                    }
                }
            }
            minutes++;
        }
        return (freshCount == 0)? ((polluted)? (minutes - 1): 0) : -1;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println("grid:");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("minimum number of minutes:" + sol.orangesRotting(grid));
    }
}
