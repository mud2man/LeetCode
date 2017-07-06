/* Dynamic programming: O(n)
 * 1. dp[i] = the number of decode way from 0-th character to i-th character
 * 2. if s.charAt(i) > 0, dp[i] += dp[i - 1]
 * 3. if s.charAt(i - 1) | s.charAt(i - 1) < 27 && > 9, dp[i] += dp[i - 2]
 *
 * ex: n = 1234, dp[0] = 1, dp[1] = 1
 * dp[2] = dp[1] + dp[0] = 2
 * dp[3] = dp[2] + dp[1] = 3
 * dp[4] = dp[3] = 3
 */         

import java.util.*;

public class Solution {
    public int numDecodings(String s) {
        int[] dp = new int [s.length() + 1];
        int tens, digits;
        
        if(s.length() == 0){
            return 0;
        }
        
        digits = Integer.parseInt(s.substring(0, 1));
        if(digits == 0){
            return 0;
        }

        dp[0] = 1;
        dp[1] = 1;
        for(int i = 1; i < s.length(); ++i){
            tens = Integer.parseInt(s.substring(i - 1, i + 1));
            digits = Integer.parseInt(s.substring(i, i + 1));
            if(tens >= 1 && tens <= 26 && s.charAt(i - 1) != '0'){
                dp[i + 1] += dp[i - 1];
            }
            
            if(digits > 0){
                dp[i + 1] += dp[i];
            }
        }
        return dp[s.length()];
    }
 
    public static void main(String[] args){
        Solution sol;
        String s;
        
        s = "1234";
        sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("number of Decode Ways: " + sol.numDecodings(s));
    }
}
