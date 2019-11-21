/* Dynamic programming: O(n)
 * 1. Have 2 array dpFirstTaken and dpFirstNotTaken
 * 2. dp[i] is the maximum mony can get when the rober visit the house i
 * 3. Return the gigger one between dpFirstTaken[nums.length - 2] and dpFirstNotTaken[nums.length - 1]
 */         

import java.util.*;

public class Solution {
    public int rob(int[] nums) {    
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        
        int[] dpFirstTaken = new int[nums.length];
        int[] dpFirstNotTaken = new int[nums.length];
        dpFirstTaken[0] = nums[0];
        dpFirstTaken[1] = nums[0];
        dpFirstNotTaken[0] = 0;
        dpFirstNotTaken[1] = nums[1];
        for(int i = 2; i < nums.length; ++i){
            dpFirstTaken[i] = Math.max(dpFirstTaken[i - 1], dpFirstTaken[i - 2] + nums[i]);
            dpFirstNotTaken[i] = Math.max(dpFirstNotTaken[i - 1], dpFirstNotTaken[i - 2] + nums[i]);
        }
        return Math.max(dpFirstTaken[nums.length - 2], dpFirstNotTaken[nums.length - 1]);
    }
 
    public static void main(String[] args){
        int[] nums = {3, 2, 1, 5, 6, 4};
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("The maximum money: " + sol.rob(nums));
    }
}
