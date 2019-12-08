/* Binary Search: Time:O(nlogm), Space:O(1), where n is nums#, and m is max(nums)
 * 1. Binary search the answer between lb = 1, and hb = max(nums[i]), where 0 <= i <= nums.length - 1
 */

import java.util.*;

public class Solution{
    private int divide(int[] nums, int divisor){
        int result = 0;
        for(int num: nums){
            result += (((num - 1) / divisor) + 1);
        }
        return result;
    }
    
    public int smallestDivisor(int[] nums, int threshold) {
        int hb = 1;
        for(int num: nums){
            hb = Math.max(hb, num);
        }
        
        int lb = 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int result = divide(nums, mid);
            if(result > threshold){
                lb = mid + 1;
            }else{
                hb = mid - 1;
            }
        }
        return lb;
    }
  
    public static void main(String[] args){
        int[] nums = {1, 2, 5, 9};
        int threshold = 6;
        Solution sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("threshold:" + threshold);
        System.out.println("smallest divisor:" + sol.smallestDivisor(nums, threshold));
    }
}
