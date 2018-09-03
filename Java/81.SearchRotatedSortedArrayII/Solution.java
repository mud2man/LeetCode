/* Binary search: Average: O(logn), Worst:O(n)
 * 1. Call helper recursively
 * 2. In helper, there are 4 cases: nums[mid] == target, nums[mid] > nums[lb] (1st slope), nums[mid] < nums[lb](2nd slope), others 
 * 3. If nums[mid] == target, return true
 * 4. If mid is in 1st slope, find targert from right half if target is not in 1st slope, or find it from left half if target is in
 *    in 1st slope, otherwise find it from both halves
 * 5. If mid is in 2nd slope, find targert from left half if target is not in 2nd slope, or find it from right half if target is in
 *    in 2nd slop, otherwise find it from both halvese
 * 6. Otherwise, find target from both halvese
 */         

import java.util.*;

public class Solution {
    private boolean helper(int lb, int hb, int[] nums, int target){
        if(lb > hb){
            return false;
        }
        
        int mid = (lb + hb) / 2;
        if(nums[mid] == target){
            return true;
        }
        else if(nums[mid] > nums[lb]){
            if(target > nums[mid] || target < nums[lb]){
                return helper(mid + 1, hb, nums, target);
            }
            else if(target < nums[mid] && target >= nums[lb]){
                return helper(lb, mid - 1, nums, target);
            }
            else{
                return helper(lb, mid - 1, nums, target) | helper(mid + 1, hb, nums, target);
            }
        }
        else if(nums[mid] < nums[lb]){
            if(target < nums[mid] || target > nums[hb]){
                return helper(lb, mid - 1, nums, target);
            }
            else if(target > nums[mid] && target <= nums[hb]){
                return helper(mid + 1, hb, nums, target);
            }
            else{
                return helper(lb, mid - 1, nums, target) | helper(mid + 1, hb, nums, target);
            }
        }
        else{
            return helper(lb, mid - 1, nums, target) | helper(mid + 1, hb, nums, target);
        }
    }
    
    public boolean search(int[] nums, int target) {
        return helper(0, nums.length - 1, nums, target);
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {4, 5, 5, 6, 7, 0, 1, 1, 2};
        int target;
        
        target = 7;
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        System.out.println("position: " + sol.search(nums, target));
    }
}
