/* Greedy: Time:O(n*m), Space:O(n*m). LeetCode has Space:O(s) solution
 * 1. Since the LSB is the most important. There are only two cases can cause max score
 * 2. Case1: swap rows and makes all A[i][0] as 0, then swap and check each columns to get the max score
 * 3. Case2: swap rows and makes all A[i][0] as 1, then swap and check each columns to get the max score
 * 4. Select the biiger score between case1 and case2
 */         

import java.util.*;

public class Solution {
    private void swapRow(int[][] A, int y){
        for(int x = 0; x < A[0].length; ++x){
            A[y][x] = (A[y][x] == 0)? 1: 0;
        }
    }
    
    private int swapCol(int[][] A, int x){
        int count = 0;
        for(int y = 0; y < A.length; ++y){
            A[y][x] = (A[y][x] == 0)? 1: 0;
            count += (A[y][x] == 1)? 1: 0;
        }
        return count;
    }
    
    private int getScore(int[][] A){
        int total = 0;
        for(int y = 0; y < A.length; ++y){
            int score = 0;
            for(int x = 0; x < A[0].length; ++x){
                score = score * 2 + A[y][x];
            }
            total += score;
        }
        return total;
    }
    
    public int matrixScore(int[][] A) {
        int depth = A.length;
        int width = A[0].length;
        int[][] A0 = new int[depth][width];
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                A0[y][x] = A[y][x];
            }
        }
        
        //LSB = 0
        for(int y = 0; y < depth; ++y){
            if(A0[y][0] == 1){
                swapRow(A0, y);
            }
        }
        for(int x = 0; x < width; ++x){
            if(swapCol(A0, x) <= depth / 2){
                swapCol(A0, x);
            }
        }
        int score = getScore(A0);
        
        //LSB = 1
        for(int y = 0; y < depth; ++y){
            if(A[y][0] == 0){
                swapRow(A, y);
            }
        }
        for(int x = 0; x < width; ++x){
            if(swapCol(A, x) <= depth / 2){
                swapCol(A, x);
            }
        }
        score = Math.max(getScore(A), score);
        
        return score;
    }
 
    public static void main(String[] args){
        Solution sol= new Solution();
        int[][] grid = {{0, 0, 1, 1}, {1, 0, 1, 0},{1, 1, 0, 0}};

        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("maximum score: " + sol.matrixScore(grid));
    }
}
