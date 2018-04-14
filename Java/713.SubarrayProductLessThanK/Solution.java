/* Two Pointer: Time:O(n), Space:O(1). Shift interval[1] is a cleaner solution in LeetCode
 * 1. Keep an max-product-window less than K
 * 2. Shift interval[0] right every time, and shift interval[1] to reach max-product less than K
 */

import java.util.*;

public class Solution{
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k == 0){
            return 0;
        }
        
        int[] interval = {0, -1};
        int count = 0;
        int product = 1;
        while(interval[0] < nums.length){
            while(product < k && interval[1] < (nums.length - 1)){
                product = product * nums[++interval[1]];
            }
            
            if(product >= k && interval[1] > 0){
                product = product / nums[interval[1]--];
            }
            
            count = count + (interval[1] - interval[0] + 1); 
            if(product > 1){
                product = product / nums[interval[0]]; 
            }
            interval[0]++;
            if(interval[1] < interval[0]){
                interval[1] = interval[0];
                if(interval[1] < nums.length){
                    product = product * nums[interval[1]]; 
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int K = 100;
        int[] nums = {10, 5, 2, 6};
        
        sol = new Solution();
        System.out.println("K: " + K);
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("count: " + sol.numSubarrayProductLessThanK(nums, K));
    }
}
