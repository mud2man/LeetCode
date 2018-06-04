/* Time:O(n), Space:O(n)
 * 1. Strip the leading zeros and tail zeros, then split the string to base part and exponent part
 * 2. In base part, it can come along with dot
 * 3. In exponent, it can only be integer
 */

import java.util.*;

public class Solution{
    String strip(String s){
        int start = 0;
        int end = s.length() - 1;
        while(start < s.length() && s.charAt(start) == ' '){start++;}
        while(end > 0 && s.charAt(end) == ' '){end--;}
        return s.substring(start, end + 1);
    }
        
    private boolean isBase(String s){
        if(s.length() < 1){
            return false;
        }
        int start = (s.charAt(0) == '+' || s.charAt(0) == '-')? 1: 0;
        int dotCount = 0;
        int length = 0;
        for(int i = start; i < s.length(); ++i){
            if(s.charAt(i) == '.'){
                dotCount++;
            }
            else if(!Character.isDigit(s.charAt(i))){
                return false;
            }
            else{
                length++;
            }
        }
        
        if(dotCount > 1 || (dotCount == 1 && length == 0)){
            return false;
        }
        else if(dotCount == 0){
            return (s.substring(start).length() > 0);
        }
        else{
            return true;
        }
    }
    
    private boolean isExponent(String s){
        if(s.length() < 1){
            return false;
        }
        int start = (s.charAt(0) == '+' || s.charAt(0) == '-')? 1: 0;
        for(int i = start; i < s.length(); ++i){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return (s.substring(start).length() > 0);
    }
    
    public boolean isNumber(String s) {
        s = strip(s);
        if(s.indexOf(' ') != -1){
            return false;
        }
        
        String[] baseAndExponent = s.split("e");
        if(baseAndExponent.length > 2 || s.indexOf('e') != s.lastIndexOf('e')){
            return false;
        }
        else if(baseAndExponent.length == 2){
            if(!isBase(baseAndExponent[0]) || !isExponent(baseAndExponent[1])){
                return false;
            }
        }
        else{
            if(s.indexOf("e") != -1 || !isBase(baseAndExponent[0])){
                return false;
            }
        }
        return true;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "2e10";
        System.out.println("S:" + S);
        System.out.println("is number ? " + sol.isNumber(S));
    }
}
