/* Time:O(n^2*m), Space:O(1), where n is row#, m is column#
 * 1. Fix two rows, and get the number of column with 1's on both rows
 * 2. Assume the hitted column number is k, Accumulate count with combination(k, 2)
 */         

import java.util.*;

public class Solution {
    public int countCornerRectangles(int[][] grid) {
        int count = 0;
        int depth = grid.length;
        int width = grid[0].length;
        
        for(int row0 = 0; row0 < (depth - 1); ++row0){
            for(int row1 = row0 + 1; row1 < depth; ++row1){
                int hitColumn = 0;
                for(int column = 0; column < width; ++column){
                    hitColumn += (grid[row0][column] == 1 && grid[row1][column] == 1)? 1: 0; 
                }
                count += (hitColumn > 1)? hitColumn * (hitColumn - 1) / 2: 0;
            }
        }
        
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] grid = {{1, 0, 0, 1, 0}, 
                        {0, 0, 1, 0, 1},
                        {0, 0, 0, 1, 0},
                        {1, 0, 1, 0, 1}};
        sol = new Solution();
        
        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("number of corner rectangles: " + sol.countCornerRectangles(grid));
    }
}
