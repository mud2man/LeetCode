/* Dynamic Programming: Time:O(D*n^2), Space:O(D*n)
 * 1. dp[y][x] is the min capacity to ship {0, 1, ..., x} in y days
 * 2. When update dp[y][x], we get the solution from dp[y - 1][i - 1] + sum(weights[i], weights[i + 1], ... weights[x]), where 0 < i <= x
 */

import java.util.*;

public class Solution{
    public int shipWithinDays(int[] weights, int D) {
        int[][] dp = new int[D][weights.length];
        for(int x = 0; x < weights.length; ++x){
            dp[0][x] = (x == 0)?  weights[x]: dp[0][x - 1] + weights[x];
        }
        
        for(int y = 1; y < D; ++y){
            for(int x = 0; x < weights.length; ++x){
                int lastShip = 0;
                int minCapacity = Integer.MAX_VALUE;
                for(int i = x; i > 0; --i){
                    lastShip += weights[i];
                    int currentCapacity = Math.max(lastShip, dp[y - 1][i - 1]);
                    minCapacity = Math.min(minCapacity, currentCapacity);
                }
                dp[y][x] = minCapacity;
            }
        }
        return dp[D - 1][weights.length - 1];
    }
	
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;
        System.out.println("weights:" + Arrays.toString(weights));
        System.out.println("D:" + D);
        System.out.println("least weight capacity:" + sol.shipWithinDays(weights, D));
	}
}
