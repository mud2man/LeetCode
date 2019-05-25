/* Time:O(n^2), Space:O(1)
 * 1. Mirrow and reverse
 */

import java.util.*;

public class Solution{
    public void rotate(int[][] matrix) {
        //swap
        for(int y = 0; y < matrix.length; ++y){
            for(int x = y; x < matrix.length; ++x){
                int tmp = matrix[y][x];
                matrix[y][x] = matrix[x][y];
                matrix[x][y] = tmp;
            }
        }
        //reverse
        for(int y = 0; y < matrix.length; ++y){
            for(int x = 0; x < (matrix.length / 2); ++x){
                int tmp = matrix[y][x];
                matrix[y][x] = matrix[y][matrix.length - x - 1];
                matrix[y][matrix.length - x - 1] = tmp;
            }
        }
    }
  
    public static void main(String[] args){
        int[][] matrix = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9}};

        System.out.println("before matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        Solution sol = new Solution();
        sol.rotate(matrix);
        System.out.println("after matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
    }
}
