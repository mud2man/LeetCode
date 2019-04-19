/* Divide & Conquer: Time:O(n^2), Space:O(n)
 * 1. Call helper recursivley
 * 2. In helper, get the "count" info, and seperate the string to substrings by separate char, where count[char] < k
 * 3. If there is no separate char, then return end - start + 1
 * 4. Otherwise, call helper with given substring, where substring contains no separate char, and keep max updated
 * 5. Since we put at least on char into separate char set, the depth of recursion is 26. So the complexity is O(n)
 */

import java.util.*;

public class Solution{
    private int helper(String s, int start, int end, int k){
        int[] count = new int[26];
        for(int i = start; i <= end; ++i){
            count[s.charAt(i) - 'a']++;
        }
        
        boolean isMeet = true;
        for(int i = 0; i < 26; ++i){
            if(count[i] > 0 && count[i] < k){
                isMeet = false;
            }
        }
        
        if(isMeet == true){
            return end - start + 1;
        }
        
        int i = start;
        int max = 0;
        while(i <= end){
            while(i <= end && count[s.charAt(i) - 'a'] < k){
                ++i;
            }
            int j = i;
            while(j <= end && count[s.charAt(j) - 'a'] >= k){
                ++j;
            }
            max = Math.max(max, helper(s, i, j - 1, k));
            i = j;
        }
        return max;
    }
    
    public int longestSubstring(String s, int k) {
        return helper(s, 0, s.length() - 1, k);
    }
 
    public static void main(String[] args){
        String s = "aaabb";
        int k  = 3;
        Solution sol = new Solution();

        System.out.println("s" + s);
        System.out.println("k:" + k);
        System.out.println("longest length of substring: " + sol.longestSubstring(s, k));
    }
}
