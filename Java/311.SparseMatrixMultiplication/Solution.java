/* O(n*p*q)
 * 1. Traverse all the rows in A to check if any non-zero, if yes, nonZeroA[y] = true
 * 2. Traverse all the columns in B to check if any non-zero, if yes, nonZeroB[x] = true
 * 3. Execute multiply if nonZeroA[y]!=0 && nonZeroB[x]!=0
 */

import java.util.*;

public class Solution{
	public int multiplier(int[][] A, int[][] B, int x, int y){
        int addNum;
        int i;
        int sum;
        
        addNum = A[0].length;
        sum = 0;
        
        for(i = 0; i < addNum; ++i){
            sum = sum + A[y][i]*B[i][x];
        }
        
        return sum;
    }
    
    public int[][] multiply(int[][] A, int[][] B) {
        int rowNum;
        int colNum;
        int[][] result;
        int x;
        int y;
        boolean[] nonZeroA;
        boolean[] nonZeroB;
        
        
        rowNum = A.length;
        colNum = B[0].length;
        result = new int[rowNum][colNum];
        nonZeroA = new boolean[rowNum];
        nonZeroB = new boolean[colNum];
        
        for(y = 0; y < rowNum; ++y){
            nonZeroA[y] = false;
            for(x = 0; x < A[0].length; ++x){
                if(A[y][x] != 0){
                    nonZeroA[y] = true;
                    break;
                }
            }
        }
        
        for(x = 0; x < colNum; ++x){
            nonZeroB[x] = false;
            for(y = 0; y < B.length; ++y){
                if(B[y][x] != 0){
                    nonZeroB[x] = true;
                    break;
                }
            }
        }
        
        for(y = 0; y < rowNum; ++y){
            for(x = 0; x < colNum; ++x){
                if((nonZeroA[y] == false) || (nonZeroB[x] == false)){
                    result[y][x] = 0;
                }
                else{
                    result[y][x] = multiplier(A, B, x, y);
                }
            }
        }
        
        return result;
    }

	public static void main(String[] args){
		Solution sol;
		int i;
		int[][] result;
		int[][] A = {{1, 0, 0},
				     {-1, 0, 3}};
		int[][] B = {{7, 0, 0},
				     {0, 0, 0},
				     {0, 0, 1}};

		sol = new Solution();
		
		System.out.println("A[][]: ");	
		for(i = 0; i < A.length; ++i){
			System.out.println(Arrays.toString(A[i]));
		}
		
		System.out.println("B[][]: ");	
		for(i = 0; i < B.length; ++i){
			System.out.println(Arrays.toString(B[i]));
		}

		result = sol.multiply(A, B);

		System.out.println("result[][]: ");	
		for(i = 0; i < result.length; ++i){
			System.out.println(Arrays.toString(result[i]));
		}
	}
}
