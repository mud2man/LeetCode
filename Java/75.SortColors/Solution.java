/* Quicksort like partition: O(n)
 * 1. The zeroIdx = the last index of zeros
 * 2. The oneIdx = the last index of ones
 * 3. Take snapshot example: [0,1,1,2,2,0,2], zeroIdx = 0, oneIdx = 2, idx = 5, 
 *    where nums[0, 4] is partitioned finished
 *
 */

import java.util.*;

public class Solution{
    public void sortColors(int[] nums) {
        int zeroIdx;
        int oneIdx;
        int idx;
        
        zeroIdx = -1;
        oneIdx = -1;
        
        for(idx = 0 ; idx < nums.length; ++idx){
            switch (nums[idx]){
                case 0:
                    nums[idx]= nums[++oneIdx];
                    nums[oneIdx] = nums[++zeroIdx];
                    nums[zeroIdx] = 0;
                    break;
                case 1:
                    nums[idx]= nums[++oneIdx];
                    nums[oneIdx] = 1;
                    break;
                case 2:
                    break;
            }
        }
    }
 
    public static void main(String[] args){
		Solution sol;
		int[] nums = {0,2,1,1,0,2};
		
		sol = new Solution();
		
        System.out.println("before sort: " + Arrays.toString(nums));
		sol.sortColors(nums);
		System.out.println("after sort: " + Arrays.toString(nums));
	}
}
