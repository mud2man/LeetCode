/* Dynamic Programming: Time:O(n^2), Space:O(n^2). LeetCode has O(1) solution
 * 1. dp[end][start] = the max sum with the first picker from {piles[start], ... piles[end]} 
 * 2. dp[end][start] = max(piles[start] + sum[end][start + 1] - dp[end][start + 1], dp[end][start], piles[end] + sum[end - 1][start] - dp[end - 1][start])
 * 3. If Alex's sum (dp[len - 1][0]) > Lee's (sum[len - 1][0] - dp[len - 1][0]), then Alex wins
 */

import java.util.*;

public class Solution{
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        int[][] sum = new int[len][len];
        for(int end = 0; end < len; ++end){
            int subSum = 0;
            for(int start = end; start >= 0; --start){
                subSum += piles[start];
                sum[end][start] = subSum;
            }
        }
        
        int[][] dp = new int[len][len];
        for(int end = 0; end < len; ++end){
            for(int start = end; start >= 0; --start){
                if(start == end){
                    dp[end][start] = piles[start];
                }else{
                    dp[end][start] = piles[start] + sum[end][start + 1] - dp[end][start + 1];
                    dp[end][start] = Math.max(dp[end][start], piles[end] + sum[end - 1][start] - dp[end - 1][start]);
                }
            }
        }
        return (dp[len - 1][0] > (sum[len - 1][0] - dp[len - 1][0]));
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] piles = {5, 3, 4, 5};
        System.out.println("piles:" + Arrays.toString(piles));
        System.out.println("can Alex win:" + sol.stoneGame(piles));
    }
}
