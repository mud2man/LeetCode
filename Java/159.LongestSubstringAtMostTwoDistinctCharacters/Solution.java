/* Sliding window: Time:O(n), Space:O(1)
 * 1. Use two pointers to store the head and tail of the string with at most 2 characters
 * 2. Use hashmap to record the number of occurrence of ebery character in between head and tail
 * 3. If the size of hashmap == 3, move head to right until the size is equal to 2
 * 4. Otherwise, move tail to right
 */

import java.util.*;

public class Solution{
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character, Integer> distribution = new HashMap<Character, Integer>();
        int maxLen = 0;
        for(int tail = 0, head = 0; tail < s.length(); tail++){
            char c = s.charAt(tail);
            distribution.putIfAbsent(c, 0);
            distribution.put(c, distribution.get(c) + 1);
            while(distribution.size() == 3){
                c = s.charAt(head++);
                if(distribution.get(c) == 1){
                    distribution.remove(c); 
                }
                else{
                    distribution.put(c, distribution.get(c) - 1);
                }
            }
            maxLen = Math.max(tail - head + 1, maxLen);
        }
        return maxLen;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "eceba";
        
        System.out.println("s: " + s);
        System.out.println("Maximum length: " + sol.lengthOfLongestSubstringTwoDistinct(s));
    }
}
