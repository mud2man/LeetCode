/* Manacher's Algorithm: Time:O(n), Space:O(n)
 * 1. Have a right bound of palindrome, and then traverse t
 * 2. If i > rightBound, then set r[i] = 0. Otherwise, set r[i] = min(rightBound - i, r[2 * c - i])
 * 3. Retrieve palindrome given c and its radius "max"
 */         

import java.util.*;

public class Solution {
    public String longestPalindrome(String s) {
        StringBuilder t = new StringBuilder("");
        t.append("*");
        for(int i = 0; i < s.length(); ++i){
            t.append(s.charAt(i));
            t.append('#');
        }
        
        int[] r = new int[t.length()];
        int c = 1;
        int right = 1;
        int maxRadius = 0;
        int maxC = 1;
        for(int i = 2; i < t.length(); ++i){
            r[i] = (right > i)? Math.min(right - i, r[2 * c - i]) : 0;
            while(i - r[i] - 1 >= 0 && i + r[i] + 1 < t.length() && t.charAt(i - r[i] - 1) == t.charAt(i + r[i] + 1)){
                r[i]++;
            }
            
            if(i + r[i] > right){
                right = i + r[i];
                c = i;
            }
            
            if(maxRadius < r[i]){
                maxC = i;
                maxRadius = r[i];
            }
        }
        
        int start = ((maxC - maxRadius) == 1)? 1: maxC - maxRadius + 1;
        int end = maxC + maxRadius;
        StringBuilder p = new StringBuilder("");
        for(int i = start; i <= end && i < t.length(); i += 2){
            p.append(t.charAt(i));
        }
        return p.toString();
    }
  
    public static void main(String[] args){
        Solution sol;
        String s = "babad";
        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("longest palindrome : " + sol.longestPalindrome(s));
    }
}
