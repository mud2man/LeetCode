/* Reverse twice: O(n),
 * 1. Reverse the whole string
 * 2. Reverse every word in the string
 */

import java.util.*;

public class Solution{
    public void reverseString(char[] s, int lb, int hb){
        while(lb < hb){
            char tempC = s[lb];
            s[lb++] = s[hb];
            s[hb--] = tempC;
        }
    }
    
    public void reverseWords(char[] s) {
        reverseString(s, 0, s.length - 1);
    
        int hb = 0;
        int lb = 0;
        while(hb < s.length){
            while(hb < s.length && s[hb] != ' '){
                hb++;
            }
            reverseString(s, lb, hb - 1);
            lb = ++hb;
        }
    }
    
    public static void main(String[] args){
        Solution sol;
        String s;
        char[] cArray;

           s = "the sky is blue";
        sol = new Solution();

        System.out.println("before rotate: " + s);

        cArray = s.toCharArray();
        sol.reverseWords(cArray);
        s = String.valueOf(cArray);

        System.out.println("after rotate: " + s);
    }
}
