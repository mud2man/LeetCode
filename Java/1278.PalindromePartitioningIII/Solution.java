/* Dynamic programming: Time:O(n^2 * k), Space:O(max(n^2, n*k)), where n is s.length()
 * 1. counts[start][end] = the minimum replacement of s.substring(start, end + 1)
 * 2. dp[y][x] = minimum replacement of s.substring(0, x + 1) with (y + 1) division
 * 3. dp[y][x] = min(dp[y - 1][start - 1] + counts[start][end]), where  y <= start <= end. counts[start][end] is minimum replacement# of the last division
 */

import java.util.*;


public class Solution{
    public int palindromePartition(String s, int k) {
        int[][] counts = new int[s.length()][s.length()];
        for(int end = 0; end < s.length(); ++end){
            counts[end][end] = 0;
            for(int start = end - 1; start >= 0; --start){
                int count = (start + 1 < end - 1)? counts[start + 1][end - 1]: 0;
                count += (s.charAt(start) == s.charAt(end))? 0: 1;
                counts[start][end] = count;
            }
        }
        
        int[][] dp = new int[k][s.length()];
        for(int y = 0; y < k; ++y){
            for(int x = 0; x < s.length(); ++x){
                if(y == 0){
                    dp[y][x] = counts[0][x];
                }else if(y >= x){
                    dp[y][x] = 0;
                }else{
                    dp[y][x] = x + 1;
                    int end = x;
                    for(int start = end; start >= y; start--){
                        dp[y][x] = Math.min(dp[y][x], dp[y - 1][start - 1] + counts[start][end]);
                    }
                }
            }
        }
        return dp[k - 1][s.length() - 1];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "abc";
        int k = 2;
        System.out.println("s:" + s);
        System.out.println("k:" + k);
        System.out.println(String.format("minimum replacement of %d palindrome partition:%d", k, sol.palindromePartition(s, k)));
    }
}
