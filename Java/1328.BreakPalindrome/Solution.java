/* String: Time:O(n), Space:O(n)
 * 1. Replace the first char which is not 'a' to 'a'. Otherwise, replace the last char to 'b'
 */

import java.util.*;

public class Solution{
    public String breakPalindrome(String palindrome) {
        if(palindrome.length() == 1){
            return "";
        }
        StringBuilder ret = new StringBuilder(palindrome);
        for(int i = 0; i < palindrome.length() / 2; ++i){
            if(ret.charAt(i) != 'a'){
                ret.setCharAt(i, 'a');
                return ret.toString();
            }
        }
        ret.setCharAt(palindrome.length() - 1, 'b');
        return ret.toString();
    }
  
    public static void main(String[] args){
        String palindrome = "abccba";
        Solution sol = new Solution();
        System.out.println("palindrome:" + palindrome);
        System.out.println("answer:" + sol.breakPalindrome(palindrome));
    }
}
