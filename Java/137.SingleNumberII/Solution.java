/* Bit manupulate: Time:O(n), Space:O(1)
 * 1. Count the digit number for every num in nums
 * 2. Filter out the digit which its count is not 3's multiple, and retrieve the single
 */

import java.util.*; // Stack

public class Solution {
    public int singleNumber(int[] nums) {
        int single = 0;
        int[] digit = new int [32];
        for(int num: nums){
            for(int i = 0; i < 32; ++i){
                digit[i] += ((num & (1 << i)) != 0)? 1: 0;
            }
        }
        
        for(int j = 0, base = 1; j < 32; ++j){
            if(digit[j] % 3 != 0){
                single = single + base;
            }
            base = base << 1;
        }
        return single;
    }
  
    public static void main(String[] args){
        int[] nums = {2, 2, 3, 2};
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("single: " + sol.singleNumber(nums));
    }
}
