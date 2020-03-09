/* Dynamic programming(min-max):O(K*N), Space:O(K*N), LeetCode has O(KlongN) solution
 * 1. dp[y][x] = the minimum moves given K = y and N = x
 * 2. dp[y][x] = min(max(dp[y - 1][mid - 1](left), dp[y][x - mid](right))), where 1 <= mid <= x
 * 3. Given y and x, our goal is to fine the minimum of max("left", "right"), where "left" is an increasing function while right is decreasing function
 * 4. Therefore max("left", "right") is ----+++++ like function
 * 5. So we keep shift mid to right as long as Math.max(dp[y - 1][mid - 1](left), dp[y][x - mid](right)) > Math.max(dp[y - 1][mid](left), dp[y][x - mid - 1](right))
 */

import java.util.*;

public class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K][N + 1];
        for(int x = 0; x <= N; ++x){
            dp[0][x] = x;
        }
        
        for(int y = 1; y < K; ++y){
            dp[y][1] = 1;
            int mid = 1;
            for(int x = 2; x <= N; ++x){
                while(mid < x && Math.max(dp[y - 1][mid - 1], dp[y][x - mid]) > Math.max(dp[y - 1][mid], dp[y][x - mid - 1])){
                    mid++;
                }
                dp[y][x] = Math.max(dp[y - 1][mid - 1], dp[y][x - mid]) + 1;
            }
            
        }
        return dp[K - 1][N];
    }
  
    public static void main(String[] args){
        int K = 2;
        int N = 6;
        Solution sol = new Solution();
        System.out.println("K:" + K + ", N:" + N);
        System.out.println("minimum number of moves:" + sol.superEggDrop(K, N));
    }
}
