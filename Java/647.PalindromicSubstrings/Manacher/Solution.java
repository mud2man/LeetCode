/* Manacher's algorithm: Time:O(n), Space:O(n)
 * 1. Preprocess string s.t. s = "*a#b#b#", when s = "abc"
 * 2. Keep record the palindrome's most right bound "right", and its center "c"
 * 3. Traverse s from left, and update radius r[i]
 * 4. If i is not over right, radius = min(right - i, r[2 * c - i]). Otherwise radius = 0. Then expend radius as long as possible
 * 5. Get the length of the palindrome, then accumulate count with (len + 2) / 2
 * 6. If i + radis > right, update "right" and center "c"
 *
 * ex: *a#b#b#
 *  index: 0  1  2  3  4  5  6 
 *  chars: *  a  #  b  #  b  #
 *  r[i]:  0  1  0  1  2  1  0
 *  right: X  1  2  4  6  6  6
 *  c:     X  1  2  3  4  4  4  
 */          

import java.util.*;

public class Solution {
    public int countSubstrings(String s) {
        StringBuilder sb = new StringBuilder("");
        sb.append('#');
        for(char c : s.toCharArray()){
            sb.append(c);
            sb.append('#');
        }
        
        int c = 1;
        int rb = 1;
        int[] r = new int[sb.length()];
        int count = 1;
        for(int i = 2; i < sb.length(); ++i){
            int radius = (i <= rb)? Math.min(rb - i, r[2 * c - i]): 0;
            while(i - radius - 1 >= 0 && i + radius + 1 < sb.length() && sb.charAt(i - radius - 1) == sb.charAt(i + radius + 1)){
                radius++;
            }
            r[i] = radius;
            
            if(i + radius > rb){
                c = i;
                rb = i + radius;
            }
            
            //retrieve string with two ends not equal to '#'
            radius = (sb.charAt(i - radius) == '#')? radius - 1: radius;
            //len("a#a#a") = (2 + 2) / 2 = 2, len("a#a#a#a") = (3 + 2) / 2 = 2
            int len = (radius + 2) / 2;
            count += len;
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aaa";
        System.out.println("count: " + sol.countSubstrings(s));
    }
}
