/* Dynamic Programing: O(n*m)
 * 1. Create a 2D array to store partual sum
 * 2. sumMatrix(i, j) = sumMatrix(i, j-1) + sumMatrix(i-1, j) + x(i, j) - sumMatrix(i-1, j-1)
 * 3. regionSum(row1, col1, row2, col2) = sumMatrix(row2, col2) - sumMatrix(row2, col1 - 1 ) - sumMatrix(row1, col2 - 1) + sumMatrix(row1 - 1, col1 - 1)
 */

import java.util.*;

public class NumMatrix{
    private int[][] sumMatrix;
    public NumMatrix(int[][] matrix) {
        if(matrix.length == 0){
            return;
        }
        sumMatrix = new int[matrix.length][matrix[0].length];
        for(int y = 0; y < sumMatrix.length; ++y){
            for(int x = 0; x < sumMatrix[0].length; ++x){
                int up = (y > 0)? sumMatrix[y - 1][x]: 0;
                int left = (x > 0)? sumMatrix[y][x - 1]: 0;
                int upLeft = (y > 0 && x > 0)? sumMatrix[y - 1][x - 1]: 0;
                sumMatrix[y][x] = matrix[y][x] + up + left - upLeft;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int up = (row1 > 0)? sumMatrix[row1 - 1][col2]: 0;
        int left = (col1 > 0)? sumMatrix[row2][col1 - 1]: 0;
        int upLeft = (row1 > 0 && col1 > 0)? sumMatrix[row1 - 1][col1 - 1]: 0;
        return sumMatrix[row2][col2] - up - left + upLeft;
    }

    public static void main(String[] args){
        NumMatrix sol;
        int[][] matrix = {{3, 0, 1, 4, 2},
                          {5, 6, 3, 2, 1},
                          {1, 2, 0, 1, 5},
                          {4, 1, 0, 1, 7},
                          {1, 0, 3, 0, 5}};
    
        System.out.println("matrix:");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");

        sol = new NumMatrix(matrix);
        System.out.println("(2, 1, 4, 3) = " + sol.sumRegion(2, 1, 4, 3));
        System.out.println("(1, 1, 2, 2) = " + sol.sumRegion(1, 1, 2, 2));
        System.out.println("(1, 2, 2, 4) = " + sol.sumRegion(1, 2, 2, 4));
        System.out.println("(1, 2, 1, 2) = " + sol.sumRegion(1, 2, 1, 2));

    }
}
