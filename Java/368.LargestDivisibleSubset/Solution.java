/* Dynamic programming: Time:O(n^2), Space:O(n)
 * 1. Sort nums, 
 * 2. dp[i][1] = the index of the  dividend if current number, 
 * 3. dp[i][0] = the number of divisible subset including thee current number
 * 4. Retrieve the subset according dp
 * 
 * ex: nums = {1, 2, 3}
 * dp = {{1, 1}, {2, 0}, {2, 0}}
 */

import java.util.*;

public class Solution{
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums == null || nums.length == 0){
            return new LinkedList<Integer>();
        }
        
        Arrays.sort(nums);
        int[][] dp = new int[nums.length][2];
        int[] max = {0, 0};
        for(int i = 0; i < nums.length; ++i){
            dp[i][0] = 1;
            dp[i][1] = i;
            for(int j = i - 1; j >= 0; --j){
                if(nums[i] % nums[j] == 0){
                    if(dp[i][0] < (dp[j][0] + 1)){
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = j;
                    }
                }
            }
            
            if(max[0] < dp[i][0]){
                max[0] = dp[i][0];
                max[1] = i;
            }
        }
        
        List<Integer> subset = new LinkedList<Integer>();
        int lastIndex = max[1];
        subset.add(0, nums[lastIndex]);
        while(lastIndex != dp[lastIndex][1]){
            lastIndex = dp[lastIndex][1];
            subset.add(0 ,nums[lastIndex]);
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
