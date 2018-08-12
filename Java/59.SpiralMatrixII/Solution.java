/* Time:O(n^2), Space:O(1)
 * 1. Have a utility method "fillRing" to fill the ring of the matrix
 * 2. Every time, start fill the ring from top-left corner, and update ring's length and start
 */

import java.util.*;


public class Solution{
    private void fillRing(int[][] matrix, int y, int x, int start, int len){
        if(len == 1){
            matrix[y][x] = start;
            return;
        }
        
        //top
        for(int i = 1; i < len; ++i){
            matrix[y][x++] = start++;
        }
        //right
        for(int i = 1; i < len; ++i){
            matrix[y++][x] = start++;
        }
        //bottom
        for(int i = 1; i < len; ++i){
            matrix[y][x--] = start++;
        }
        //left
        for(int i = 1; i < len; ++i){
            matrix[y--][x] = start++;
        }
    }
    
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int start = 1;
        int length = n;
        int y = 0;
        int x = 0;
        while(length >= 0){
            fillRing(matrix, y, x, start, length);
            ++y;
            ++x;
            start += (length - 1) * 4;
            length -= 2;
        }
        return matrix;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 3;

        int[][] matrix = sol.generateMatrix(n);
        System.out.println("n: " + n);
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
    }
}
