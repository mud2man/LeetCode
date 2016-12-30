/* Simple traverse: O(n*m),
 * 1. Check if the first row is zeros
 * 2. Check if the first column is zeros
 * 3. Check if zero among matrix[y][x], where x > 1, y > 1, 
 *	  and put the result in matrix[0][x] and matrix[y][0]
 * 4. Reset matrix by matrix[0][x] and matrix[y][0] 
 */

import java.util.*;

public class Solution{
	public void setZeroes(int[][] matrix) {
        int y;
        int x;
        int rowNum;
        int colNum;
        boolean isZeroFirstRow;
        boolean isZeroFirstCol;
        
        rowNum = matrix.length;
        colNum = 0;
        if(rowNum > 0){
           colNum =  matrix[0].length;
        }
        
        isZeroFirstRow = false;
        for(x = 0; x < colNum; ++x){
            if(matrix[0][x] == 0){
                isZeroFirstRow = true;
                break;
            }    
        }
        
        isZeroFirstCol = false;
        for(y = 0; y < rowNum; ++y){
            if(matrix[y][0] == 0){
                isZeroFirstCol = true;
                break;
            }    
        }
        
        //record zeroed column and zerod row
        for(y = 1; y < rowNum; ++y){
            for(x = 1; x < colNum; ++x){
                if(matrix[y][x] == 0){
                    matrix[0][x] = 0;
                    matrix[y][0] = 0;
                }
            }  
        }
        
        //reset columns to zeros
        for(x = 1; x < colNum; ++x){
            if(matrix[0][x] == 0){
                for(y = 1; y < rowNum; ++y){
                    matrix[y][x] = 0;
                }
            }
        }
        
        //reset rows to zeros
        for(y = 1; y < rowNum; ++y){
            if(matrix[y][0] == 0){
                for(x = 1; x < colNum; ++x){
                    matrix[y][x] = 0;
                }
            }
        }
        
        if(isZeroFirstRow == true){
            for(x = 0; x < colNum; ++x){
                matrix[0][x] = 0;
            }
        }
        
        if(isZeroFirstCol == true){
            for(y = 0; y < rowNum; ++y){
                matrix[y][0] = 0;
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
