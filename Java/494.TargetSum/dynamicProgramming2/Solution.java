/* Dynamic programming: TimeO(n*k), Space:O(k), where k = (sum - S) / 2
 * 1. sum(possitive) = S + sum(negative)
 * 2. sum(possitive) + sum(negative) = S + 2*sum(negative)
 * 3. sum(all)/2 - S/2 = sum(negative)
 * 4. Therefore, we cae reformulate this problem as find the sum of subset,s.t. the subSum = sum(all)/2 - S/2
 * 5. Use dyanamic programming, we use dp[subSum + 1] to track number of combinations, because any num > subSum never be selected
 * 6. For every num, dp[idx] = dp[idx] + dp[idx - num], where idx goes from subSum to num
 *
 * ex: nums{1, 1, 1, 1, 1}, S = 1, dp = {1, 0, 0}, target = 5/2 - 1/2 = 2
 * time = 0, num = 1, dp = {1, 1, 0}
 * time = 1, num = 1, dp = {1, 2, 1}
 * time = 2, num = 1, dp = {1, 3, 3}
 * time = 3, num = 1, dp = {1, 4, 6}
 * time = 4, num = 1, dp = {1, 5, 10}
 *
 * => the way of subSum = 2 is 10, the way of subSum = 1 is 5, and the way of subSum = 0 is 1
 */          

import java.util.*; // Stack

public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        double doubleSum, doubleS, doubleTarget;
        int intTarget, idx;
        int[] db;
        
        doubleSum = 0;
        doubleS = (double)S;
        
        // get the sum of nums
        for(int num: nums){
            doubleSum = doubleSum + (double)num;
        }
        
        if(doubleSum < doubleS){
            return 0;
        }
        
        doubleTarget = doubleSum/2 - doubleS/2;
        intTarget = (int)(doubleSum/2 - doubleS/2);
        
        // cannot find answer
        if(doubleTarget - (double)intTarget > 0){
            return 0;
        }
        
        db = new int[intTarget + 1];
        db[0] = 1;
        
        for(int num: nums){
            for(idx = intTarget; idx >= num; idx--){
                db[idx] = db[idx] + db[idx - num];
            }
        }
        
        return db[intTarget];
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 1, 1, 1, 1};
        int S = 1;

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("S: " + S);
        System.out.println("the number of ways of combinations which sum = " + S + ": " + sol.findTargetSumWays(nums, S));
    }
}
