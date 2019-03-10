/* Hashmap + Sliding window: Time:O(n), Space:O(n)
 * 1. Move 'l' pointer to right, and update map until the size of map is equal to k
 * 2. During every loop, keep update 'maxLen' by max(maxLen, r - l + 1)
 */

import java.util.*;

public class Solution{
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k == 0){
            return 0;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        int l = 0;
        int maxLen = 0;
        for(int r = 0; r < s.length(); ++r){
            char rc = s.charAt(r);
            map.putIfAbsent(rc, 0);
            map.put(rc, map.get(rc) + 1);
            
            while(map.size() > k && l < r){
                char lc = s.charAt(l++);
                map.put(lc, map.get(lc) - 1);
                if(map.get(lc) == 0){
                    map.remove(lc);
                }
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
 
    public static void main(String[] args){
        String s = "eceba";
        int k = 2;
        Solution sol = new Solution();

        System.out.println("s:" + s);
        System.out.println("k:" + k);
        System.out.println("longest substring: " + sol.lengthOfLongestSubstringKDistinct(s, k));
    }
}
