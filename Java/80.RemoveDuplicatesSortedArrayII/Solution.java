/* Two Pointers: Time:O(n), Space: O(1)
 * 1. Have a window prevNums to keep tracking the last two visited numbers 
 * 2. If prevNums[0] != nums[i], swap the value of nums[tailIndex + 1] and nums[i]
 */         

import java.util.*;

public class Solution {
    private void swap (int[] nums, int source, int destination){
        int temp = nums[source];
        nums[source] = nums[destination];
        nums[destination] = temp;
    }
    
    public int removeDuplicates(int[] nums) {
        int tailIndex = -1;
        int[] prevNums = (nums.length >= 2)? new int[]{nums[0] - 2, nums[0] - 1}: new int[2];
        
        for(int i = 0; i < nums.length; ++i){
            int temp = nums[i];
            if(prevNums[0] != nums[i]){
                swap(nums, ++tailIndex, i);
            }
            prevNums[0] = prevNums[1];
            prevNums[1] = temp;
        }
        
        return tailIndex + 1;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1,1,1,2,2,3};
        
        sol = new Solution();

        System.out.println("nums before delete: " + Arrays.toString(nums));
        sol.removeDuplicates(nums);
        System.out.println("nums after delete: " + Arrays.toString(nums));
    }
}
