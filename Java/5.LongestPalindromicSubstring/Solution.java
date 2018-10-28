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
            t.append("#");
        }
        
        int c = 1;
        int rightBound = 1;
        int[] r = new int[t.length()];
        int max = 0;
        for(int i = 2; i < t.length(); ++i){
            r[i] = (i > rightBound)? 0 : Math.min(rightBound - i, r[2 * c - i]);
            while((i - r[i] - 1) >= 0 && (i + r[i] + 1) < t.length() && t.charAt(i - r[i] - 1) == t.charAt(i + r[i] + 1)){
                r[i]++;
            }
            
            if(r[i] > max){
                rightBound = i + r[i];
                c = i;
                max = r[i];
            }
        }
        
        StringBuilder palindrome = new StringBuilder("");
        int start = ((c - max) == 1)? 1: c - max + 1;
        int end = ((c - max) == 1)? c + max + 1: c + max;
        for(int i = start; i < end && i < t.length(); i += 2){
            palindrome.append(t.charAt(i));
        }
        return palindrome.toString();
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "babad";
        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("longest palindrome : " + sol.longestPalindrome(s));
    }
}
