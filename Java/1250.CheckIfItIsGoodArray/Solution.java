/* Math: Time:O(n), Space:O(1)
 * 1. We can reach the goal if we get the GCD among a set of numbers, which is equal to 1
 * 2. Keep update the GCD by euclidean algorithm  nums[i] and gcd 
 */

import java.util.*; // Stack

public class Solution {
    private int euclideanAlgorithm(int num0, int num1){
        int small = Math.min(num0, num1);
        int big = Math.max(num0, num1);
        if(small == big || small == 0){
            return big;
        }else{
            return euclideanAlgorithm(small, big % small);
        }
    }
    
    public boolean isGoodArray(int[] nums) {
        int gcd = nums[0];
        for(int i = 1; i < nums.length; ++i){
            gcd = euclideanAlgorithm(gcd, nums[i]);
        }
        return (gcd == 1);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {12, 5, 7, 23};
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("array is good:" + sol.isGoodArray(nums));
    }
}
