/* Binary search: Time:O(logn), Space:O(1)
 * 1. In loop, return mid if nums[mid] == target
 * 2. Then, identify if mid on left or right slope, then check if target on left or right half
 */         

import java.util.*;

public class Solution {
    public int search(int[] nums, int target) {
        int lb = 0;
        int hb = nums.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(nums[mid] == target){
                return mid;
            }
            
            //left solpe
            if(nums[mid] >= nums[lb]){
                if(nums[mid] > target && nums[lb] <= target){
                    hb = mid - 1;
                }
                else{
                    lb = mid + 1;
                }
            }
            //right solpe
            else{
                if(nums[mid] < target && nums[hb] >= target){
                    lb = mid + 1;
                }
                else{
                    hb = mid - 1;
                }
            }
        }
        return -1;
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
