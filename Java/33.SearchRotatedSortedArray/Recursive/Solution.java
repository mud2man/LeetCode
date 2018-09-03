/* Binary search: O(logn)
 * 1. In helper, there are three cases: nums[mid] == target, nums[mid] < target, nums[mid] <= nums[hb]
 * 2. In each case: Need to identify if mid on left or right slope, then check if target on left or right half
 */         

import java.util.*;

public class Solution {
    private int helper(int lb, int hb, int[] nums, int target){
        if(lb > hb){
            return -1;
        }
        
        int mid = (lb + hb) / 2;
        if(nums[mid] == target){
            return mid;
        }
        else if(nums[mid] < target){
            if(nums[mid] >= nums[lb]){
                return helper(mid + 1, hb, nums, target);
            }
            else{
                if(nums[hb] >= target){
                    return helper(mid + 1, hb, nums, target);
                }
                else{
                    return helper(lb, mid - 1, nums, target);
                }
            }
        }
        else{
            if(nums[mid] <= nums[hb]){
                return helper(lb, mid - 1, nums, target);
            }
            else{
                if(nums[lb] <= target){
                    return helper(lb, mid - 1, nums, target);
                }
                else{
                    return helper(mid + 1, hb, nums, target);
                }
            }
        }
    }
    
    public int search(int[] nums, int target) {
        return helper(0, nums.length - 1, nums, target);
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target;
        
        target = 7;
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        System.out.println("position: " + sol.search(nums, target));
    }
}
