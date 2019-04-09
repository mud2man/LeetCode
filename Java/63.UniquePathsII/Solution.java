/* Dynamic programming: Time:O(n*m), Space:O(n*m)
 * 1. Traverse from dp[0][0], and return the right-bottom corner
 */

import java.util.*;

public class Solution{
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int [obstacleGrid.length][obstacleGrid[0].length];
        for(int y = 0; y < obstacleGrid.length; ++y){
            for(int x = 0; x < obstacleGrid[0].length; ++x){
                if(obstacleGrid[y][x] == 1){
                    dp[y][x] = 0;
                }
                else{
                    int upCount = (y > 0)? dp[y - 1][x]: 0;
                    int leftCount = (x > 0)? dp[y][x - 1]: 0;
                    dp[y][x] = (y == 0 && x == 0)? 1: upCount + leftCount;
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1]; 
    }
 
    public static void main(String[] args){
		Solution sol = new Solution();;
		int[][] obstacleGrid = {{0, 0, 0},
                                {0, 1, 0},
                                {0, 0, 0}};
        
        for(int i = 0; i < obstacleGrid.length; ++i){
        	System.out.println(Arrays.toString(obstacleGrid[i]));
        }
        System.out.println("#path: " + sol.uniquePathsWithObstacles(obstacleGrid));
	}
}
