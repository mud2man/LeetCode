/* Dynamic programming: Time:O(N*L), Space:O(N*L)
 * 1. dp[y][x] = the number of play lists have unique y songs with length x
 * 2. If y > x, dp[y][x] = dp[y][x - 1] * (y - K) + dp[y - 1][x - 1] * (N - (y - 1))
 * 3. Otherwise, dp[y][x] = dp[y][x - 1] * (N - x + 1)
 */

import java.util.*; // Stack

public class Solution {
    public int numMusicPlaylists(int N, int L, int K) {
        long constant = 1_000_000_007;
        long[][] dp = new long[N + 1][L + 1];
        for(int x = 0; x <= L; ++x){
            for(int y = 0; y <= N; ++y){
                if(x == 0){
                    dp[y][x] = 1;
                }else{
                    if(y > 0 && y >= K){
                        if(x <= y){
                            dp[y][x] = (dp[y][x - 1] * (long)(N - x + 1)) % constant;
                        }else{
                            dp[y][x] = (dp[y][x - 1] * (long)(y - K)) % constant;
                            dp[y][x] = (dp[y][x] + (dp[y - 1][x - 1] * (long)(N - (y - 1))) % constant) % constant;
                        }
                    }
                }
            }
        }
        return (int)dp[N][L];
    }
  
    public static void main(String[] args) {
        int N = 3;
        int L = 3;
        int K = 1;
        Solution sol = new Solution();
        System.out.println("N:" + N + ", L:" + L + ", K:" + K);
        System.out.println("playlists#:" + sol.numMusicPlaylists(N, L, K));
    }
}
