/* Time:O(n), Space:O(n)
 * 1. Concatenate s itself as ss, and delete the first and last character
 * 2. If ss have the substring of s, then it's a repeated string
 */

import java.util.*;

public class Solution{
    public boolean repeatedSubstringPattern(String s) {
        StringBuilder newString = new StringBuilder(s + s);
        newString.deleteCharAt(0);
        newString.deleteCharAt(newString.length() - 1);
        return (newString.indexOf(s) != -1);
    }

    public static void main(String[] args){
        Solution sol;
        String s = "abcabc";

        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("is repeated: " + sol.repeatedSubstringPattern(s));
    }
}
