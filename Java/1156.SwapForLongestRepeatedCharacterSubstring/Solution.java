/* Two pointers: Time:O(n), Space:O(n)
 * 1. Get the rightestIdx and leftestIdx w.r.t char
 * 2. Have char2Count and charsMoreThanOnce to record the character count and the characters with >1 frequency between (start, end]
 * 3. Shift end once each time, and move start until char2Count.size() <= 2 && charsMoreThanOnce.size() <= 1 s.t. meet swap policy 
 * 4. Check rightestIdx and leftestIdx to see if we can find a character out of (start, end), and update max
 */

import java.util.*; // Stack

public class Solution {
    public int maxRepOpt1(String text) {
        int[] rightestIdx = new int[26];
        int[] leftestIdx = new int[26];
        Arrays.fill(leftestIdx, text.length());
        for(int i = 0; i < text.length(); ++i){
            rightestIdx[text.charAt(i) - 'a'] = Math.max(i, rightestIdx[text.charAt(i) - 'a']);
            leftestIdx[text.charAt(i) - 'a'] = Math.min(i, leftestIdx[text.charAt(i) - 'a']);
        }
        
        Map<Character, Integer> char2Count = new HashMap<>();
        Set<Character> charsMoreThanOnce = new HashSet<>();
        int start = -1;
        int max = 1;
        for(int end = 0; end < text.length(); ++end){
            char endChar = text.charAt(end);
            char2Count.put(endChar, char2Count.getOrDefault(endChar, 0) + 1);
            if(char2Count.get(endChar) > 1){
                charsMoreThanOnce.add(endChar);
            }
            
            while(char2Count.size() > 2 || charsMoreThanOnce.size() > 1){
                char startChar = text.charAt(++start);
                char2Count.put(startChar, char2Count.get(startChar) - 1);
                if(char2Count.get(startChar) == 0){
                    char2Count.remove(startChar);
                }else if(char2Count.get(startChar) == 1){
                    charsMoreThanOnce.remove(startChar);
                }
            }
            List<Character> needReplaceChars = new ArrayList<>(char2Count.keySet());
            if(needReplaceChars.size() == 1){
                max = Math.max(max, end - start);
            }else{
                char char0 = needReplaceChars.get(0);
                char char1 = needReplaceChars.get(1);
                if(char2Count.get(char0) == 1 && (rightestIdx[char1 - 'a'] > end || leftestIdx[char1 - 'a'] < start)){
                    max = Math.max(max, end - start);
                }
                if(char2Count.get(char1) == 1 && (rightestIdx[char0 - 'a'] > end || leftestIdx[char0 - 'a'] < start)){
                    max = Math.max(max, end - start);
                }
            }
        }
        return max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String text = "ababa";
        System.out.println("text:" + text);
        System.out.println("Longest length:" + sol.maxRepOpt1(text));
    }
}
