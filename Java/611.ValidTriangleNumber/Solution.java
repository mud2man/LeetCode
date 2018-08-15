/* Greedy: Time:O(n^2), Space:O(1).
 * 1. Sort numbers, and fixed i and j, then shift k until (nums[i] + nums[j]) <= nums[k]
 * 2. Accumulate count with (k - j - 1)
 * 3. Since j and k only move right, so the inner loop is O(n)
 */

import java.util.*;

public class Solution{
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        
        int count = 0;
        for(int i = 0; i < nums.length - 2; ++i){
            int k = i + 2;
            for(int j = i + 1; j < nums.length - 1; ++j){
                while(k < nums.length && (nums[i] + nums[j]) > nums[k]){
                    ++k;
                }
                count += ((k - j) > 1)? (k - j - 1): 0;
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int nums[] = {2, 2, 3, 4};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("triangle number: " + sol.triangleNumber(nums));
    }
}
