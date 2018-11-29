/* Dynamic programming: O(n^2), where n is the length of string
 * 1. dp[tailIdx][headIdx] is the max length of palindromic subsequence between index headIdx and tailIdx
 * 2. dp[tailIdx][headIdx] = max(dp[tailIdx][headIdx + 1], dp[tailIdx - 1][headIdx], 
 *    (dp[tailIdx - 1][headIdx + 1] + 2) iff s.charAt(headIdx) == s.charAt(tailIdx))
 */

import java.util.*;

public class Solution{
   public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for(int tail = 0; tail < s.length(); ++tail){
            dp[tail][tail] = 1;
            for(int head = tail - 1; head >= 0; head--){
                if(s.charAt(tail) == s.charAt(head)){
                    dp[tail][head] = (tail - 1 >= head + 1)? 2 + dp[tail - 1][head + 1]: 2; 
                }
                dp[tail][head] = Math.max(dp[tail][head], dp[tail - 1][head]);
                dp[tail][head] = Math.max(dp[tail][head], dp[tail][head + 1]);
            }
        }
        return dp[s.length() - 1][0];
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "bbbab";
        sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("max length of palindromic subsequence: " + sol.longestPalindromeSubseq(s));
    }
}
