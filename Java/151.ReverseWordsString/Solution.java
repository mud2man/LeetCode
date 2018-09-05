/* Time:O(n), Space:O(n)
 * 1. Reverse the whole string
 * 2. Reverse every word in the string
 */

import java.util.*;

public class Solution{
    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        String reverse = "";
        int i = 0;
        while(i < sb.length()){
            StringBuilder word = new StringBuilder("");
            while(i < sb.length() && sb.charAt(i) != ' '){
                word.append(sb.charAt(i++));
            }
            word.reverse();
            reverse += word.toString();
            if(i != sb.length()){
                reverse += " ";
            }
            
            while(i < sb.length() && sb.charAt(i) == ' '){
                ++i;
            }
        }
        return reverse;
    }
    
    public static void main(String[] args){
        Solution sol;
        String s;

        s = "the sky is blue";
        sol = new Solution();

        System.out.println("before reverse: " + s);
        System.out.println("after reverse: " + sol.reverseWords(s));
    }
}
