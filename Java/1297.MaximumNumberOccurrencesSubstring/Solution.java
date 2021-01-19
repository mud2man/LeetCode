/* Slide window: Time:O(26 * n), Space:O(26 * n)
 * 1. We can prove the maximum number of occurrences of a substring must owned by the one with length==minSize
 * 2. Scan string with minSize-long slide window to update max
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int length = minSize;
        int[] letter2Count = new int[26];
        int uniqueCharCount = 0;
        Map<String, Integer> string2Count = new HashMap<>();
        for(int i = 0; i < length - 1; i++){
            letter2Count[s.charAt(i) - 'a']++;
            uniqueCharCount +=(letter2Count[s.charAt(i) - 'a'] == 1)? 1: 0;
        }
        
        int max = 0;
        for(int i = length - 1; i < s.length(); i++){
            letter2Count[s.charAt(i) - 'a']++;
            uniqueCharCount +=(letter2Count[s.charAt(i) - 'a'] == 1)? 1: 0;
            if(i - length >= 0){
                char deleteChar = s.charAt(i - length);
                letter2Count[deleteChar - 'a']--;
                uniqueCharCount -=(letter2Count[deleteChar - 'a'] == 0)? 1: 0;
            }
            if(uniqueCharCount <= maxLetters){
                String substring = s.substring(i - length + 1, i + 1);
                string2Count.put(substring, string2Count.getOrDefault(substring, 0) + 1);
                max = Math.max(max, string2Count.get(substring));
            }
        }
        return max;
    } 
 
    public static void main(String[] args){
        String s = "edbbdaaaa";
        int maxLetters = 2;
        int minSize = 3;
        int maxSize = 4;
        Solution sol = new Solution();
        System.out.println("s:" + s + ", maxLetters:" + maxLetters + ", minSize:" + minSize + ", maxSize:" + maxSize);
        System.out.println("max number:" + sol.maxFreq(s, maxLetters, minSize, maxSize));
    }
}
