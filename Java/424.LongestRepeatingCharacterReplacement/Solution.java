/* Sliding Window: O(n)
 * 1. Devide the case into 26 characters
 * 2. In getMaxLength, if charArray[end] != c, k-- if k > 0
 * 3. Otherwise, add start until reach the char != c
 * 4. Keep doing step2 and step3, until reach the the end
 * 
 * ex: str = "ABCABB", k = 2, assume c = A
 * time[0]: end = 0, start = 0, str = "ABCABB", maxLen = 1, k = 2
 * time[1]: end = 1, start = 0, str = "AACABB", maxLen = 2, k = 1
 * time[2]: end = 2, start = 0, str = "AAAABB", maxLen = 3, k = 0
 * time[3]: end = 3, start = 0, str = "AAAABB", maxLen = 4, k = 0
 * time[4]: end = 4, start = 2, str = "ABAAAB", maxLen = 4, k = 0
 * time[5]: end = 5, start = 3, str = "ABCAAA", maxLen = 4, k = 0
 */

import java.util.*; // Stack

public class Solution {
    private int getMaxLength(char[] charArray, char c, int k){
        int maxLen = 0;
        int start = 0;
        for(int end = 0; end < charArray.length; ++end){
            if(charArray[end] != c){
                if(k > 0){
                    --k;
                }
                else{
                    while(charArray[start] == c){
                        start++;
                    }
                    start++;
                }
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
    
    public int characterReplacement(String s, int k) {
        int maxLen = 0;
        char[] charArray = s.toCharArray();
        
        for(char c = 'A'; c <= 'Z'; ++c){
            maxLen = Math.max(maxLen, getMaxLength(charArray, c, k));
        }    
        return maxLen;
    }
 
    public static void main(String[] args){
        Solution sol;
        String str = "ABCABB";
        int k, maxLen;

        sol = new Solution();
        k = 1;

        System.out.println("str: " + str);
        maxLen = sol.characterReplacement(str, k);
        System.out.println("maxLen: " + maxLen);
	}
}
