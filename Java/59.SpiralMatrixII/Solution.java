/* Time:O(n^2), Space:O(1)
 * 1. Have a "start" position to start fill "val" following 4 directions until val > n^2
 */

import java.util.*;


public class Solution{
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int width = n - 1;
        int val = 1;
        int[] start = {0, 0};
        while(val <= n * n){
            int[] curr = start;
            if(width == 0){
                matrix[curr[0]][curr[1]] = val++;
                break;
            }
            for(int[] dir: dirs){
                for(int i = 0; i < width && val <= n * n; ++i){
                    matrix[curr[0]][curr[1]] = val++;
                    curr[0] += dir[0];
                    curr[1] += dir[1];
                }
            }
            start = new int[]{start[0] + 1, start[1] + 1};
            width -= 2;
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
