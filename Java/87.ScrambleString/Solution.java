/* Divide and Conquer: Time:O(n^4), Space:O(n^4 * n * n). LeetCode has DP with the same time complexity
 * 1. Use set "matches" to store the success cases, and "misMatches" to store the failure cases
 * 2. Check if both (left of s1, left of s2) and (right of s2, right of s2) match
 * 3. Otherwise, check if both (left of s1, right of s2) and (right of s1, right of s2) match
 * 4. Add (s1 + "|" + s2) and (s2 + "|" + s1) into matches or misMatches, to prevent repeated calculation
 */

import java.util.*;

public class Solution{
    private boolean helper(String s1, String s2, Set<String> matches, Set<String> misMatches) {
        if(s1.equals(s2)){
            return true;
        }
        
        String hashCode = s1 + "|" + s2;
        if(matches.contains(hashCode)){
            return true;
        }
        if(misMatches.contains(hashCode)){
            return false;
        }
        
        int diff = 0;
        int[] count = new int[256];
        //from both satrt ends
        for(int i = 0; i < s1.length() - 1; ++i){
            char s1Char = s1.charAt(i);
            char s2Char = s2.charAt(i);
            count[s1Char]++;
            // diff - 1: -1 -> 0, diff + 1: 0 -> 1
            diff -= (count[s1Char] == 0)? 1 : (count[s1Char] == 1)? -1: 0;
            count[s2Char]--;
            // diff - 1: 1 -> 0, diff + 1: 0 -> -1
            diff -= (count[s2Char] == 0)? 1 : (count[s2Char] == -1)? -1: 0;
            if(diff == 0){
                boolean left = helper(s1.substring(0, i + 1), s2.substring(0, i + 1), matches, misMatches);
                boolean right = helper(s1.substring(i + 1), s2.substring(i + 1), matches, misMatches);
                if(left && right){
                    matches.add(s1 + "|" + s2);
                    matches.add(s2 + "|" + s1);
                    return true;
                }
            }
        }
        
        diff = 0;
        Arrays.fill(count, 0);
        //from satrt of s1, and end of s2
        for(int i = 0; i < s1.length() - 1; ++i){
            char s1Char = s1.charAt(i);
            char s2Char = s2.charAt(s2.length() - 1 - i);
            count[s1Char]++;
            // diff - 1: -1 -> 0, diff + 1: 0 -> 1
            diff -= (count[s1Char] == 0)? 1 : (count[s1Char] == 1)? -1: 0;
            count[s2Char]--;
            // diff - 1: 1 -> 0, diff + 1: 0 -> -1
            diff -= (count[s2Char] == 0)? 1 : (count[s2Char] == -1)? -1: 0;
            if(diff == 0){
                boolean left = helper(s1.substring(0, i + 1), s2.substring(s1.length() - 1 - i), matches, misMatches);
                boolean right = helper(s1.substring(i + 1), s2.substring(0, s1.length() - 1 - i), matches, misMatches);
                if(left && right){
                    matches.add(s1 + "|" + s2);
                    matches.add(s2 + "|" + s1);
                    return true;
                }
            }
        }
        misMatches.add(s1 + "|" + s2);
        misMatches.add(s2 + "|" + s1);
        return false;
    }
    
    public boolean isScramble(String s1, String s2) {
        return helper(s1, s2, new HashSet<>(), new HashSet<>());
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s1 = "great";
        String s2 = "rgeat";
        System.out.println("s1: " + s1 + ", s2: " + s2);
        System.out.println("is scramble: " + sol.isScramble(s1, s2));
    }
}
