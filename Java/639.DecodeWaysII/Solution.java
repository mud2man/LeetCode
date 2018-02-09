/* Dynamic programming: O(n)
 * 1. Add '9' at the beginning of string s which doen't impact the answer, and set dp[0] = 1
 * 2. Define dp[i] being the number of decode way from 0-th character to i-th character
 * 3. dp[i] = dp[i - 1] * getOneDigitCount() + dp[i - 2] * getTwoDigitCount()
 * 4. Need to consider "getOneDigitCount" and "getTwoDigitCount" respectively
 *
 * ex: s = "1*" => s = "91*", dp[0] = 1, dp[1] = 1
 * dp[2] = dp[1] * 9 + dp[0] * 9 = 18
 */         

import java.util.*;

public class Solution {
    private long getOneDigitCount(char c){
        if(c == '*'){
            return 9;
        }
        else if(c == '0'){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    private long getTwoDigitCount(char[] chars){
        if(chars[0] == '0'){
            return 0;
        }
        else if(chars[0] == '*'){
            if(chars[1] == '*'){
                return 15;
            }
            else if(('9' - chars[1]) >= 3){
                return 2;
            }
            else{
                return 1;
            }
        }
        else{
            if((chars[0] != '1') && (chars[0] != '2')){
                return 0;
            }
            else{
                if(chars[0] == '1'){
                    return (chars[1] == '*')? 9: 1;
                }
                else{
                    if(chars[1] == '*'){
                        return 6;
                    }
                    else{
                        return (('9' - chars[1]) >= 3)? 1: 0;
                    }
                }
            }
        }
    }
    
    public int numDecodings(String s) {
        s = "3" + s;
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        long constant = 1000000007;
        
        for(int i = 1; i < s.length(); ++i){
            long count = dp[i - 1] * getOneDigitCount(s.charAt(i));
            count = count % constant;
            count += (i > 1)? dp[i - 2] * getTwoDigitCount(new char[]{s.charAt(i - 1), s.charAt(i)}): 0;
            count = count % constant;
            dp[i] = count;
        }
        
        return (int)dp[s.length() - 1];
    }
 
    public static void main(String[] args){
        Solution sol;
        String s;
        
        s = "1*";
        sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("number of decode ways: " + sol.numDecodings(s));
    }
}
