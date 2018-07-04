/* Greedy: Time:O(n*m), Space:O(n)
 * 1. Get the maximum height in row and column
 * 2. Accumulate the count by the difference between row/column maximum and current height
 */         

import java.util.*;

public class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int depth = grid.length;
        int width = (depth > 0)? grid[0].length: 0;
        int[] row = new int[width];
        int[] col = new int[depth];
        
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                row[x] = Math.max(row[x], grid[y][x]);
                col[y] = Math.max(col[y], grid[y][x]);
            }
        }
        
        int count = 0;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                count += Math.min(row[x], col[y]) - grid[y][x];
            }
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol= new Solution();
        int[][] grid = {{3, 0, 8, 4}, {2, 4, 5, 7},{9, 2, 6, 3},{0, 3, 1, 0}};

        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("maximum total sum: " + sol.maxIncreaseKeepingSkyline(grid));
    }
}
