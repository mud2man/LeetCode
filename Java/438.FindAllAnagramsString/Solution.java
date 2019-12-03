/* Sliding window: Time:O(n), Space:O(1)
 * 1. Keep record the count of chars in the sliding window
 * 2. Put the "start" in "starts" when diff is 0
 * 3. Accumulate "diff" when count[i] is away from 0. Otherwise, decrease "diff"
 */

import java.util.*;

public class Solution{
    public List<Integer> findAnagrams(String s, String p) {
        if(s.length() < p.length()){
            return new ArrayList<>();
        }
        int[] count = new int[26];
        for(char c: p.toCharArray()){
            count[c - 'a']--;
        }
        
        int diff = p.length();
        for(int i = 0; i < p.length(); ++i){
            diff += (count[s.charAt(i) - 'a'] < 0)? -1: 1;
            count[s.charAt(i) - 'a']++;
        }
        
        List<Integer> starts = new ArrayList<>();
        if(diff == 0){
            starts.add(0);
        }
        for(int end = p.length(); end < s.length(); ++end){
            char addChar = s.charAt(end);
            diff += (count[addChar - 'a'] < 0)? -1: 1;
            count[addChar - 'a']++;
            char deleteChar = s.charAt(end - p.length());
            diff += (count[deleteChar - 'a'] > 0)? -1: 1;
            count[deleteChar - 'a']--;
            if(diff == 0){
                starts.add(end - p.length() + 1);
            }
        }
        return starts;
    }
  
    public static void main(String[] args){
        String s = "cbaebabacd";
        String p = "abc";
        Solution sol = new Solution();
        System.out.println("s:" + s);
        System.out.println("p:" + p);
        System.out.println("starts of anagrams:" + sol.findAnagrams(s, p));
    }
}
