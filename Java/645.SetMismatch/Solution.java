/* Math: Time:O(n), Space:O(n)
 * 1. Xor nums and {1, 2, .. n} to get the xor of duplicate and missing number
 * 2. Then we get the rightest bit, and separate numbers to two groups given the rightest bit and xor
 * 3. After xor in the two groups, we can get the duplicate and missing number
 * 
 * ex: {1, 2, 2, 4}
 * step1: xor {1, 2, 2, 4} and {1, 2, 3, 4} => 2 ^ 3
 * step2: RightestBit of xor = 2'b1
 * step3: group0 = {1, 1, 3}, group1 = {2, 2, 2, 4, 4}
 * step4: xor grpup0 = 3, xor group1 = 2 
 */

import java.util.*; // Stack


public class Solution {
    public int[] findErrorNums(int[] nums) {
        int xor = 0;
        for(int i = 0; i < nums.length; i++){
            xor ^= (i + 1);
            xor ^= nums[i];
        }
        int rightestBit = xor & (~xor + 1);
        
        
        int[] ans = new int[2];
        for(int i = 0; i < nums.length; i++){
            if(((i + 1) & rightestBit) != 0){
                ans[0] = ans[0] ^ (i + 1);
            }else{
                ans[1] = ans[1] ^ (i + 1);
            }
            
            if((nums[i] & rightestBit) != 0){
                ans[0] = ans[0] ^ nums[i];
            }else{
                ans[1] = ans[1] ^ nums[i];
            }
        }
        
        for(int num: nums){
            if(num == ans[0]){
                return ans;
            }
        }
        return new int[]{ans[1], ans[0]};
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 2, 2, 4};
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("ans" + Arrays.toString(sol.findErrorNums(nums)));
    }
}
