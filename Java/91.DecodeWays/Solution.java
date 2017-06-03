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
        int i, len, lastDigitNum, lastTwoDigitNum;
        int[] dp;
        String lastTwoDigit, lastDigit;
        
        if((len = s.length()) == 0){
            return 0;
        }
        
        dp = new int [len + 1];
        i = 1;
        dp[0] = 1;

        //find the first characetr != "0"
        if(Integer.parseInt(s.substring(0, 1)) > 0){
            dp[1] = 1;
            i = 2;
        }
        else{
            while(i <= len && Integer.parseInt(s.substring(i - 1, i)) == 0){
                dp[i] = 0;
                ++i;
            }
        }
        
        //dynamic programming 
        for(i = i; i <= len; i++){
            lastDigit = s.substring(i - 1, i);
            lastTwoDigit = s.substring(i - 2, i);
            lastDigitNum = Integer.parseInt(lastDigit);
            lastTwoDigitNum = Integer.parseInt(lastTwoDigit);
            dp[i] = 0;
            if(lastDigitNum > 0){
                dp[i] += dp[i - 1];
            }
            
            if(lastTwoDigitNum < 27 && lastTwoDigitNum > 9){
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[len];
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
