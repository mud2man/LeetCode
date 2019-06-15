/* Manacher: Time:O(n), Space:O(n). LeetCode has KMP solution
 * 1. Interleave '*' in the input string s, 
 * 2. In every loop, check if "i == r", since the longer r[i] has radius reaching to 0, the shorter the palindrome  
 * 3. If so, we can generate the associated palindrome
 */

import java.util.*; // Stack

public class Solution {
    private String genPalindrome(StringBuilder sb, int center){
        StringBuilder p = (center % 2 == 1)? new StringBuilder(Character.toString(sb.charAt(center))): new StringBuilder();
        int right = center + 1;
        while(right < sb.length()){
            if(right % 2 == 1){
                p.insert(0, sb.charAt(right));
                p.append(sb.charAt(right));
            }
            right++;
        }
        return p.toString();
    }
    
    public String shortestPalindrome(String s) {
        if(s.length() == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder("*");
        for(char c: s.toCharArray()){
            sb.append(c);
            sb.append('*');
        }
        
        //Manacher
        int[] radius = new int[sb.length()];
        radius[1] = 1;
        int center = 1;
        int right = 1;
        int keyIdx = 1;
        for(int i = 2; i < sb.length(); ++i){
            int r = (right > i)? Math.min(right - i, radius[2 * center - i]): 0;
            while(i + r + 1 < sb.length() && i - r - 1 >= 0 && sb.charAt(i + r + 1) == sb.charAt(i - r - 1)){
                ++r;
            }
            radius[i] = r;
            
            if(i == r){
                keyIdx = i;
            }
            if(i + r > right){
                right = i + r;
                center = i;
            }
        }
        return genPalindrome(sb, keyIdx);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aacecaaa"; 
        System.out.println("s: " + s);
        System.out.println("palindrome: " + sol.shortestPalindrome(s));
    }
}
