/* Binary search: Time:O(logn), Space:O(1)
 * 1. Use binary search to find the boundries
 */         

import java.util.*;

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int lb = 0;
        int hb = nums.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(nums[mid] < target){
                lb = mid + 1;
            }
            else{
                hb = mid - 1;
            }
        }
        
        if(lb >= nums.length || nums[lb] != target){
            return new int[]{-1, -1};
        }
        
        int start = lb;
        lb = 0;
        hb = nums.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(nums[mid] <= target){
                lb = mid + 1;
            }
            else{
                hb = mid - 1;
            }
        }
        return new int[]{start, hb};
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        System.out.println("range: " + Arrays.toString(sol.searchRange(nums, target)));
    }
}
