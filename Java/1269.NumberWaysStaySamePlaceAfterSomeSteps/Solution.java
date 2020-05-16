/* Dynamic Programming: Time:O(steps * min(steps, arrLen)), Space:O(min(steps, arrLen))
 * 1.dp[y][x] = the number of ways ending at x with (y + 1) steps
 * 2.dp[y][x] = dp[y - 1][x - 1](from left) + dp[y - 1][x](stay) + dp[y - 1][x + 1](from right)
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    public int numWays(int steps, int arrLen) {
        int base = 1_000_000_007;
        int width = Math.min(steps + 1, arrLen);
        int[][] dp = new int[2][width];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for(int step = 1; step < steps; ++step){
            for(int x = 0; x < width; ++x){
                int fromLeft =(x > 0)? dp[(step - 1) % 2][x - 1]: 0;
                int fromRight =(x < width - 1)? dp[(step - 1) % 2][x + 1]: 0;
                dp[step % 2][x] = (dp[(step - 1) % 2][x] + fromLeft) % base;
                dp[step % 2][x] = (dp[step % 2][x] + fromRight) % base;
            }
        }
        return dp[(steps - 1) % 2][0];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int steps = 3; 
        int arrLen = 2;
        System.out.println("steps:" + steps);
        System.out.println("arrLen:" + arrLen);
        System.out.println("number of ways:" + sol.numWays(steps, arrLen));
    }
}
