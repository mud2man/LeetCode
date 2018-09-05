/* Time:O(n)
 * 1. Check if boolean "neg" is true
 * 2. Use "num / 10 != prev", where num = 10 * prev + tail, to check if overflow
 */

import java.util.*;

public class Solution{
    public int myAtoi(String str) {
        str = str.trim();
        if(str.length() == 0){
            return 0;
        }
        
        int idx = 0;
        boolean neg = false;
        if(str.charAt(0) == '-'){
            neg = true;
            idx++;
        }
        else if(str.charAt(0) == '+'){
            idx++;
        }
        else if(!Character.isDigit(str.charAt(idx))){
            return 0;
        }
        
        int num = 0;
        while(idx < str.length() && Character.isDigit(str.charAt(idx))){
            int prev = num;
            int tail = str.charAt(idx++) - '0';
            tail = neg? -tail: tail;
            num = 10 * num + tail;

            if(num / 10 != prev){
                return neg? Integer.MIN_VALUE: Integer.MAX_VALUE;
            }
        }
        return num;
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "   -42";
        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("integer: " + sol.myAtoi(s));
    }
}
