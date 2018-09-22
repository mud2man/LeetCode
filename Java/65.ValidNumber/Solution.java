/* Time:O(n), Space:O(1)
 * 1. Have indicator sawE, sawPoint, sawBase, sawExp, sawDigit
 * 2. Traverse char in s after trim, and handle cases 0~9, e, +/-, .
 * 3. If sawE, then requies sawBase&sawExp, otherwise tre 
 */

import java.util.*;

public class Solution{
    public boolean isNumber(String s) {
        s = s.trim();
        if(s.length() == 0){
            return false;
        }
        boolean sawE = false;
        boolean sawPoint = false;
        boolean sawBase = false;
        boolean sawExp = false;
        boolean sawDigit = false;
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                if(!sawE){
                    sawBase = true;
                }
                else{
                    sawExp = true;
                }
                sawDigit = true;
            }
            else if(c == 'e'){
                if(sawE == true){
                    return false;
                }
                sawE = true;
                sawDigit = false;
            }
            else if(c == '+' || c == '-'){
                if(i != 0 && s.charAt(i - 1) != 'e'){
                    return false;
                }
                sawDigit = false;
            }
            else if(c == '.'){
                if(sawPoint == true || sawE == true){
                    return false;
                }
                if(!sawDigit && (i + 1 == s.length() || !Character.isDigit(s.charAt(i + 1)))){
                    return false;
                }
                sawPoint = true;
                sawDigit = false;
            }
            else{
                return false;
            }
        }
        return sawE? (sawBase&sawExp): true;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "2e10";
        System.out.println("S:" + S);
        System.out.println("is number ? " + sol.isNumber(S));
    }
}
