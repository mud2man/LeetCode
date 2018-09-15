/* Binary search: O(logn)
 * 1. Find the "start", where nums[start] is the lowest
 * 2. Binary search target with given starting index "start"
 */         

import java.util.*;

public class Solution {
    public int search(int[] nums, int target) {
        int lb = 0;
        int hb = nums.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int num = nums[mid];
            int left = (mid > 0)? nums[mid - 1]: Integer.MAX_VALUE;
            int right = (mid < nums.length - 1)? nums[mid + 1]: Integer.MAX_VALUE;
            if(num < left && num < right){
                lb = mid;
                break;
            }
            else if(num >= nums[lb] && num > nums[hb]){
                lb = mid + 1;
            }
            else{
                hb = mid - 1;
            }
        }
        
        int len = nums.length;
        int start = lb;
        lb = 0;
        hb = nums.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int num = nums[(mid + start) % len];
            if(target > num){
                lb = mid + 1;
            }
            else{
                hb = mid - 1;
            }
        }
        
        return (lb < len && nums[(lb + start) % len] == target)? (lb + start) % len: -1;
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
