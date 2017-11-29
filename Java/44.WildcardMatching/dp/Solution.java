/* Greedy: Time:O(n*m), Space:O(n*m)
 */

import java.util.*;

public class Solution{
    private boolean onlyStar(String p, int endIndex){
        for(int i = 0; i <= endIndex; ++i){
            if(p.charAt(i) != '*'){
                return false;
            }
        }    
        return true;
    }
    
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 || p.length() == 0){
            if(s.length() == 0 && p.length() == 0){
                return true;
            }
            else if(p.length() == 0){
                return false;
            }
            else{
                return onlyStar(p, p.length() - 1);
            }
        }
        
        int depth = p.length();
        int width = s.length();
        boolean[][] dp = new boolean[depth][width];
        for(int y = 0; y < depth; ++y){
            char patternChar = p.charAt(y);
            for(int x = 0; x < width; ++x){
                if(patternChar == '*'){
                    if(y == 0 && x == 0){
                        dp[y][x] = true;
                    }
                    else if(y == 0){
                        dp[y][x] = true;
                    }
                    else if(x == 0){
                        dp[y][x] = dp[y - 1][x];
                    }
                    else{
                        dp[y][x] = dp[y][x - 1] | dp[y - 1][x - 1] | dp[y - 1][x];
                    }
                }
                else if(patternChar == '?' || s.charAt(x) == patternChar){
                    if(y == 0 && x == 0){
                        dp[y][x] = true;
                    }
                    else if(y == 0){
                        dp[y][x] = false;
                    }
                    else if(x == 0){
                        dp[y][x] = (onlyStar(p, y - 1))? true: false;
                    }
                    else{
                        dp[y][x] = dp[y - 1][x - 1];
                    }
                }
            }
        }
        return dp[depth - 1][width - 1];
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
