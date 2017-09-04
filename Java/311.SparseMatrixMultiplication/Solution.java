/* O(n*p*q)
 * 1. Traverse all element of row y in A to check if all zeros, if yes, cloneA[y] = null
 * 2. Traverse all elements of column x in B to check if zeros, if yes, tarnsfomB[x] = null
 * 3. Execute inerMultiply if A[] != null && B[x] != null
 */

import java.util.*;

public class Solution{
    private int inerMultiply(int[] A, int[] B){
        if(A == null || B == null){
            return 0;
        }
        
        int ans = 0;
        for(int i = 0; i < A.length; ++i){
            ans = ans + A[i]*B[i];    
        }    
        return ans;
    }
    
    public int[][] multiply(int[][] A, int[][] B) {
        int lengthA = A.length;
        int widthA = A[0].length;
        int lengthB = B.length;
        int widthB = B[0].length;
        int[][] tarnsfomB = new int[widthB][lengthB];
        int[][] cloneA = new int[lengthA][widthA];
        int[][] AB = new int[lengthA][widthB];
        
        for(int x = 0; x < widthB; ++x){
            boolean isZero = true;
            for(int y = 0; y < lengthB; ++y){
                tarnsfomB[x][y] = B[y][x];
                isZero = (tarnsfomB[x][y] != 0)? false: isZero;
            }
            
            if(isZero){
                tarnsfomB[x] = null;
            }
        }
        
        for(int y = 0; y < lengthA; ++y){
            boolean isZero = true;
            for(int x = 0; x < widthA; ++x){
                cloneA[y][x] = A[y][x];
                isZero = (A[y][x] != 0)? false: isZero;
            }
            
            if(isZero){
                cloneA[y] = null;
            }
        }

        for(int y = 0; y < A.length; ++y){
            for(int x = 0; x < tarnsfomB.length; ++x){
                AB[y][x] = inerMultiply(cloneA[y], tarnsfomB[x]);
            }
        }
        
        return AB;
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
