/* BFS + Dynamic Programming: O(n)
 * 1. dp[i] = the least number of steps can reach position i
 * 2. Have a pointer rightMostIndex to record the right most index which can be reached now
 * 3. When the position i is visited, it must be the shortest path. Because our visiting way is BFS
 * 4. The sooner we visit the position, the shorter the length is
 *
 */          

import java.util.*; // Stack

public class Solution {
    public int jump(int[] nums) {
        int size = nums.length;
        
        int[] dp = new int[nums.length];
        for(int i = 1; i < size; ++i){
            dp[i] = Integer.MAX_VALUE;
        }
        
        int rightMostIndex = 0;
        for(int i = 0; i < size; ++i){
            if(dp[i] == Integer.MAX_VALUE){
                return 0;
            }
            
            int currLimit = Math.min(size - 1, i + nums[i]);
            if(rightMostIndex < currLimit){
                for(int j = currLimit; j > i; --j){
                    if(dp[j] == Integer.MAX_VALUE){
                        dp[j] = dp[i] + 1; 
                    }
                    else{
                        break;
                    }
                }
            }
            
            rightMostIndex = Math.max(rightMostIndex, currLimit);
            if(rightMostIndex == size - 1){
                return dp[rightMostIndex];
            }
        }
        
        return 0;
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
