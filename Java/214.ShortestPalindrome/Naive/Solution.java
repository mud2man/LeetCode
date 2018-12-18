/* Two pointers: Time:O(n^2), Space:(n) 
 * 1. Interleave '#' in the input string s
 * 2. Traverse from middle to left, and check if left part can be mirrowed
 * 3. If so, generate the corresponding palindrome
 */

import java.util.*; // Stack

public class Solution {
    private boolean isPalindrome(StringBuilder sb, int idx){
        int left = idx - 1;
        int right = idx + 1;
        while(left >= 0 && right < sb.length()){
            if(sb.charAt(left--) != sb.charAt(right++)){
                return false;
            }
        }
        return true;
    }
    
    private String genPalindrome(StringBuilder sb, int idx){
        String mid = (idx % 2 == 1)? "": sb.substring(idx, idx + 1);
        int start = (idx % 2 == 1)? idx + 1: idx + 2;
        StringBuilder right = new StringBuilder("");
        for(int i = start; i < sb.length(); i += 2){
            right.append(sb.charAt(i));
        }
        String palindrome = mid + right.toString();
        right.reverse();
        palindrome = right + palindrome;
        return palindrome;
    }
    
    public String shortestPalindrome(String s) {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < s.length(); ++i){
            sb.append(s.charAt(i));
            sb.append('#');
        }
        
        for(int i = sb.length() / 2 - 1; i >= 0; --i){
            if(isPalindrome(sb, i)){
                return genPalindrome(sb, i);
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
