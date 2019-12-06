/* Quicksort like partition: O(n)
 * 1. The zeroIdx = the last index of zeros
 * 2. The oneIdx = the last index of ones
 * 3. Take snapshot example: [0,1,1,2,2,0,2], zeroIdx = 0, oneIdx = 2, idx = 5, 
 *    where nums[0, 4] is partitioned finished
 *
 */

import java.util.*;

public class Solution{
    private void swap(int[] nums, int source, int destination){
        int temp = nums[source];
        nums[source] = nums[destination];
        nums[destination] = temp;
    }
    
    public void sortColors(int[] nums) {
        int ptr0 = -1;
        int ptr1 = -1;
        
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] == 2){
                continue;
            }else if(nums[i] == 1){
                swap(nums, ++ptr1, i);
            }else{
                swap(nums, ++ptr1, i);
                swap(nums, ++ptr0, ptr1);
            }
        }
    }
  
    public static void main(String[] args){
        int[] nums = {0,2,1,1,0,2};
        Solution sol = new Solution();
        
        System.out.println("before sort: " + Arrays.toString(nums));
        sol.sortColors(nums);
        System.out.println("after sort: " + Arrays.toString(nums));
	}
}
