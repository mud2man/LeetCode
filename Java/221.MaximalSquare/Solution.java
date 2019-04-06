/* Dynamic Programming: Time:O(n*m), Space:O(n)
 * 1. dp[y][x][0] = the maximum length of upward column of '1' connect with (y, x)
 * 2. dp[y][x][1] = the maximum length of left-ward row of '1' connect with (y, x)
 * 3. dp[y][x][2] = the length of biggest square with right-bottom cornet at (y, x)
 * 4. In each round, update dp[y][x][0], dp[y][x][1] and dp[y][x][2]
 * 5. Also, update 'maxLen' by max(maxLen, dp[y][x][2]) 
 */

import java.util.*;


public class Solution{
    public int maximalSquare(char[][] matrix) {
        int depth = matrix.length;
        int width = (depth > 0)? matrix[0].length: 0;
        int[][][] dp = new int[2][width][3]; //up, left, upLeft
        
        int maxLen = 0;        
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                for(int i = 0; i < 3; ++i){
                   dp[y % 2][x][i] = 0; 
                }
                if(matrix[y][x] == '1'){
                    int up = (y > 0)? dp[(y - 1) % 2][x][0]: 0;
                    int left = (x > 0)? dp[y % 2][x - 1][1]: 0;
                    int upLeft = (y > 0 && x > 0)? dp[(y - 1) % 2][x - 1][2]: 0;
                    dp[y % 2][x][0] = up + 1;
                    dp[y % 2][x][1] = left + 1;
                    int currLen = Math.min(dp[y % 2][x][0], dp[y % 2][x][1]);
                    currLen = Math.min(upLeft + 1, currLen);
                    dp[y % 2][x][2] = currLen;
                    maxLen = Math.max(maxLen, currLen);
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
        
        System.out.println("matrix: ");
        for(char[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("maxSquare: " + sol.maximalSquare(matrix));
    }
}
