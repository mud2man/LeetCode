/* Dynamic programming: Time:O(n ^ 2 * k), Space:O(n * k)
 * 1. dp[y][x] = the minimum length of encoded with y deleted chars in s.substring(0, x)
 * 2. When keeping currChar, dp[y][x] = min{dp[y - deleteCount][i - 1] + getEncodeLen(concatCount), ...} where 1 <= i <= x, 
 *    while considering the rightest group of 'currChar'
 * 3. When deleting currChar, dp[y][x] = min(dp[y][x],  dp[y - 1][x - 1])
 * 4. The key is to consider the rightest group of 'currChar' and increase its range since the optimal answer must be among the cases during increasing
 */

import java.util.*; // Stack


public class Solution {
    private int getEncodeLen(int count) {
        return (count <= 1)? 1: 2 + (int)(Math.log(count) / Math.log(10));
    }

    public int getLengthOfOptimalCompression(String s, int k) {
        int[][] dp = new int[k + 1][s.length() + 1];
        for(int y = 0; y <= k; y++){
            for(int x = 1; x <= s.length(); x++){
                dp[y][x] = s.length();
                char currChar = s.charAt(x - 1);
                int deleteCount = 0;
                int concatCount = 0;
                //keep currChar, factor on rightest group of 'currChar'
                for(int i = x; i > 0; i--){
                    deleteCount +=(s.charAt(i - 1) != currChar)? 1: 0;
                    concatCount +=(s.charAt(i - 1) == currChar)? 1: 0;
                    if(deleteCount > y){
                        break;
                    }
                    int leftLength = dp[y - deleteCount][i - 1];
                    dp[y][x] = Math.min(dp[y][x], leftLength + getEncodeLen(concatCount));
                }
                //delete currChar
                if(y > 0){
                    dp[y][x] = Math.min(dp[y][x], dp[y - 1][x - 1]);
                }
            }
        }
        return dp[k][s.length()];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aabbaa";
        int k = 2;
        System.out.println("s:" + s + ", k:" + k);
        System.out.println("min length:" + sol.getLengthOfOptimalCompression(s, k));
    }
}
