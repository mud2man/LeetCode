/* Sliding window: Time:O(n), Space:O(n)
 * 1. Create a hashmap with key = character of t, value = [character# in t, character# in s within window]
 * 2. In the loop, shift the right end "right", and update remain and update char2count
 * 3. Then, shift the left end "left" as long as the window contains all char in t (remain = 0)
 * 4. Update "minLength" and "ret"" in the end of loop
 */

import java.util.*;
public class Solution{
    public String minWindow(String s, String t) {
        int remain = t.length();
        Map<Character, int[]> char2count = new HashMap<>();
        for(int i = 0; i < t.length(); ++i){
            char c = t.charAt(i);
            char2count.putIfAbsent(c, new int[2]);
            char2count.get(c)[0]++;
        }
        
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        String ret = "";
        for(int right = 0; right < s.length(); ++right){
            char c = s.charAt(right);
            if(char2count.containsKey(c)){
                char2count.get(c)[1]++;
                if(char2count.get(c)[0] >= char2count.get(c)[1]){
                    remain--;
                }
            }
            
            //window contains all char in t
            if(remain == 0){
                c = s.charAt(left);
                while(!char2count.containsKey(c) || char2count.get(c)[0] < char2count.get(c)[1]){
                    if(char2count.containsKey(c)){
                        char2count.get(c)[1]--;
                    }
                    c = s.charAt(++left);
                }
                
                if((right - left + 1) < minLength){
                    ret = s.substring(left, right + 1);
                    minLength = right - left + 1;
                }
            }
        }
        
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "ADOBECODEBANC";
        String t = "ABC";

        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("t: " + t);
        System.out.println("minimum window substring:" + sol.minWindow(s, t));
    }
}
