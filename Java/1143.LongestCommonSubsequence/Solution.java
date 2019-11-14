/* Dynamic programming: Time:O(n*m), Space:O(n*m). We can reduce space to O(m)
 * 1. dp[y][x] = the length of longest common subsequence between text1.sustring(y + 1) and text2.sustring(x + 1)
 * 2. dp[y][x] =(text1Char == text2Char)? max(left, top, topLeft + 1): max(left, top)
 */

import java.util.*; // Stack

public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for(int y = 0; y < text1.length(); ++y){
            for(int x = 0; x < text2.length(); ++x){
                char text1Char = text1.charAt(y);
                char text2Char = text2.charAt(x);
                int topLeft = (y > 0 && x > 0)? dp[y - 1][x - 1]: 0;
                int top = (y > 0)? dp[y - 1][x]: 0;
                int left = (x > 0)? dp[y][x - 1]: 0;
                dp[y][x] =(text1Char == text2Char)? Math.max(left, Math.max(top, topLeft + 1)): Math.max(left, top);
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("text1:" + text1);
        System.out.println("text2:" + text2);
        System.out.println("length of longest common subsequence:" + sol.longestCommonSubsequence(text1, text2));
    }
}
