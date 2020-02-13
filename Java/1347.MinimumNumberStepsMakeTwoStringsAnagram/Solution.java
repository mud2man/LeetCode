/* Time:O(n), Space:O(n)
 * 1. Get the count of char from s and t
 * 2. Accumulate tCharCount[i] - sCharCount[i] if tCharCount[i] > sCharCount[i], since we have to replace the additional char(i + 'a')
 */

import java.util.*;

public class Solution{
    private int[] getCharCount(String s){
        int[] count = new int[26];
        for(char c: s.toCharArray()){
            count[c - 'a']++;
        }
        return count;
    }
    
    public int minSteps(String s, String t) {
        int[] sCharCount = getCharCount(s);
        int[] tCharCount = getCharCount(t);
        int count = 0;
        for(int i = 0; i < 26; ++i){
            count += (tCharCount[i] > sCharCount[i])? (tCharCount[i] - sCharCount[i]): 0;
        }
        return count;
    }
  
    public static void main(String[] args){
        String s = "bab";
        String t = "aba";
        Solution sol = new Solution();
        System.out.println("s:" + s + ", t:" + t);
        System.out.println("minimum number of replacement:" + sol.minSteps(s, t));
    }
}
