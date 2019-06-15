/* Quick sort: Time:O(nlogn), Space:O(logn)
 */

import java.util.*;

public class Solution {
    private void quicksort(int[] nums, int lb, int ub){
        if(lb >= ub){
            return ;
        }
        int pivot = nums[ub];
        int ptr = lb - 1;
        for(int i = lb; i < ub; ++i){
            if(nums[i] <= pivot){
                int temp = nums[ptr + 1];
                nums[++ptr] = nums[i];
                nums[i] = temp;
            }
        }
        int temp = nums[ptr + 1];
        nums[++ptr] = pivot;
        nums[ub] = temp;
        quicksort(nums, lb, ptr - 1);
        quicksort(nums, ptr + 1, ub);
    }
    
    public int[] sortArray(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
        return nums;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {5, 2, 3, 1};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("nums after sort:" + Arrays.toString(sol.sortArray(nums)));
    }
}
