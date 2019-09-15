/* Recursive: Time:O(n^2), Space:O(n^2)
 * 1. Have a global "index" to store the current position
 * 2. "reverse" can move the index to the next starting position of the paraent "reverse"
 * 3. Encapsulate ( and ) to the original string
 * 4. Reverse the answer back because of the Encapsulation
 */

import java.util.*;

public class Solution{
    private String reverse(String s, int[] idx){
        StringBuilder sb = new StringBuilder("");
        while(idx[0] < s.length() && s.charAt(idx[0]) != ')'){
            char c = s.charAt(idx[0]++);
            if(c == '('){
                sb.append(reverse(s, idx));
            }else{
                sb.append(c);
            }
        }
        idx[0]++;
        return sb.reverse().toString();
    }
    
    public String reverseParentheses(String s) {
        int[] idx = {0};
        idx[0]++;
        StringBuilder tmp = new StringBuilder(reverse("(" + s + ")", idx));
        return tmp.reverse().toString();
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "(u(love)i)";
        System.out.println("s: " + s);
        System.out.println("reversed s: " + sol.reverseParentheses(s));
    }
}
