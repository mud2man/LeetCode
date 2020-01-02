/* Dynamic programming: Time:O(S*T), Space:O(S*T). We can optimize space complexity by module-2 easily
 * 1. dp[y][x] = the length of shortest window ending at S.charAt(x) which covers T.substring(0, y + 1)
 * 2. dp[y][x] = (charS == charT)? min(dp[y - 1][x - 1] + 1, dp[y][x - 1] + 1): dp[y][x - 1] + 1
 * 3. We set MAX = S.length() + 1 as threshold
 * 4. Finally, we get the minimum window with left-most starting index from the final row dp[T.length() - 1]
 */

import java.util.*;


public class Solution{
    public String minWindow(String S, String T) {
        int[][] dp = new int[T.length()][S.length()];
        int MAX = S.length() + 1;
        for(int y = 0; y < T.length(); ++y){
            for(int x = 0; x < S.length(); ++x){
                char charS = S.charAt(x);
                char charT = T.charAt(y);
                dp[y][x] = MAX;
                if(y == 0 && x == 0){
                    dp[y][x] = (charS == charT)? 1: dp[y][x];
                }else if(y == 0){
                    dp[y][x] = (charS == charT)? 1: (dp[y][x - 1] < MAX)? dp[y][x - 1] + 1: dp[y][x];
                }else if(x == 0){
                    dp[y][x] = MAX;
                }else{
                    dp[y][x] = (charS == charT)? Math.min(dp[y - 1][x - 1] + 1, dp[y][x - 1] + 1): dp[y][x - 1] + 1;
                }
            }
        }
        
        String window = "";
        int minLen = MAX;
        for(int x = 0; x < S.length(); ++x){
            if(dp[T.length() - 1][x] < MAX && minLen > dp[T.length() - 1][x]){
                minLen = dp[T.length() - 1][x];
                window = S.substring(x - minLen + 1, x + 1);
            }
        }
        return window;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "abcdebdde";
        String T = "bde";
        System.out.println("S:" + S);
        System.out.println("T:" + T);
        System.out.println("minimum window:" + sol.minWindow(S, T));
    }
}
