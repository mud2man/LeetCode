/* Dynamic programming: Time:O(n*m), Space:O(n*m)
 * 1. dp[y][x] is defined as if p.substring(0, y) is matched with s.substring(0, x)
 * 2. Initialize dp[0][x], and dp[y][0]
 * 3. If charP == '.' or charP == charS, dp[y][x] = dp[y - 1][x - 1]
 * 4. If charP == '*', and (charP != charS and charP != '.'), dp[y][x] = dp[y - 2][x] 
 * 5. If charP == '*', and (charP == charS or charP != '.'), dp[y][x] = dp[y][x - 1](map ore or more) | dp[y - 2][x](map zero)
 * 6. Otherwise, dp[y][x] = false
 * 
 * ex: s = "aab", p = "c*a*b"
 *
 *     dp[][]:   X, a, a, b
 *             X T, F, F, F
 *             c F, F, F, F
 *             * T, F, F, F
 *             a F, T, F, F
 *             * T, T, T, F
 *             b F, F, F, T
 */

import java.util.*;

public class Solution{
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for(int x = 1; x < dp[0].length; ++x){
            dp[0][x] = false;
        }
        
        for(int y = 1; y < dp.length; ++y){
            dp[y][0] = (p.charAt(y - 1) != '*')? false: dp[y - 2][0];
        }
        
        for(int y = 1; y < dp.length; ++y){
            for(int x = 1; x < dp[0].length; ++x){
                char charS = s.charAt(x - 1);
                char charP = p.charAt(y - 1);
                if(charP == '*'){
                    char prevCharP = p.charAt(y - 2);
                    if(prevCharP != charS && prevCharP != '.'){
                        dp[y][x] = dp[y - 2][x];
                    }else{
                        //use one or more previous p | remove previous p
                        dp[y][x] = dp[y][x - 1] | dp[y - 2][x]; 
                    }
                }else if(charP == '.' || charP == charS){
                    dp[y][x] = dp[y - 1][x - 1];
                }else{
                    dp[y][x] = false;
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
 
    public static void main(String[] args){
        String s = "aab";
        String p = "c*a*b"; 
        Solution sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("p: " + p);
        System.out.println("is match: " + sol.isMatch(s, p));
    }
}
