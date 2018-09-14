/* DFS: Time:O(k^n) Space:O(n)
 * 1. Sort the input array, and create a array residure[] with initial value (sum / k)
 * 2. Call helper and put the nums[index] into different residure[] every time 
 * 3. If index == -1 and remain == 0 retunr true
 */

import java.util.*;

public class Solution{
    private boolean helper(int[] nums, int index, int[] residure, int remain){
        if(index == -1 && remain == 0){
            return true;
        }
        
        int num = nums[index];
        for(int i = 0; i < residure.length; ++i){
            if(num <= residure[i]){
                residure[i] = residure[i] - num;
                if(helper(nums, index - 1, residure, remain - num)){
                    return true;
                }
                residure[i] = residure[i] + num;
            }
        }
        return false;
    }
    
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        
        int target = sum / k;
        if(target * k != sum){
            return false;
        }
        
        int[] residure = new int[k];
        for(int i = 0; i < k; ++i){
            residure[i] = target;
        }
        
        Arrays.sort(nums);
        return helper(nums, nums.length - 1, residure, sum);
    }

    public static void main(String[] args){
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        Solution sol = new Solution();
        
        System.out.println("k: " + k);
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can be divide? " + sol.canPartitionKSubsets(nums, k));
    }
}
