/* Dynamic programming: Time:O(d*f*target), Space:O(target)
 * 1. dp[y][x] = the count considering dice 1, 2, .. y with sum = x
 * 2. dp[y][x] can contribute dp[y + 1][x + 1], dp[y + 1][x + 2], ... dp[y + 1][x + f] with its count dp[y][x] 
 * 3. We don't count sum > target, and dp[d][target] is our answer
 */          

import java.util.*; 
public class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        int[][] dp = new int[2][target + 1];
        int base = 1_000_000_007;
        dp[0][0] = 1;
        for(int dice = 0; dice < d; ++dice){
            for(int sum = 0; sum <= target; ++sum){
                dp[(dice + 1) % 2][sum] = 0; 
            }
            for(int sum = 0; sum < target; ++sum){
                int count = dp[dice % 2][sum];
                for(int face = 1; face <= f && sum + face <= target && count > 0; ++face){
                    dp[(dice + 1) % 2][sum + face] = (dp[(dice + 1) % 2][sum + face] + count) % base;
                }
            }
        }
        return dp[d % 2][target];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int d = 1;
        int f = 6;
        int target = 3;
        System.out.println("d:" + d);
        System.out.println("f:" + f);
        System.out.println("target:" + target);
        System.out.println("roll count:" + sol.numRollsToTarget(d, f, target));
    }
}
