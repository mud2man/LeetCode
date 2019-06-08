/* Time:O(n + m), Space:O(1), where n is the length of T, m is the length of S
 * 1. Treat "S" an order list, and get the character count "count" from "T"
 * 2. Scan the order list "S" from left, and append count[c] times to the "answer"
 * 3. Append the un-scanned character from "count" to answer, which their order doesn't matter
 */

import java.util.*;

public class Solution{
    public String customSortString(String S, String T) {
        int[] count = new int[26];
        for(char c: T.toCharArray()){
            count[c - 'a']++;
        }
        
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < S.length(); ++i){
            char c = S.charAt(i);
            for(int j = 0; j < count[c - 'a']; ++j){
                answer.append(c);
            }
            count[c - 'a'] = 0;
        }
        
        for(int i = 0; i < 26; ++i){
            char c = (char)(i + 'a');
            for(int j = 0; j < count[i]; ++j){
                answer.append(c);
            }
        }
        return answer.toString();
    }
  
    public static void main(String[] args){
        Solution sol;
        String S = "cba";
        String T = "abcd";
        sol = new Solution();
        
        System.out.println("S: " + S);
        System.out.println("T: " + T);
        System.out.println("after rearranged: " + sol.customSortString(S, T));
    }
}
