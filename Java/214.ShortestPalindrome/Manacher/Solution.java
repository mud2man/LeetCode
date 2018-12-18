/* Manacher: Time:O(n), Space:O(n). LeetCode has KMP solution
 * 1. Interleave '#' in the input string s, and reverse it
 * 2. In every loop, check if "right" reaches to the end, since the sooner reach to end, the shorter the palindrome  
 * 3. If so, we can generate the according palindrome
 */

import java.util.*; // Stack

public class Solution {
    private String genPalindrome(StringBuilder sb, int center){
        String mid = (center % 2 == 0)? "": sb.substring(center, center + 1);
        int end = (center % 2 == 0)? center - 1: center - 2;
        StringBuilder right = new StringBuilder("");
        for(int i = end; i >= 0; i -= 2){
            right.append(sb.charAt(i));
        }
        String palindrome = mid + right.toString();
        right.reverse();
        palindrome = right + palindrome;
        return palindrome;
    }
    
    public String shortestPalindrome(String s) {
        if(s == null || s.equals("")){
            return "";
        }
        
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < s.length(); ++i){
            sb.append(s.charAt(i));
            sb.append('#');
        }
        sb.reverse();
        
        int right = 1;
        int center = 1;
        int[] radius = new int[sb.length()];
        for(int i = 1; i < sb.length(); ++i){
            int r = (right > i)? Math.min(radius[2 * center - i], right - i): 0;
            while(i + r + 1 < sb.length() && i - r - 1 >= 0 && sb.charAt(i + r + 1) == sb.charAt(i - r - 1)){
                ++r;
            }
            radius[i] = r;
                
            if(i + r > right){
                right = i + r;
                center = i;
            }
            
            if(right == sb.length() - 1){
                return genPalindrome(sb, center);
            }
        }
        return "";
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aacecaaa"; 
        System.out.println("s: " + s);
        System.out.println("palindrome: " + sol.shortestPalindrome(s));
    }
}
