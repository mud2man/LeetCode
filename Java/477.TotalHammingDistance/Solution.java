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
    public int totalHammingDistance(int[] nums) {
        int i, j, dis, size;
        int[] bit1Count, mask;
        
        size = nums.length;
        bit1Count = new int[32];
        mask = new int[32];
        dis = 0;
        
        for(i = 0; i< 32; ++i){
            mask[i] = 1 << i;
        }
        
        for(i = 0; i < 32; ++i){
            for(j = 0; j < size; ++j){
                if((nums[j] & mask[i]) != 0){
                    bit1Count[i]++;
                }
            }
            dis = dis + (size - bit1Count[i])*(bit1Count[i]);
        }
        return dis;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {4, 14, 2};
        
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("distence: " + sol.totalHammingDistance(nums));
    }
}
