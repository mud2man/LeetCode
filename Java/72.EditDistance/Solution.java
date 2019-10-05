/* Dynamic programming: O(n*m)
 * 1. Transform word1 to xword1, and eord2 to xword2
 * 1. Have a 2D array, dp[y][x] = the minimum steps of tarnsfer word1.substring(y + 1) to word2.substring(x + 1);
 * 
 * ex: word1=ros, word2=horse => word1=xros, word2=xhorse
 * dp[][] = [[0, 1, 2, 3, 4, 5],
 *           [1, 1, 2, 2, 3, 4],
 *           [2, 2, 1, 2, 3, 4],
 *           [3, 3, 2, 3, 2, 3]]
 */

import java.util.*;

public class Solution{
    public int minDistance(String word1, String word2) {
        int depth = word1.length() + 1;
        int width = word2.length() + 1;
        int[][] dp = new int[depth][width];
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(x == 0 && y == 0){
                    dp[y][x] = 0;
                }else if(x == 0){
                    dp[y][x] = y;
                }else if(y == 0){
                    dp[y][x] = x;
                }else{
                    char word1Char = word1.charAt(y - 1);
                    char word2Char = word2.charAt(x - 1);
                    dp[y][x] = (word1Char == word2Char)? dp[y - 1][x - 1]: dp[y - 1][x - 1] + 1; //replce
                    dp[y][x] = Math.min(dp[y][x], dp[y - 1][x] + 1); // delete word1Char
                    dp[y][x] = Math.min(dp[y][x], dp[y][x - 1] + 1); // add word2Char
                }
            }
        }
        return dp[depth - 1][width - 1];
    }
  
    public static void main(String[] args){
        String word1 = "ros";
        String word2 = "horse";
        Solution sol = new Solution();
        System.out.println("word1: " + word1);
        System.out.println("word2: " + word2);
        System.out.println("minimum distance: " + sol.minDistance(word1, word2));
    }
}
