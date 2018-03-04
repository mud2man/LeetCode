/* Dynamic programming: Time:O(2^n), Space:O(n)
 * 1. Sort nums, 
 * 2. dp[i][0] = the index of the  dividend if current number, 
 * 3. dp[i][1] = the number of divisible subset including thee current number
 * 4. Retrieve the subset according dp
 * 
 * ex: nums = {1, 2, 3}
 * dp = {{1, 1}, {0, 2}, {0, 2}}
 */

import java.util.*;

public class Solution{
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums == null || nums.length == 0){
            return new LinkedList<Integer>();
        }
        
        Arrays.sort(nums);
        int[][] dp = new int[nums.length][2];
        int[] tracker = new int[2];
        tracker[0] = 0;
        tracker[1] = 1;
        
        dp[0][0] = 0;
        dp[0][1] = 1;
        for(int i = 1; i < nums.length; ++i){
            dp[i][0] = i;
            dp[i][1] = 1;
            for(int j = i - 1; j >= 0; --j){
                if(nums[i] % nums[j] == 0 && dp[i][1] < (dp[j][1] + 1)){
                    dp[i][0] = j;
                    dp[i][1] = dp[j][1] + 1;
                }
            }
            
            if(tracker[1] < dp[i][1]){
                tracker[0] = i;
                tracker[1] = dp[i][1];
            }
        }
        
        List<Integer> subset = new LinkedList<Integer>();
        int index = tracker[0];
        int previousNum = nums[index] - 1;
        while(previousNum != nums[index]){
            subset.add(0, nums[index]);
            previousNum = nums[index];
            index = dp[index][0];
        }
        
        return subset;
    }

    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> subsets;
        int[] nums = {1, 2, 3};
        
        sol = new Solution();
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("subsets: " + sol.largestDivisibleSubset(nums));
    }
}
