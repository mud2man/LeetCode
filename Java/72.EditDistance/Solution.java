/* Dynamic programming: O(n*m)
 * 1. Have a 2D array, dp[y][x] = the minimum steps of tarnsfer word1.substring(y) to word2.substring(x);
 * 2. If y==0 / x==0
 *   2.1. If word1.charAt(y)!=word2.charAt(x), then dp[y][x]=dp[y][x - 1] + 1 / dp[y - 1][x] + 1;
 *   2.2  If word1.charAt(y)==word2.charAt(x)
 *       2.2.1 If dp[y][x - 1]==x / dp[y - 1][x]==y (the current character never overlap), then dp[y][x]=dp[y][x - 1] / dp[y - 1][x]
 *       2.2.2 Else, dp[y][x] = dp[y][x - 1] + 1 / dp[y][x] = dp[y - 1][x] + 1
 * 3. Otherwise
 *   3.1 If word1Char==word2Char, dp[y][x]=min(dp[y - 1][x] + 1, dp[y][x - 1] + 1, dp[y - 1][x - 1])
 *   3.2 Else, dp[y][x]=min(dp[y - 1][x] + 1, dp[y][x - 1] + 1, dp[y - 1][x - 1] + 1)
 * 
 * ex: word1=ab, word2=bac
 * dp[][] = [[1, 1, 2],
 *           [1, 2, 2]]
 */

import java.util.*;

public class Solution{
        public int minDistance(String word1, String word2) {
        if(word1.length() == 0 || word2.length() == 0){
            return Math.max(word1.length(), word2.length());
        }
        
        int depth = word1.length();
        int width = word2.length();
        int[][] dp = new int[depth][width];
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(y == 0 && x == 0){
                    if(word1.charAt(y) == word2.charAt(x)){
                        dp[y][x] = 0;
                    }
                    else{
                        dp[y][x] = 1;
                    }
                }
                else if(y == 0){
                    if(word1.charAt(y) == word2.charAt(x)){
                        dp[y][x] = x;
                    }
                    else{
                        dp[y][x] = dp[y][x - 1] + 1;
                    }
                }
                else if(x == 0){
                    if(word1.charAt(y) == word2.charAt(x)){
                        dp[y][x] = y;
                    }
                    else{
                        dp[y][x] = dp[y - 1][x] + 1;
                    }
                }
                else{
                    if(word1.charAt(y) == word2.charAt(x)){
                        dp[y][x] = dp[y - 1][x - 1];
                    }
                    else{
                        dp[y][x] = Math.min(dp[y - 1][x] + 1, dp[y][x - 1] + 1);
                        dp[y][x] = Math.min(dp[y][x], dp[y - 1][x - 1] + 1);
                    }
                }
            }
        }
        return dp[depth - 1][width - 1];
    }
  
    public static void main(String[] args){
        Solution sol;
        String word1 = "ab";
        String word2 = "bac";
        
        sol = new Solution();
        
        System.out.println("word1: " + word1);
        System.out.println("word2: " + word2);
        System.out.println("minimum distance: " + sol.minDistance(word1, word2));
    }
}
