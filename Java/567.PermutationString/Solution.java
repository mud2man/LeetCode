/* Sliding window: O(n)
 * 1. Have a HashMap HashMap<Character, int[] pair> charCountMap to record the character occurance number
 * 2. pair[0] = the occurance of character in s1, pair[1] = the occurance of character in s2
 * 3. Have "diff" to record the number of difference, and return true if diff becomes 0
 */

import java.util.*;


public class Solution{
        public boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()){
            return false;
        }
        
        HashMap<Character, int[]> charCountMap = new HashMap<Character, int[]>();
        for(int i = 0; i < s1.length(); ++i){
            char c = s1.charAt(i);
            charCountMap.putIfAbsent(c, new int[2]);
            charCountMap.get(c)[0]++;
        }
        
        int diff = s1.length();
        for(int i = 0; i < s1.length(); ++i){
            char c = s2.charAt(i);
            if(charCountMap.containsKey(c)){
                charCountMap.get(c)[1]++;
                if(charCountMap.get(c)[0] >= charCountMap.get(c)[1]){
                    diff--;
                }
            }
        }

        //sliding window
        for(int i = s1.length(); i < s2.length() && diff != 0; ++i){
            char addChar = s2.charAt(i);
            if(charCountMap.containsKey(addChar)){
                charCountMap.get(addChar)[1]++;
                diff -= (charCountMap.get(addChar)[0] >= charCountMap.get(addChar)[1])? 1: 0;
            }
            char deleteChar = s2.charAt(i - s1.length());
            if(charCountMap.containsKey(deleteChar)){
                charCountMap.get(deleteChar)[1]--;
                diff += (charCountMap.get(deleteChar)[0] > charCountMap.get(deleteChar)[1])? 1: 0;
            }
        }
        return (diff == 0);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s1 = "ab";
        String s2 = "eidbaooo";
        
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("match exist: " + sol.checkInclusion(s1, s2));
    }
}
