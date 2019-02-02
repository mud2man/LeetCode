/* BFS + Dynamic Programming: O(n)
 * 1. dp[i] = the least number of steps can reach position i
 * 2. We can prove dp[i] <= dp[j], where i < j
 * 3. Every step, we get the farrest reachable position "end", and update dp[j] by currStep + 1
 *
 * ex: nums = {2, 3, 1, 1, 4} 
 * time[0]: dp{0, max, max, max, max}
 * time[1]: dp{0, 1, 1, max, max}
 * time[2]: dp{0, 1, 1, 2, 2} => done
 */          

import java.util.*; // Stack

public class Solution {
    public int jump(int[] nums) {
        int size = nums.length;
        int[] dp = new int[size];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < size; ++i){
            int currStep = dp[i];
            int end = Math.min(size - 1, i + nums[i]);
            for(int j = end; j > i && dp[j] == Integer.MAX_VALUE; --j){
                dp[j] = currStep + 1;
            }
            
            if(end == size - 1){
                return dp[end];
            }
        }
        return dp[size - 1];
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {2, 3, 1, 1, 4};
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        int shortestPath = sol.jump(nums);
        System.out.println("shortestPath: " + shortestPath);
    }
}
