/* DFS: Time:O(2^n), Space:O(n)
 * 1. Sort as decreasing order
 * 2. Call dfs recursively, and check if base > target or (base + remain) < target, then terminated and return false
 */          

import java.util.*;

public class Solution {
    private boolean dfs(int[] nums, int idx, int base, int target, int remain){
        if(idx == nums.length){
            return (base == target);
        }
        
        if(base == target){
            return true;
        }
        else if(base > target || (base + remain) < target){
            return false;
        }
        else{
            boolean ret = dfs(nums, idx + 1, base + nums[idx], target, remain - nums[idx]);
            if(ret == true){
                return true;
            }
            else{
                return dfs(nums, idx + 1, base, target, remain - nums[idx]);
            }
        }
    }
    
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length / 2; ++i){
            int temp = nums[i];
            nums[i] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = temp;
        }
        
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        
        if(sum % 2 == 1){
            return false;
        }
        
        return dfs(nums, 0, 0, sum / 2, sum);
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 5, 11, 5};
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can partition equal: " + sol.canPartition(nums));
    }
}
