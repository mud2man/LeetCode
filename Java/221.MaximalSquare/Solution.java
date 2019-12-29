/* Dynamic Programming: Time:O(n*m), Space:O(n)
 * 1. dp[y][x] = the length of biggest square with right-bottom cornet at (y, x)
 * 2. In each round, update dp[y][x] by min(dp[y - 1][x], dp[y][x - 1], dp[y - 1][x - 1]) + 1
 * 3. Also, update 'max' by max(max, dp[y][x]) 
 */

import java.util.*;


public class Solution{
    public int maximalSquare(char[][] matrix) {
        int depth = matrix.length;
        int width = (depth > 0)? matrix[0].length: 0;
        int[][] dp = new int[depth][width];
        int max = 0;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(matrix[y][x] == '1'){
                    int topSquare = (y > 0)? dp[y - 1][x]: 0;
                    int leftSquare = (x > 0)? dp[y][x - 1]: 0;
                    int topLeftSquare = (y > 0 && x > 0)? dp[y - 1][x - 1]: 0;
                    dp[y][x] = Math.min(topLeftSquare, Math.min(topSquare, leftSquare)) + 1;
                    max = Math.max(max, dp[y][x]);
                }
            }
        }
        return max * max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                           {'1', '0', '1', '1', '1'},
                           {'1', '1', '1', '1', '1'},
                           {'1', '0', '0', '1', '0'}};
        
        System.out.println("matrix: ");
        for(char[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("maxSquare: " + sol.maximalSquare(matrix));
    }
}
