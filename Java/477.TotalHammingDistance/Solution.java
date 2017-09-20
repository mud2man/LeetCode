/* Bit Manipulation: O(n)
 * 1. Count the number of 1s among all the numbers from bit0 to bit31
 * 2. Summerize haming distence of all pair by (size - bit1Count[i])*(bit1Count[i]) from bit0 to bit31
 *
 * ex: nums = {2'b0100, 2'b1110, 2'b0010}
 *     bit1Count = {1, 2, 2, 0}
 *     distence = 1*2 + 2*1 + 2*1 + 0*0 = 6 
 */

import java.util.*; // Stack

public class Solution {
    private void helper(int[] bitsCount, int num){
        int i = 0;
        while(num > 0){
            if((num & 1) > 0){
                bitsCount[i] += 1;
            }
            num = num >> 1;
            ++i;
        }
    }
    
    public int totalHammingDistance(int[] nums) {
        int[] bitsCount = new int[32];
        
        for(int num: nums){
            helper(bitsCount, num);
        }
        
        int size = nums.length;
        int totalDistance = 0;
        for(int i = 0; i < 32; ++i){
            totalDistance += (size - bitsCount[i]) * bitsCount[i];
        }
        
        return totalDistance;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {4, 14, 2};
        
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("distence: " + sol.totalHammingDistance(nums));
    }
}
