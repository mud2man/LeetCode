/* Sliding window: Time:O(n), Space:O(1)
 * 1. Find the right end by traversing right, and update max
 * 2. Find the left end by traversing left, and update min
 */

import java.util.*;

public class Solution{
    public int findUnsortedSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int[] range = new int[2];
        range[0] = 1;
        
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] < max){
                range[1] = i;
            }
            else{
                max = nums[i];
            }
        }
        
        for(int i = nums.length - 1; i >= 0; --i){
            if(nums[i] > min){
                range[0] = i;
            }
            else{
                min = nums[i];
            }
        }
        
        return range[1] - range[0] + 1;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {2, 6, 4, 8, 10, 9, 15};

        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("shortest length: " + sol.findUnsortedSubarray(nums));
    }
}
