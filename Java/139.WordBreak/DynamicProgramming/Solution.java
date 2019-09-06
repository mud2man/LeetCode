/* Use Dynamic Programing: O(n*k*m), where n is the length of s, k is the size of wordDict, m is the length of longest word
 * 1. dp[i + 1] = true, means s.substring(0, i + 1) can be seperated
 * 2. Only handle when dp[i] is true, 
 * 3. If dp[size] is true, means s.substring(0, size) can be seperated
 * ex: s: "leetcode", wordDict:["leet", "code"]
 *     dp[] = {1, 0, 0, 0, 1, 0, 0, 0, 1}
 */

import java.util.*;

public class Solution{
    public boolean wordBreak(String s, List<String> wordDict) {
        int size = s.length();
        boolean[] dp = new boolean[size + 1];
        dp[0] = true;
        
        for(int i = 0; i < size; ++i){
            if(dp[i] != true){
                continue;
            }else{
                for(String word: wordDict){
                    if(s.startsWith(word, i)){
                        dp[i + word.length()] = true; 
                    }
                }
            }
        }
        return dp[size];
    }
    
    public static void main(String[] args){
        List<String> wordDict = Arrays.asList("leet", "code");
        String s = "leetcode";
        Solution sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("wrodDict: " + wordDict);
        System.out.println("canBorken: " + sol.wordBreak(s, wordDict));
    }
}
