/* Binary search: Average: O(logn), Worst:O(n)
 * 1. Call binarySearch recursively
 * 2. If nums[mid] > nums[lb], then mid is on left slope, then check if target in left slope. If so, search the left
 * 3. If nums[mid] < nums[hb], then mid is on right slope, then check if target in right slope. If so, search the right
 * 6. Otherwise, find target from both halves
 */         

import java.util.*;

public class Solution {
    private boolean binarySearch(int[] nums, int lb, int hb, int target){
        if(lb > hb){
            return false;
        }

        int mid = (lb + hb) / 2;
        if(nums[mid] == target){
            return true;
        }
        
        if(nums[mid] > nums[lb] && target <= nums[mid] && target >= nums[lb]){ //left slope
            return binarySearch(nums, lb, mid - 1, target);
        }
        else if(nums[mid] < nums[hb] && target <= nums[hb] && target >= nums[mid]){ // right slope
            return binarySearch(nums, mid + 1, hb, target);
        }
        else{
            return binarySearch(nums, lb, mid - 1, target) | binarySearch(nums, mid + 1, hb, target);
        }
    }
    
    public boolean search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
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
