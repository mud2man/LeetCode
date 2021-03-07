/* Dynamic Programming: Time:O(n), Space:O(n). LeetCode has O(n)-O(1) solution
 * 1. evenSumFromRight[i] = sum of even excluding nums[i] from right
 * 2. evenOddFromRight[i] = sum of odd excluding nums[i] from right
 * 3. Accumulate count if evenSumFromLeft + oddSumFromRight[i] == oddSumFromLeft + evenSumFromRight[i]
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int waysToMakeFair(int[] nums) {
        int[] evenSumFromRight = new int[nums.length];
        int[] oddSumFromRight = new int[nums.length];
        for(int i = nums.length - 1; i > 0 ; i--){
            if(i % 2 == 0){
                evenSumFromRight[i - 1] = nums[i] + evenSumFromRight[i];
                oddSumFromRight[i - 1] = oddSumFromRight[i];
            }else{
                evenSumFromRight[i - 1] = evenSumFromRight[i];
                oddSumFromRight[i - 1] = nums[i] + oddSumFromRight[i];
            }
        }
        
        int evenSumFromLeft = 0;
        int oddSumFromLeft = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            count +=(evenSumFromLeft + oddSumFromRight[i] == oddSumFromLeft + evenSumFromRight[i])? 1: 0;
            if(i % 2 == 0){
                evenSumFromLeft += nums[i];
            }else{
                oddSumFromLeft += nums[i];
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        int[] nums = {2, 1, 6, 4};
        Solution sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("fair count:" + sol.waysToMakeFair(nums));
    }
}
