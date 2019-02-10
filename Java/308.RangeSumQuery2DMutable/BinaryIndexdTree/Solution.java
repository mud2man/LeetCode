/* Binary Indexd Tree: Time:O(logn*logm), Space:O(n*m).
 * 1. When update, call getNext in x direction and y direction to update tree[][]
 * 2. When sum, call getParent in x direction and y direction to accumulate the answer
 * 3. In region sum, answer = sum - topSum - leftSum + leftTopSum
 */

import java.util.*;

public class Solution{
    int[][] binaryIndexTree;
    int[][] matrix;
    public NumMatrix(int[][] matrix) {
        int depth = matrix.length;
        int width = (matrix.length > 0)? matrix[0].length: 0;
        this.matrix = new int[depth][width];
        binaryIndexTree = new int[depth + 1][width + 1];
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                update(y, x, matrix[y][x]);
            }
        }
    }
    
    private int next(int x){
        return x + (x & (-x));
    }
    
    private int prev(int x){
        return x - (x & (-x));
    } 
    
    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        for(int y = row + 1; y < binaryIndexTree.length; y = next(y)){
            for(int x = col + 1; x < binaryIndexTree[0].length; x = next(x)){
                binaryIndexTree[y][x] += diff; 
            }
        }
    }
    
    private int getSumFromLeftTop(int y, int x){
        int sum = 0;
        for(int i = y + 1; i > 0; i = prev(i)){
            for(int j = x + 1; j > 0; j = prev(j)){
                sum += binaryIndexTree[i][j];
            }
        }
        return sum;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int leftTopSum = (row1 > 0 && col1 > 0)? getSumFromLeftTop(row1 - 1, col1 - 1): 0;
        int leftSum = (col1 > 0)? getSumFromLeftTop(row2, col1 - 1): 0;
        int topSum = (row1 > 0)? getSumFromLeftTop(row1 - 1, col2): 0;
        int sum = getSumFromLeftTop(row2, col2);
        return sum - leftSum - topSum + leftTopSum; 
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
