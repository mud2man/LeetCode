/* Simple traverse: O(n*m),
 * 1. Check if the first row is zeros
 * 2. Check if the first column is zeros
 * 3. Check if zero among matrix[y][x], where x > 1, y > 1, 
 *      and put the result in matrix[0][x] and matrix[y][0]
 * 4. Reset matrix by matrix[0][x] and matrix[y][0] 
 */

import java.util.*;

public class Solution{
    public void setZeroes(int[][] matrix) {
        int depth = matrix.length;
        int width = (depth == 0)? 0: matrix[0].length;
        boolean firstRowZeros = false;
        boolean firstColumZeros = false;
        
        for(int y = 0; y < depth; ++y){
            if(matrix[y][0] == 0){
                firstColumZeros = true;
                break;
            }
        }
        
        for(int x = 0; x < width; ++x){
            if(matrix[0][x] == 0){
                firstRowZeros = true;
                break;
            }
        }
        
        for(int y = 1; y < depth; ++y){
            for(int x = 1; x < width; ++x){
                if(matrix[y][x] == 0){
                    matrix[0][x] = 0;
                    matrix[y][0] = 0;
                }
            }
        }
        
        for(int y = 1; y < depth; ++y){
            for(int x = 1; x < width; ++x){
                if(matrix[y][0] == 0 || matrix[0][x] == 0){
                    matrix[y][x] = 0;
                }
            }
        }
        
        for(int y = 0; y < depth; ++y){
            if(firstColumZeros == true){
                matrix[y][0] = 0;
            }
        }
        
        for(int x = 0; x < width; ++x){
            if(firstRowZeros == true){
                matrix[0][x] = 0;
            }
        }
    }
 
    public static void main(String[] args){
        Solution sol;
        int pathNum;
        int i;
        int[][] matrix = {{0, 1, 3},
                          {1, 1, 4},
                          {5, 0, 4}};

        sol = new Solution();
        
        System.out.println("before reset matrix[][]: ");
        for(i = 0; i < matrix.length; ++i){
            System.out.println(Arrays.toString(matrix[i]));
        }

        sol.setZeroes(matrix);
        
        System.out.println("after reset matrix[][]: ");
        for(i = 0; i < matrix.length; ++i){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
