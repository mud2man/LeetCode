/* Search: O(n + m)
 * 1. Startting from the first column, traverse from the last element until matrix[y][x] < target
 * 2. Repeat step.1 until target found, or run out of all columns
 */

import java.util.*;

public class Solution{
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0){
            return false;
        }
        
        int y = matrix.length - 1;
        for(int x = 0; x < matrix[0].length; ++x){
            for(y = y; y >= 0; --y){
                if(matrix[y][x] == target){
                    return true;
                }else if(matrix[y][x] < target){
                    break;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args){
        int i;
        int j;
        Solution sol;
        int target;
        int[][] matrix = {
                          {1,   4,  7, 11, 15},
                          {2,   5,  8, 12, 19},
                          {3,   6,  9, 16, 22},
                          {10, 13, 14, 17, 24},
                          {18, 21, 23, 26, 30}
                          };
           target = 17;
        sol = new Solution();
 
        System.out.println("matrix[][]:");
        for(i = 0; i < matrix.length; ++i){
            System.out.println(Arrays.toString(matrix[i]));
        }

        System.out.println("target: " + target);
        System.out.println("isFound:" + sol.searchMatrix(matrix, target));

    }
}
