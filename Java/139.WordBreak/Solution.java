/* Use Dynamic Programing: O(n^2),
 * 1. dp[i + 1] = (dp[i] & wordDict.contains(tail)) | (dp[i - 1] & wordDict.contains(tail)) | ....
 * ex: s: "leetcode" wordDict:["leet", "code"]
 *     dp[4] = (dp[3]=0 & wordDict.contains("t")) | (dp[2]=0 & wordDict.contains("et")) \
 *             (dp[1]=0 & wordDict.contains("eet")) | (dp[0]=1 & wordDict.contains("leet"))
 */

import java.util.*;

public class Solution{
    public boolean wordBreak(String s, Set<String> wordDict) {
        String tail;
        int i;
        int j;
        boolean[] dp;
        int len;
        int maxLen;
        
        len = s.length();
        
        if(len == 0){
            return true;
        }
        
        maxLen = 0;
        for(String word : wordDict){
            if(maxLen < word.length()){
                maxLen = word.length();
            }
        }
        
        dp = new boolean[len + 1];
        dp[0] = true;
        for(i = 0; i < len; ++i){
            for(j = 1; j <= Math.min(i + 1, maxLen); ++j){
                tail = s.substring(i - j + 1, i + 1);
                if(wordDict.contains(tail) && (dp[i - j + 1] == true)){
                    dp[i + 1] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
    
    public static void main(String[] args){
		Solution sol;
        String s;
		Set<String> wordDict;
		boolean canBroken;

       	s = "leetcode";
		sol = new Solution();
 		wordDict = new HashSet <String>();
		wordDict.add("leet");
		wordDict.add("code");

		canBroken = sol.wordBreak(s , wordDict);

        System.out.println("s: " + s);
        System.out.println("wrodDict: " + wordDict);
        System.out.println("canBorken: " + canBroken);
		
	}
}
