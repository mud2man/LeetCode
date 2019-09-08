/* Recursive: O(n)
 * 1. Have a global variable "index" for string traverse
 * 2. In decode, if first character is not digit, append character until we hit ']' or digit
 * 3. Otherwise, get the repeat time, and get the payload by calling "decode", then append payload with "times" 
 */

import java.util.*;

public class Solution{
    private String decode(int[] index, String s){
        StringBuilder sb = new StringBuilder("");
        while(index[0] < s.length() && s.charAt(index[0]) != ']'){
            if(!Character.isDigit(s.charAt(index[0]))){
                while(index[0] < s.length() && !Character.isDigit(s.charAt(index[0])) && s.charAt(index[0]) != ']'){
                    sb.append(s.charAt(index[0]++));
                }
            }else{
                int leftIndex = s.indexOf('[', index[0]);
                int stratIndex = index[0];
                index[0] = leftIndex + 1;
                int times = Integer.parseInt(s.substring(stratIndex, leftIndex));
                String payload = decode(index, s);
                for(int i = 0; i < times; ++i){
                    sb.append(payload);
                }
            }
        }
        index[0]++;
        return sb.toString();
    }
    
    public String decodeString(String s) {
        int[] index = {0};
        return decode(index, s);
    }
 
    public static void main(String[] args){
        String s = "3[a]2[bc]";
        Solution sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("ans: " + sol.decodeString(s));
    }
}
