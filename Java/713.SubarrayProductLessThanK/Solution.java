/* Two Pointer: Time:O(n), Space:O(1)
 * 1. Keep an max-product-window less than K
 * 2. Shift "end" right every time, and shift "start" to reach max-product less than K
 */

import java.util.*;

public class Solution{
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int product = 1;
        int count = 0;
        int start = 0;
        for(int end = 0; end < nums.length; ++end){
            product *= nums[end];
            while(product >= k && start <= end){
                product /= nums[start++];
            }
            count += (start <= end)? (end - start + 1): 0;
        }
        return count;
    }
 
    public static void main(String[] args){
        int K = 100;
        int[] nums = {10, 5, 2, 6};
        
        Solution sol = new Solution();
        System.out.println("K: " + K);
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("count: " + sol.numSubarrayProductLessThanK(nums, K));
    }
}
