/* Dynamic Programming: O(n*m)
 * 1. dp[y][x] = the length of biggest square with right-bottom cornet at (y, x)
 * 2. Let upLength = dp[y - 1][x], leftLength = dp[y][x - 1]
 * 3. If upLength != leftLength, dp[y][x] = min(upLength, leftLength) + 1
 * 4. Otherwise, if matrix[y - upLength][x - leftLength] == '1', then dp[y][x] = upLength + 1
 * 5. Otherwise, dp[y][x] = upLength
 */

import java.util.*;


public class Solution{
    public int maximalSquare(char[][] matrix) {
        int depth = matrix.length;
        int width = (depth > 0)? matrix[0].length: 0;
        int[][] dp = new int[depth][width];
        
        int maxLen = 0;
        
        for(int x = 0; x < width; ++x){
            dp[0][x] = (matrix[0][x] == '1')? 1: 0;
            maxLen = Math.max(maxLen, dp[0][x]);
        }
        
        for(int y = 1; y < depth; ++y){
            dp[y][0] = (matrix[y][0] == '1')? 1: 0;
            maxLen = Math.max(maxLen, dp[y][0]);
        }
        
        for(int y = 1; y < depth; ++y){
            for(int x = 1; x < width; ++x){
                if(matrix[y][x] == '1'){
                    int upLength = dp[y - 1][x];
                    int leftLength = dp[y][x - 1];
                    if(upLength != leftLength){
                        int minLength = Math.min(upLength, leftLength);
                        dp[y][x] = minLength + 1;
                    }
                    else{
                        if(matrix[y - upLength][x - leftLength] == '1'){
                            dp[y][x] = upLength + 1;
                        }
                        else{
                            dp[y][x] = upLength;
                        }
                    }
                    maxLen = Math.max(maxLen, dp[y][x]);
                }
            }
        }
        return (maxLen * maxLen);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                           {'1', '0', '1', '1', '1'},
                           {'1', '1', '1', '1', '1'},
                           {'1', '0', '0', '1', '0'}};
        int maxSquare;

        maxSquare = sol.maximalSquare(matrix);
        
        System.out.println("matrix: ");
        for(char[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("maxSquare: " + maxSquare);
    }
}
