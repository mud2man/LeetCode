/* Time:O(n^2), Space:O(n^2)
 */         

import java.util.*;

public class Solution {
    public String longestPalindrome(String s) {
        int idx, maxIdx, maxLen;
        char c;
        List<List<Integer>> dp;
        List<Integer> prevPalindromeLens, currPalindromeLens;
        
        dp = new ArrayList<List<Integer>>();
        dp.add(new ArrayList<Integer>());
        dp.get(0).add(1);
        dp.get(0).add(0);
        maxIdx = 0;
        maxLen = 1;
        
        for(idx = 1; idx < s.length(); idx++){
            prevPalindromeLens = dp.get(idx - 1);
            currPalindromeLens = new ArrayList<Integer>();
            currPalindromeLens.add(1);
            currPalindromeLens.add(0);
            for(int len: prevPalindromeLens){
                if(idx - len - 1 >= 0 && s.charAt(idx - len - 1) == s.charAt(idx)){
                    currPalindromeLens.add(len + 2);
                    if(len + 2 > maxLen){
                        maxLen = len + 2;
                        maxIdx = idx;
                    }
                }
            }
            dp.add(currPalindromeLens);
        }
        
        return s.substring(maxIdx - maxLen + 1, maxIdx + 1);
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "babad";
        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("longest palindrome : " + sol.longestPalindrome(s));
    }
}
