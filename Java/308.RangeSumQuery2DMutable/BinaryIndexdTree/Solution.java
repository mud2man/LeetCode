/* Time:O(logn*logm), Space:O(n*m).
 * 1. When update, call getNext in x direction and y direction to update tree[][]
 * 2. When sum, call getParent in x direction and y direction to accumulate the answer
 * 3. In region sum, answer = sum(row2, col2) - sum(row2, col1 - 1) - sum(row1 - 1, col2) + sum(row1 - 1, col1 - 1)
 */

import java.util.*;

public class Solution{
    int[][] tree;
    int depth;
    int width;
    int[][] matrix;
    
    public Solution(int[][] matrix) {
        depth = matrix.length + 1;
        width = (matrix.length > 0) ?matrix[0].length + 1: 1;
        tree = new int[depth][width];
        this.matrix = new int[depth - 1][width - 1];
        for(int y = 0; y < (depth - 1); ++y){
            for(int x = 0; x < (width - 1); ++x){
                update(y, x, matrix[y][x]);
            }
        }
    }
    
    private int getNext(int x){
        return x + (x & (-x));
    }
    
    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
    
        for(int y = row + 1; y < depth; y = getNext(y)){
            for(int x = col + 1; x < width; x = getNext(x)){
                tree[y][x] += diff;
            }
        }
    }
    
    private int getParent(int x){
        return x - (x & (-x));
    }
    
    private int sum(int row, int col){
        if(row < 0 || col < 0){
            return 0;
        }
        
        int sum = 0;
        for(int y = row + 1; y > 0; y = getParent(y)){
            for(int x = col + 1; x > 0; x = getParent(x)){
                sum += tree[y][x];
            }
        }
        return sum;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row2, col2) - sum(row2, col1 - 1) - sum(row1 - 1, col2) + sum(row1 - 1, col1 - 1);
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
