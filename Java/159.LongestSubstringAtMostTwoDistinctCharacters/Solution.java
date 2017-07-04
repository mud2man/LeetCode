/* Sliding window: O(n)
 * 1. Use two pointers to store the head and tail of the string with at most 2 characters
 * 2. Use hashmap to record the number of occurrence of ebery character in between head and tail
 * 3. If the size of hashmap == 3, move head to right until the size is equal to 2
 * 4. Otherwise, move tail to right
 */

import java.util.*;

public class Solution{
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character, Integer> distribution = new HashMap<Character, Integer>();
        int head, tail, maxLen, currLen;
        char c;
        
        maxLen = 0;
        head = 0;
        tail = 0;
        currLen = 0;
        
        for(tail = 0; tail < s.length(); tail++){
            c = s.charAt(tail);
            currLen++;
            if(distribution.containsKey(c)){
                distribution.put(c, distribution.get(c) + 1);
            }
            else{
                distribution.put(c, 1);
            }
            
            
            while(distribution.size() == 3){
                c = s.charAt(head++);
                currLen--;
                if(distribution.get(c) == 1){
                    distribution.remove(c); 
                }
                else{
                    distribution.put(c, distribution.get(c) - 1);
                }
            }
            maxLen = Math.max(currLen, maxLen);
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
