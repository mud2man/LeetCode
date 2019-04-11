/* Binary search: Time:O(log(n*m)), Space:O(1) 
 * 1. Treat the 2D matrix as a sorted list
 * 2. Use binary search to find the target
 */

import java.util.*;

public class Solution{
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0){
            return false;
        }
        
        int colNum = matrix[0].length;
        int rowNum = matrix.length;
        int len = rowNum * colNum;
        int lb = 0;
        int hb = len - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(matrix[mid / colNum][mid % colNum] > target){
                hb = mid - 1;
            }
            else if(matrix[mid / colNum][mid % colNum] < target){
                lb = mid + 1;
            }
            else{
                return true;
            }
        }
        return false;
    }
 
    public static void main(String[] args){
        Solution sol;
        int pathNum;
        int i, target;
        int[][] matrix = {{1, 3, 5, 7},
                          {10, 11, 16, 20},
                          {23, 30, 34, 50}};

        target = 3;
        sol = new Solution();
        
        System.out.println("matrix[][]: ");
        for(i = 0; i < matrix.length; ++i){
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("");

        if(sol.searchMatrix(matrix, target)){
            System.out.println("target:" + target + " can be found");
        }
        else{
            System.out.println("target:" + target + " cannot be found");
        }
    }
}
