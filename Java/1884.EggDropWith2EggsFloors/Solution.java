/* Dynamic programming: Time:O(n^2), Space:O(n).
 * 1. dp[i] = the minimum number of moves with i floors 
 * 2. dp[i] = min(max(firstDropFloor, dp[roof - firstDropFloor] + 1)), where 1 < firstDropFloor <= roof
 */

import java.util.*; // Stack


public class Solution {
    public int twoEggDrop(int n) {
        int[] dp = new int[n + 1];
        for(int roof = 1; roof <= n; roof++){
            int min = roof;
            for(int firstDropFloor = roof; firstDropFloor > 0; firstDropFloor--){
                //if firstDropFloor break
                int countIfBreak = firstDropFloor;
                //if firstDropFloor NOT break
                int countIfNotBreak = dp[roof - firstDropFloor] + 1;
                min = Math.min(min, Math.max(countIfBreak, countIfNotBreak));
            }
            dp[roof] = min;
        }
        return dp[n];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 100
        System.out.println("n:" + n);
        System.out.println("min moves:" + sol.twoEggDrop(n));
    }
}
