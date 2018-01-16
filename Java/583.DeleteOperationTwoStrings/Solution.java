/* Dynamic Programming: Time:O(n*m), Space:O(n*m)
 * 1. Use longest common sequence algoruthm
 */         

import java.util.*;

public class Solution {
    public int minDistance(String word1, String word2) {
        int width = word1.length() + 1;
        int depth = word2.length() + 1;
        int[][] dp = new int[depth][width];
        
        for(int y = 1; y < depth; ++y){
            char c2 = word2.charAt(y - 1);
            for(int x = 1; x < width; ++x){
                char c1 = word1.charAt(x - 1);
                if(c1 == c2){
                    dp[y][x] = dp[y - 1][x - 1] + 1; 
                }
                else{
                    dp[y][x] = Math.max(dp[y - 1][x], dp[y][x - 1]); 
                }
            }
        }

        int lcsLength = dp[depth - 1][width - 1];
        int distance = (word1.length() - lcsLength) + (word2.length() - lcsLength);
        return distance;
    }

    public static void main(String[] args){
        Solution sol;
        String word1 = "sea";
        String word2 = "eat";
        sol = new Solution();

        System.out.println("word1: " + word1);
        System.out.println("word2: " + word2);
        System.out.println("minimum distance: " + sol.minDistance(word1, word2));
    }
}
