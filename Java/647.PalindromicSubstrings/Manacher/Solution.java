/* Manacher's algorithm: Time:O(n), Space:O(n)
 * 1. Preprocess string s.t. s = "*a#b#b#", when s = "abc"
 * 2. Keep record the palindrome's most right bound "right", and its center "c"
 * 3. Traverse s from left, and update radius r[i]
 * 4. If i is not over right, radius = min(right - i, r[2 * c - i]). Otherwise radius = 0. Then expend radius as long as possible
 * 5. Get the length of the palindrome, then accumulate count with (len + 1) / 2
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
        sb.append('*');
        for(char c: s.toCharArray()){
            sb.append(c);
            sb.append('#');
        }
        char[] chars = sb.toString().toCharArray();
        int right = 1;
        int c = 1;
        int[] r = new int[chars.length];
        r[1] = 1;
        int count = 1;
        for(int i = 2; i < chars.length; ++i){
            int radius = (right > i)? Math.min(right - i, r[2 * c - i]): 0;
            while(i + radius + 1 < chars.length && i - radius - 1 >= 0 && chars[i + radius + 1] == chars[i - radius - 1]){
                radius++;
            }
            r[i] = radius;
            
            int len = (i - radius - 1 == 0)?  radius + 1: radius;
            count += (len + 1) / 2;
            
            if(i + radius > right){
                right = i + radius;
                c = i;
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aaa";
        System.out.println("count: " + sol.countSubstrings(s));
    }
}
