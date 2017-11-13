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
        int depth = word1.length();
        int width = word2.length();
        int[][] dp = new int[depth][width];
        
        if(depth == 0 || width == 0){
            return Math.max(depth, width);
        }
        
        for(int y = 0; y < depth; ++y){
            char word1Char = word1.charAt(y);
            for(int x = 0; x < width; ++x){
                char word2Char = word2.charAt(x);
                if(y == 0 && x == 0){
                    dp[y][x] = (word1Char == word2Char)? 0: 1;
                }
                else if(y == 0){
                    if(word1Char == word2Char){
                        dp[y][x] = (dp[y][x - 1] == x)? dp[y][x - 1]: dp[y][x - 1] + 1;
                    }
                    else{
                        dp[y][x] = dp[y][x - 1] + 1;
                    }
                }
                else if(x == 0){
                    if(word1Char == word2Char){
                        dp[y][x] = (dp[y - 1][x] == y)? dp[y - 1][x]: dp[y - 1][x] + 1;
                    }
                    else{
                        dp[y][x] = dp[y - 1][x] + 1;
                    }
                }
                else{
                    int minSteps = Math.min(dp[y - 1][x] + 1, dp[y][x - 1] + 1);
                    if(word1Char == word2Char){
                        minSteps = Math.min(minSteps, dp[y - 1][x - 1]);
                    }
                    else{
                        minSteps = Math.min(minSteps, dp[y - 1][x - 1] + 1);
                    }
                    dp[y][x] = minSteps;
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
