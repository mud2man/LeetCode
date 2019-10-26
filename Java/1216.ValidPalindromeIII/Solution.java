/* DynamicProgramming: O(n^2), where n is the length or s
 * 1. dp[head][tail] = the longest length of palindrome in s.substring(head, tail + 1)
 * 2. Return true if s.length() - dp[s.length() - 1][0] <= k
 *
 */

import java.util.*;

public class Solution{
    public boolean isValidPalindrome(String s, int k) {
        int[][] dp = new int[s.length()][s.length()];
        for(int tail = 0; tail < s.length(); ++tail){
            dp[tail][tail] = 1;
            for(int head = tail - 1; head >= 0; --head){
                if(s.charAt(head) == s.charAt(tail)){
                    dp[tail][head] = (tail - head == 1)? 2: dp[tail - 1][head + 1] + 2;
                }else{
                    dp[tail][head] = Math.max(dp[tail - 1][head], dp[tail][head + 1]);
                }
            }    
        }
        return (s.length() - dp[s.length() - 1][0] <= k);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "abcdeca";
        int k = 2;
        System.out.println("s: " + s);
        System.out.println("k: " + k);
        System.out.println(String.format("is %d-Palindrome: %b", k, sol.isValidPalindrome(s, k)));
    }
}
