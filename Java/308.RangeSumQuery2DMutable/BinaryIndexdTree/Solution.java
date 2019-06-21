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
        int width = (depth > 0)? matrix[0].length: 0;
        binaryIndexTree = new int[depth + 1][width + 1];
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                put(y + 1, x + 1, matrix[y][x]);
            }
        }
        this.matrix = matrix;
    }
    
    private int next(int curr){
        return curr + (curr & (-curr));
    }
    
    private int prev(int curr){
        return curr - (curr & (-curr));
    }
    
    private void put(int row, int col, int diff){
        for(int y = row; y < binaryIndexTree.length; y = next(y)){
            for(int x = col; x < binaryIndexTree[0].length; x = next(x)){
                binaryIndexTree[y][x] += diff;
            }   
        }
    }
    
    private int get(int row, int col){
        int sum = 0;
        for(int y = row; y > 0; y = prev(y)){
            for(int x = col; x > 0; x = prev(x)){
                sum += binaryIndexTree[y][x];
            }   
        }
        return sum;
    }
    
    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        put(row + 1, col + 1, diff);
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int bottomRight = get(row2 + 1, col2 + 1);
        int bottomLeft = get(row2 + 1, col1);
        int topLeft = get(row1, col1);
        int topRight = get(row1, col2 + 1);
        int sum = bottomRight - bottomLeft - topRight + topLeft;
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
