/* Binary search: Time:O(n), Space;O(1)
 * 1. First, set min = Math.min(nums[0], nums[nums.length - 1]) in case the array not rotated
 * 2. If nums[mid] < left && nums[mid] < right, return min
 * 3. Or shift right if nums[mid] > nums[hb]
 * 4. Or shift left if nums[mid] < nums[hb]
 * 5. Otherwise, shift right if and update min with Math.min(min, nums[lb])
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
            if(nums[mid] < left && nums[mid] < right){
                lb = mid;
                break;
            }else if(nums[mid] >= nums[lb] && nums[mid] > nums[hb]){ //right slope
                lb = mid + 1;
            }else if(nums[mid] > nums[lb] && nums[mid] <= nums[hb]){ //left slope
                hb = mid - 1;
            }else if(nums[lb] > nums[hb]){
                lb++;
            }else{
                hb--;
            }
        }
        return nums[lb];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {2, 2, 2, 7, 0, 1, 2};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("minimum:" + sol.findMin(nums));
    }
}
