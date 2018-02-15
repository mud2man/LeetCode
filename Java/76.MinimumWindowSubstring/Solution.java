/* Sliding window: Time:O(n), Space:O(n)
 * 1. Create a hashmap with key = character of t, value = [character# in t, character# in s within window]
 * 2. In the loop, shift the left end "j" of window until "remain" = 0
 * 3. Then, shift the right end "i" until remain > 0
 * 4. Update "minLength" and "minSubstring" in the end of loop
 */

import java.util.*;
public class Solution{
    public String minWindow(String s, String t) {
        HashMap<Character, int[]> characterToCount = new HashMap<Character, int[]>();
        
        for(int i = 0; i < t.length(); ++i){
            char c = t.charAt(i);
            characterToCount.putIfAbsent(c, new int[2]);
            characterToCount.get(c)[0]++;
        }
        
        int minLength = s.length() + 1;
        String minSubstring = "";
        int i = 0;
        int j = 0;
        int remain = t.length();
        while(j < s.length()){
            while(j < s.length() && remain > 0){
                char c = s.charAt(j++);
                if(characterToCount.containsKey(c)){
                    int[] pair = characterToCount.get(c);
                    if(pair[1] < pair[0]){
                        remain--;
                    }
                    pair[1]++;
                }
            }
            
            boolean isCover = (remain == 0)? true: false;
            
            while(i < j && remain == 0){
                char c = s.charAt(i++);
                if(characterToCount.containsKey(c)){
                    int[] pair = characterToCount.get(c);
                    pair[1]--;
                    if(pair[1] < pair[0]){
                        remain++;
                        break;
                    }
                }
            }
            
            if(minLength > (j - i + 1) && isCover){
                minSubstring = s.substring(i - 1, j);
                minLength = (j - i + 1);
            }
        }
        
        return minSubstring;
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
