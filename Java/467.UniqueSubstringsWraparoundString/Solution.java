/* Dynamic programming: O(n)
 * 1. If currChar and preChar are continuous, dp[idx] = dp[idx - 1] + 1
 * 2. Also, we keep recording the maximum length of substring ending with dp[idx] to subStringLens[currChar]
 * 3. If dp[idx] > subStringLens[currChar], update subStringLens[currChar] and accumulate count with subStringLens[currChar]
 *
 * ex: p = "ababc"
 * idx = 1, dp = {1, 2, 0, 0, 0}, subStringLens = {1, 2, 0, ...}, count = 3
 * idx = 2, dp = {1, 2, 1, 0, 0}, subStringLens = {1, 2, 0, ...}, count = 3
 * idx = 3, dp = {1, 2, 1, 2, 0}, subStringLens = {1, 2, 0, ...}, count = 3
 * idx = 4, dp = {1, 2, 1, 2, 3}, subStringLens = {1, 2, 3, ...}, count = 6

 */

import java.util.*; // Stack

public class Solution {
    public int findSubstringInWraproundString(String p) {
        int[] dp, subStringLens;
        int idx, count;
        char currChar, prevChar;
        HashSet<String> dic;
        
        if(p.length() == 0){
            return 0;
        }
      
        dp = new int[p.length()];
        dp[0] = 1;
        count = 1;
        dic = new HashSet<String>();
        subStringLens = new int[26];
        subStringLens[p.charAt(0) - 'a'] = 1; 
        
        for(idx = 1; idx < p.length(); idx++){
            currChar = p.charAt(idx);
            prevChar = p.charAt(idx - 1);
            if(currChar == 'a' && prevChar == 'z'){
                dp[idx] = dp[idx - 1] + 1;
            }
            else if(prevChar == currChar - 1){
                dp[idx] = dp[idx - 1] + 1;
            }
            else{
                dp[idx] = 1;
            }
            
            if(subStringLens[currChar - 'a'] < dp[idx]){
                count += (dp[idx] - subStringLens[currChar - 'a']);
                subStringLens[currChar - 'a'] = dp[idx];
            }
        }
        
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        String p = "ababc";
        
        sol = new Solution();

        System.out.println("p: " + p);
        System.out.println("unique substring#: " + sol.findSubstringInWraproundString(p));
    }
}
