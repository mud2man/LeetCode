/* Dynamic programming: Time:O(n), Space:O(1)
 * 1. Use minMax[], where minMax[0] is the minimum product containing nums[i], mixMax[1] is the maximum product containing nums[i]
 * 2. Traverse nums and keeping update minMax
 */

import java.util.*;


public class Solution {
	public int maxProduct(int[] nums) {
        int[] minMax = {nums[0], nums[0]};
        int globalMax = nums[0];
        for(int i = 1; i < nums.length; ++i){
            int localMax = Math.max(nums[i], Math.max(nums[i] * minMax[0], nums[i] * minMax[1]));
            int localMin = Math.min(nums[i], Math.min(nums[i] * minMax[0], nums[i] * minMax[1]));
            globalMax = Math.max(localMax, globalMax);
            minMax[0] = localMin;
            minMax[1] = localMax;
        }
        return globalMax; 
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {2, 3, -2, 4};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maximum product:" + sol.maxProduct(nums));
    }
}
