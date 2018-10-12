/* Dynamic programming: O(n)
 * 1. dp[i] = the number of decode way from 0-th character to i-th character
 * 2. if s.charAt(i) != '0', dp[i] += dp[i - 1]
 * 3. if s.charAt(i - 1) != '0' && s.substring(i - 1, i + 1) >= 26, dp[i] += dp[i - 2]
 *
 * ex: n = 226, dp[0] = 1
 * dp[1] = dp[0] + 1 = 2
 * dp[2] = dp[1] + dp[0] = 3
 */         

import java.util.*;

public class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0'){
            return 0;
        }
        
        int[] dp = new int [s.length()];
        dp[0] = 1;
        for(int i = 1; i < s.length(); ++i){
            dp[i] = 0;
            //single digit
            if(s.charAt(i) != '0'){
                dp[i] += dp[i - 1];
            }
            
            //double digit
            int num = Integer.parseInt(s.substring(i - 1, i + 1));
            if(num <= 26 && s.charAt(i - 1) != '0'){
                dp[i] += (i - 2 >= 0)? dp[i - 2]: 1;
            }
        }
        return dp[s.length() - 1];
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
