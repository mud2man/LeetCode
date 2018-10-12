/* Two pointers + HashMap: Time:O(n), Space:O(1)
 * 1. Have a map "map" to store the count of char
 * 2. If map doesn't contains s.charAt(tail), do nothing
 * 3. Otherwise, shift head to right until meet s.charAt(tail)
 */         

import java.util.*;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        
        Map<Character, Integer> visited = new HashMap<>();
        int maxLen = 1;
        int head = -1;
        for(int tail = 0; tail < s.length(); tail++){
            char c = s.charAt(tail);
            if(visited.containsKey(c) && visited.get(c) == 1){
                while(s.charAt(++head) != c){
                    visited.put(s.charAt(head), visited.get(s.charAt(head)) - 1);
                }
            }
            else{
                visited.put(c, 1);
            }
            maxLen = Math.max(maxLen, tail - head);
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
