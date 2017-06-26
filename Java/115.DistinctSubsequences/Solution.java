/* Dynamic Programming: O(n^2)
 * 1. dp[y][x] = # of distinct subsequences of S.subtring(0, x), which equals to T.substring(0, y)
 * 2. If T.charAt(y) == S.charAt(x), dp[y][x] = 
 *    #combinations(without S.charAt(x))=dp[y][x - 1]  + #combinations(with S.charAt(x))=dp[y - 1][x - 1]
 * 3. If T.charAt(y) != S.charAt(x), dp[y][x] = 
 *    #combinations(without S.charAt(x))=dp[y][x - 1]
 *
 * ex: S = "rabbbit", T = "rabit"
 * 
 *     r  a  b  b  b  i  t
 *   -----------------------
 * r | 1  1  1  1  1  1  1 |
 * a | 0  1  1  1  1  1  1 |
 * b | 0  0  1  2  3  3  3 |
 * i | 0  0  0  0  0  3  3 |
 * t | 0  0  0  0  0  0  3 |
 *   -----------------------
 */

import java.util.*;


public class Solution{
    public void dump(int[][] dp){
        for(int[] row: dp){
            System.out.println(Arrays.toString(row));
        }
    }
    
    public int numDistinct(String s, String t) {
        int y, x, depth, width;
        int[][] dp;
        char c;
        
        depth = t.length();
        width = s.length();
        dp = new int[depth][width];
        
        if(width < depth || width == 0 || depth == 0){
            return 0;
        }
        
        //initialize the first row
        for(y = 0, x = 0, c = t.charAt(0); x < width; ++x){
            if(c == s.charAt(x)){
                if(x == 0){
                    dp[y][x] = 1;
                }
                else{
                    dp[y][x] = dp[y][x - 1] + 1;
                }
            }
            else{
                dp[y][x] = (x > 0) ? dp[y][x - 1]: 0;
            }
        }
        //If the same, dp[y][x]=#combinations(without s.charAt(x))=dp[y][x - 1] + #combinations(with s.charAt(x))=dp[y - 1][x - 1]
        //Otherwise, dp[y][x]=#combinations(without s.charAt(x))=dp[y][x - 1] 
        for(y = 1; y < depth; ++y){
            for(x = 1; x < width; ++x){
                if(t.charAt(y) == s.charAt(x)){
                    dp[y][x] = dp[y - 1][x - 1] + dp[y][x - 1];
                }
                else{
                    dp[y][x] = dp[y][x - 1];
                }
            }
        }
        dump(dp);
        return dp[depth - 1][width - 1];
    } 
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "rabbbit";
        String t = "rabit";
        int number;

        number = sol.numDistinct(s, t);
        
        System.out.println("s: " + s);
        System.out.println("t: " + t);
        System.out.println("distinct subsequences#: " + number);
    }
}
