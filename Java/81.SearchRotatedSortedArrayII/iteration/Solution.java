/* Binary search: Average: Time:O(logn), Worst:O(n), Space:O(1)
 * 1. If nums[mid] > nums[lb], then mid is on left slope, then check if target in left slope. If so, search the left
 * 2. If nums[mid] < nums[hb], then mid is on right slope, then check if target in right slope. If so, search the right
 * 3. Otherwise, check if nums[lb] == target, if not, lb++
 */         

import java.util.*;

public class Solution {
    public boolean search(int[] nums, int target) {
        int lb = 0;
        int hb = nums.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(nums[mid] == target){
                return true;
            }
            
            //left slope
            if(nums[mid] > nums[lb] && target >= nums[lb] && target < nums[mid]){
                hb = mid - 1;
            }
            // right slope
            else if(nums[mid] < nums[hb] && target > nums[mid] && target <= nums[hb]){
                lb = mid + 1;
            }
            else{
                if(nums[lb] == target){
                    return true;
                }
                else{
                    lb++;
                }
            }
        }
        return false;
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
