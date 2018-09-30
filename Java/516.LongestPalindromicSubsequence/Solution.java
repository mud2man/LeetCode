/* Dynamic programming: O(n^2), where n is the length of string
 * 1. dp[tailIdx][headIdx] is the max length of palindromic subsequence between index headIdx and tailIdx
 * 2. dp[tailIdx][headIdx] = max(dp[tailIdx][headIdx + 1], dp[tailIdx - 1][headIdx], 
 *    (dp[tailIdx - 1][headIdx + 1] + 2) iff s.charAt(headIdx) == s.charAt(tailIdx))
 */

import java.util.*;

public class Solution{
    public int longestPalindromeSubseq(String s) {
        int size = s.length();
        int[][] dp = new int[size][size];
        int maxLength = (size == 0)? 0: 1;
        
        for(int tailIdx = 0; tailIdx < size; ++tailIdx){
            dp[tailIdx][tailIdx] = 1;
            char tailChar = s.charAt(tailIdx);
            for(int headIdx = tailIdx - 1; headIdx >= 0; headIdx--){
                char headChar = s.charAt(headIdx);
                int currLength = 0;
                if(headChar == tailChar){
                    int middlelLength = ((tailIdx - 1) >= (headIdx + 1))? dp[tailIdx - 1][headIdx + 1]: 0;
                    currLength = middlelLength + 2;
                }
                currLength = Math.max(currLength, dp[tailIdx][headIdx + 1]);
                currLength = Math.max(currLength, dp[tailIdx - 1][headIdx]);
                dp[tailIdx][headIdx] = currLength;
                maxLength = Math.max(maxLength, dp[tailIdx][headIdx]);
            }
        }
        
        return maxLength;
    }

    public static void main(String[] args){
        Solution sol;
        String s = "bbbab";
        sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("max length of palindromic subsequence: " + sol.longestPalindromeSubseq(s));
    }
}
