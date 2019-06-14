/* Binary search: O(logn)
 * 1. If nums[mid] < left && nums[mid] < right, return the current number
 * 2. Otherwise, shift right if nums[mid] > nums[hb]
 * 3. The formula works under three cases
 *
 *          *  
 * case1:  /| /
 *          |/ 
 *
 *          /  
 * case2:  /
 *        /
 */

import java.util.*;

public class Solution {
    public int findMin(int[] nums) {
        int lb = 0;
        int hb = nums.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int left = (mid > 0)? nums[mid - 1]: Integer.MAX_VALUE;
            int right = (mid < nums.length - 1)? nums[mid + 1]: Integer.MAX_VALUE;
            if(nums[mid] <= left && nums[mid] <= right){
                return nums[mid];
            }else if(nums[mid] > nums[hb]){
                lb = mid + 1;
            }else{
                hb = mid - 1;
            }
        }
        //unreachale
        return 0;
    } 

    public static void main(String[] args){
        Solution sol;
        int maximum;
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        sol = new Solution();
        
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("minimum:" + sol.findMin(nums));
    }
}
