/* Dynamic programming: O(n*m)
 * 1. Delete the duplicated * in pattern word p, e.g. **a**b => *a*b
 * 2. Let dp[y][x] = the result of match between s.substring(0, x + 1), and p.substring(0, y + 1)
 * 3. If p.charAt(y) == *, dp[y][x] = true if x >= previousFirstSuccessIndex
 * 3. Handle all the corner cases
 *
 * ex: s = "bbaab", p = "*a*b"
 * dp:    b b a a b
 *      * 1 1 1 1 1
 *      a 0 0 1 1 0
 *      * 0 0 1 1 1
 *      b 0 0 0 0 1
 */

import java.util.*;

public class Solution{
    private boolean characterMatch(int y, int x, boolean[][] dp, String p){
        if(y == 1 && x == 0 && p.charAt(0) == '*'){
            if(dp[0][0] == true){
                dp[1][0] = true;
                return true;
            }
        }
        else{
            if(y == 0 && x == 0){
                dp[y][x] = true;
                return true;
            }
            else{
                if(y > 0 && x > 0 && dp[y - 1][x - 1] == true){
                    dp[y][x] = true;
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isMatch(String s, String p) {
        StringBuilder reducedP = new StringBuilder("");
        for(int i = 0; i < p.length(); ++i){
            if(p.charAt(i) == '*'){
                while(i < p.length() && p.charAt(i) == '*'){++i;}
                reducedP.append('*');
                --i;
            }
            else{
                reducedP.append(p.charAt(i));
            }
        }
        p = reducedP.toString();
                 
        if(s.length() == 0){
            if(p.length() == 0 || (p.length() == 1 && p.charAt(0) == '*')){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(p.length() == 0){
                return false;
            }
        }
        
        int depth = p.length();
        int width = s.length();
        boolean[][] dp = new boolean[depth][width];
        int previousFirstSuccessIndex = 0;
        for(int y = 0; y < depth; ++y){
            char pChar = p.charAt(y);
            int newPreviousFirstSuccessIndex = width;
            for(int x = 0; x < width; ++x){
                switch (pChar){
                    case '*':
                        if(x >= previousFirstSuccessIndex){
                            dp[y][x] = true; 
                        }
                        break;
                        
                    case '?':
                        if(characterMatch(y, x, dp, p)){
                            newPreviousFirstSuccessIndex = Math.min(newPreviousFirstSuccessIndex, x);
                        }
                        break;
                        
                    default:
                        if(pChar != s.charAt(x)){
                            dp[y][x] = false;    
                        }
                        else{
                            if(characterMatch(y, x, dp, p)){
                                newPreviousFirstSuccessIndex = Math.min(newPreviousFirstSuccessIndex, x);
                            }
                        }
                        break;
                }
            }
            previousFirstSuccessIndex = newPreviousFirstSuccessIndex;
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
