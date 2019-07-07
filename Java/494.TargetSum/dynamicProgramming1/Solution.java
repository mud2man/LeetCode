/* Dynamic programming: Time:O(n*k), Space:O(n*k), where k = 2 * sum
 * 1. Let sum = Sum(nums), base = -sum
 * 2. dp[y][x] the number of combinations between 0-th and y-th element of nums with sum = x - base, 
 * 3. Let previous value preVal = x + base, where 0 <= x <= 2*width
 * 4. In plus direction, currVal = preVal + num, dp[y][currVal - base] += dp[y - 1][x]
 * 5. In minus direction, currVal = preVal - num, dp[y][currVal - base] += dp[y - 1][x]
 * 6. Finally, return dp[depth - 1][S - base]   
 *
 * ex: nums{1, 1, 1, 1}, S = 2
 * time = 0:
 *      [0, 0, 0, 1, 0, 1, 0, 0, 0]
 *      [0, 0, 0, 0, 0, 0, 0, 0, 0]
 *      [0, 0, 0, 0, 0, 0, 0, 0, 0]
 *      [0, 0, 0, 0, 0, 0, 0, 0, 0]
 *
 * time = 1:
 *      [0, 0, 0, 1, 0, 1, 0, 0, 0]
 *      [0, 0, 1, 0, 2, 0, 1, 0, 0]
 *      [0, 0, 0, 0, 0, 0, 0, 0, 0]
 *      [0, 0, 0, 0, 0, 0, 0, 0, 0]
 *
 * time = 2:
 *      [0, 0, 0, 1, 0, 1, 0, 0, 0]
 *      [0, 0, 1, 0, 2, 0, 1, 0, 0]
 *      [0, 1, 0, 3, 0, 3, 0, 1, 0]
 *      [0, 0, 0, 0, 0, 0, 0, 0, 0]
 *
 * time = 3:
 *      [0, 0, 0, 1, 0, 1, 0, 0, 0]
 *      [0, 0, 1, 0, 2, 0, 1, 0, 0]
 *      [0, 1, 0, 3, 0, 3, 0, 1, 0]
 *      [1, 0, 4, 0, 6, 0, 4, 0, 1]
 *
 * => dp[3][2 - (-4)] = dp[3][6] is the answer
 */          

import java.util.*; // Stack

public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int width = 0;
        int depth = nums.length;
        
        if(depth == 0){
            return 0;
        }
        
        for(int num: nums){
            width += num;
        }
        
        int[][] dp = new int[depth][2 * width + 1];
        int base = -width;
        
        dp[0][nums[0] - base] += 1;
        dp[0][-nums[0] - base] += 1;
        for(int y = 1; y < depth; ++y){
            int num = nums[y];
            for(int x = 0; x <= 2 * width; ++x){
                if(dp[y - 1][x] > 0){
                    int preVal = x + base;
                    // +
                    int currVal = preVal + num;
                    dp[y][currVal - base] += dp[y - 1][x];
                
                    //-
                    currVal = preVal - num;
                    dp[y][currVal - base] += dp[y - 1][x];
                }
            }
        }
        
        if((S - base) >= 0 && (S - base) <= (2 * width)){
            return dp[depth - 1][S - base];
        }
        else{
            return 0;
        }
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 1, 1, 1};
        int S = 2;

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("S: " + S);
        System.out.println("the number of ways of combinations which sum = " + S + ": " + sol.findTargetSumWays(nums, S));
    }
}
