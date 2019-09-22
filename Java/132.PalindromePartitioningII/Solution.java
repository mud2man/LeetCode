/* Dynamic Programming: Time:O(n^2), Space:O(n)
 * 1. dp[i] = the minimun cuts of s.substring(0, i + 1)
 * 2. We can traverse from left and expend string with middle char = s.charAt(i) until we fail to expend it
 * 3. We need to consider odd length and even length
 * 4. Note: dp[i] <= dp[j] as long as  i <= j. So the way we update dp[i] works
 */

import java.util.*;

public class Solution {
    public int minCut(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, s.length() - 1);
        for(int i = 0; i < s.length(); ++i){
            //odd length
            int start = i;
            int end = i;
            while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
                dp[end] = Math.min(dp[end], (start > 0)? (dp[start - 1] + 1): 0);
                start--;
                end++;
            }
            
            //even length
            start = i - 1;
            end = i;
            while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
                dp[end] = Math.min(dp[end], (start > 0)? (dp[start - 1] + 1): 0);
                start--;
                end++;
            }
        }
        return dp[s.length() - 1];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aab";
        System.out.println("s:" + s);   
        System.out.println("minimun cuts#:" + sol.minCut(s));   
	}
}
