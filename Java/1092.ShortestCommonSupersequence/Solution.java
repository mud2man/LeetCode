/* Dynamic programming: Time:O(n*m), Space:O(n*m)
 * 1. Like longest common sequence, dp[y][x] = the shortest supersequence of str1.substring(0, x) and str2.substring(0, y)
 * 2. Reconstruct the supersequence from bottom right, pick the next char which's value is dp[y][x] - 1
 */

import java.util.*; // Stack

public class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int width = str1.length() + 1;
        int depth = str2.length() + 1;
        int[][] dp = new int[depth][width];
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(y == 0){
                    dp[y][x] = x;
                }else if(x == 0){
                    dp[y][x] = y;
                }else{
                    if(str1.charAt(x - 1) == str2.charAt(y - 1)){
                        dp[y][x] = dp[y - 1][x - 1] + 1;
                    }else{
                        dp[y][x] = Math.min(dp[y][x - 1], dp[y - 1][x]) + 1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder("");
        int y = depth - 1;
        int x = width - 1;
        while(y > 0 || x > 0){
            if(x == 0){
                sb.append(str2.charAt(--y));
            }else if(y == 0){
                sb.append(str1.charAt(--x));
            }else if(dp[y][x - 1] == dp[y][x] - 1){
                sb.append(str1.charAt(--x));
            }else if(dp[y - 1][x] == dp[y][x] - 1){
                sb.append(str2.charAt(--y));
            }else{
                sb.append(str2.charAt(--y));
                x--;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String str1 = "abac";
        String str2 = "cab";
        System.out.println("str1:" + str1);
        System.out.println("str2:" + str2);
        System.out.println("shortest common supersequence:" + sol.shortestCommonSupersequence(str1, str2));
    }
}
