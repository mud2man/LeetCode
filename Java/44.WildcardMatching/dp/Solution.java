/* Dynmaic progamming: Time:O(n*m), Space:O(n*m)
 * 1. dp[y][x] = if p.substring(0, y + 1) can match s.substring(0, x + 1)
 * 2. In general case, if p.charAt(y) == '*',  dp[y][x] = dp[y - 1][x] | dp[y - 1][x - 1] | dp[y][x - 1]
 * 3. If p.charAt(y) == '?' ||  p.charAt(y) = s.charAt(x), dp[y][x] = dp[y - 1][x - 1]
 * 4. Otherwise, dp[y][x] = false
 * 5. We need to seperate cases of starting point, first row, first column, and others
 * 6. We need to a boolean variable seeCharInFirstColumn to handle the first column 
 */

import java.util.*;

public class Solution{
    public boolean isMatch(String s, String p) {
        s = s + "?";
        p = p + "?";
        boolean[][] dp = new boolean[p.length()][s.length()];
        boolean seeCharInFirstColumn = false;
        for(int y = 0; y < p.length(); ++y){
            char pc = p.charAt(y);
            for(int x = 0; x < s.length(); ++x){
                char sc = s.charAt(x);
                if(y == 0 && x == 0){
                    dp[y][x] = (pc == '*' || pc == '?' || pc == sc)? true: false;
                    seeCharInFirstColumn = (pc != '*' || seeCharInFirstColumn == true)? true: false;
                }
                else if(y == 0){
                    dp[y][x] = (pc == '*')? true: false;
                }
                else if(x == 0){
                    if(pc == '*'){
                        dp[y][x] = dp[y - 1][x];
                    }
                    else if(pc == '?' || pc == sc){
                        dp[y][x] = (!seeCharInFirstColumn)? dp[y - 1][x]: false;
                    }
                    else{
                        dp[y][x] = false;
                    }
                    seeCharInFirstColumn = (pc != '*' || seeCharInFirstColumn == true)? true: false;
                }
                else{
                    if(pc == '*'){
                        dp[y][x] = dp[y - 1][x] | dp[y - 1][x - 1] | dp[y][x - 1]; 
                    }
                    else if(pc == '?' || pc == sc){
                        dp[y][x] = dp[y - 1][x - 1]; 
                    }
                    else{
                        dp[y][x] = false; 
                    }
                }
            }
        }
        return dp[p.length() - 1][s.length() - 1];
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "bbaab";
        String p = "**a**b";
        
        System.out.println("s: " + s);
        System.out.println("p: " + p);
        System.out.println("isMatch:" + sol.isMatch(s, p));
    }
}
