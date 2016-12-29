/* Dynamic programming: O(n*m),
 * 1. Traverse from dp[y][x] to dp[0][0]
 */

import java.util.*;

public class Solution{
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp;
        int rowNum;
        int colNum;
        int x;
        int y;
        
        rowNum = obstacleGrid.length;
        colNum = obstacleGrid[0].length;
        dp = new int[rowNum][colNum];
        
        for(y = rowNum - 1; y >= 0; --y){
            for(x = colNum - 1; x >= 0; --x){
                if(obstacleGrid[y][x] == 1){
                    dp[y][x] = 0;
                }
                else{
                    if((y < (rowNum - 1)) && (x < (colNum - 1))){
                        dp[y][x] = dp[y][x + 1] + dp[y + 1][x];
                    }
                    else if((y == (rowNum -1)) && (x < (colNum - 1))){
                        dp[y][x] = dp[y][x + 1];
                    }
                    else if((y < (rowNum - 1)) && (x == (colNum - 1))){
                        dp[y][x] = dp[y + 1][x];
                    }
                    else{
                        dp[y][x] = 1;;
                    }
                }
            }
        }
        return dp[0][0];
    }
 
    public static void main(String[] args){
		Solution sol;
		int pathNum;
		int i;
		int[][] obstacleGrid = {{0, 0, 0},
								{0, 1, 0},
								{0, 0, 0}};

		sol = new Solution();
		pathNum = sol.uniquePathsWithObstacles(obstacleGrid);
		
		for(i = 0; i < obstacleGrid.length; ++i){
        	System.out.println(Arrays.toString(obstacleGrid[i]));
		}

        System.out.println("#path: " + pathNum);
	}
}
