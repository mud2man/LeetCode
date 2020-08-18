/* Dynamic programming: Time:O(1), Space:O(1)
 * 1.dp[i] = probability that the i-th person can get his own seat given i seat
 * 2.dp[i] = 1 / (i + 1) * (1 + dp(i - 1) + dp(i - 2) + ... + dp(1)) = 0.5, where i > 0
 *
 * ex: dp[0] = 1
 *     dp[1] = 0.5
 *     dp[2] = (1 / 3) * (1 + dp[1]) = (1 / 3) * (1 + 0.5) = 0.5
 *     dp[3] = (1 / 4) * (1 + dp[2] + dp[1]) = (1 / 4) * (1 + 0.5 + 0.5) = 0.5
 */     

import java.util.*; // Stack

public class Solution {
    public double nthPersonGetsNthSeat(int n) {
        return (n == 1)? 1.0 : 0.5;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 2;
        System.out.println("n:" + n);
        System.out.println("probability:" + sol.nthPersonGetsNthSeat(n));
    }
}
