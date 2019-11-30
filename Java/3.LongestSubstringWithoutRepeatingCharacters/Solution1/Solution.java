/* Map: Time:O(n), Space:O(1)
 * 1. Have a map "map" to store the mapping between char and latset index
 * 2. If map doesn't contains charAt(idx), update maxLen with max(++currLen, maxLen)
 * 3. Otherwise, update currLen with min(++currLen, idx - map.get(c))
 */         

import java.util.*;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> char2Idx = new HashMap<Character, Integer>();
        int maxLen = 0;
        int currLen = 0;
        for(int idx = 0; idx < s.length(); idx++){
            char c = s.charAt(idx);
            if(!char2Idx.containsKey(c)){
                maxLen = Math.max(++currLen, maxLen);
            }else{
                currLen = Math.min(++currLen, idx - char2Idx.get(c));
                maxLen = Math.max(currLen, maxLen);
            }
            char2Idx.put(c, idx);
        }
        return maxLen;
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "abcabcbb";
        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("longest length: " + sol.lengthOfLongestSubstring(s));
    }
}
