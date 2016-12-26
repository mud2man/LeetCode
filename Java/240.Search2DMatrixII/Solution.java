/* Use Dynamic Programing: O(n*m), m = primes.length
 * 1. Declare the pointer in the ugly number list, every pointer refer to the associated prime
 * 2. u(n) = min(u(ptr0)*p0, u(ptr1)*p1, ...)
 */

import java.util.*;

public class Solution{
    public boolean searchMatrix(int[][] matrix, int target) {
        int colNum;
        int rowNum;
        int y;
        int x;
        
        rowNum = matrix.length;
        if(rowNum == 0){
            return false;
        }
        colNum = matrix[0].length;
        
        y = rowNum - 1;
        x = 0;
        while((y >= 0) && (x < colNum)){
            if(matrix[y][x] == target){
                return true;
            }
            else if(matrix[y][x] < target){
                ++x;
            }
            else{
                --y;
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
			for(j = 0; j < matrix[i].length; ++j){
        		System.out.print(matrix[i][j] + ", ");
			}
        	System.out.println("");
		}

        System.out.println("target: " + target);
        System.out.println("isFound:" + sol.searchMatrix(matrix, target));

	}
}
