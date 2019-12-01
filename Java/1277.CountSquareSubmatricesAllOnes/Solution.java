/* Dynamic Programming: Time:O(n*m), Space:O(n). We can only consider the square with right-bottom cornet at (y, x), and reduce space to O(1)
 * 1. dp[y][x][0] = the maximum length of upward column of '1' connect with (y, x)
 * 2. dp[y][x][1] = the maximum length of left-ward row of '1' connect with (y, x)
 * 3. dp[y][x][2] = the length of biggest square with right-bottom cornet at (y, x)
 * 4. In each round, update dp[y][x][0], dp[y][x][1] and dp[y][x][2]
 * 5. Also, accumulate count with dp[y][x][2] 
 */

import java.util.*;


public class Solution{
    public int countSquares(int[][] matrix) {
        int[][][] dp = new int[matrix.length][matrix[0].length][3]; //up, left, square
        int count = 0;
        for(int y = 0; y < matrix.length; ++y){
            for(int x = 0; x < matrix[0].length; ++x){
                if(matrix[y][x] == 1){
                    dp[y][x][0] = (y > 0)? dp[y - 1][x][0] + 1: 1;
                    dp[y][x][1] = (x > 0)? dp[y][x - 1][1] + 1: 1;
                    dp[y][x][2] = (y > 0 && x > 0)? Math.min(Math.min(dp[y][x][0], dp[y][x][1]), dp[y - 1][x - 1][2] + 1): 1;
                }
                count += dp[y][x][2];
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] matrix = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("square number: " + sol.countSquares(matrix));
    }
}
