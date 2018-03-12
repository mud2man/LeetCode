/* Time:O(n), Space:O(n^2). However Leetcode has Binary Indexed Tree solution
 * 1. Hava a 2D array rowSum, where rowSum[y][x] = sum(matrix[y][0] + matrix[y][1] + ... matrix[y][x])
 * 2. We can do O(n) update, and O(n) query
 */

import java.util.*;

public class Solution{
    int[][] rowSum;
    int depth;
    int width;
    int[][] matrix;
    
    public Solution(int[][] matrix) {
        depth = matrix.length;
        width = (depth > 0) ?matrix[0].length: 0;
        rowSum = new int[depth][width];
        this.matrix = matrix;
        for(int y = 0; y < depth; ++y){
            int sum = 0;
            for(int x = 0; x < width; ++x){
                sum += matrix[y][x];
                rowSum[y][x] = sum;
            }
        }
    }
    
    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
    
        for(int x = col; x < width; ++x){
            rowSum[row][x] += diff; 
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for(int y = row1; y <= row2; ++y){
            sum += (col1 > 0)? rowSum[y][col2] - rowSum[y][col1 - 1]: rowSum[y][col2];
        }
        return sum;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[][] matrix = {{3, 0, 1, 4, 2},
                          {5, 6, 3, 2, 1},
                          {1, 2, 0, 1, 5},
                          {4, 1, 0, 1, 7},
                          {1, 0, 3, 0, 5}};

        sol = new Solution(matrix);
        
        System.out.println("matrix[][]: ");
        for(int i = 0; i < matrix.length; ++i){
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("");
        
        System.out.println("sumRegion(2, 1, 4, 3):" + sol.sumRegion(2, 1, 4, 3));
        System.out.println("update(3, 2, 2)");
        sol.update(3, 2, 2);
        System.out.println("sumRegion(2, 1, 4, 3):" + sol.sumRegion(2, 1, 4, 3));
    }
}
