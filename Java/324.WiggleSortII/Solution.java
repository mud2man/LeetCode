/* Select: Time:O(n), Space:O(n). LeetCode hase O(n) + O(1) solution, but it's too tricky to do it
 * 1. Apply selectKth to find the median, s.t. array => l0, l1, m2, m3, s4, s5
 * 2. Interleave the array with the two half, s.t. array => m3, l0, s4, l1, s5, m2
 */

import java.util.*;

public class Solution{
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private int selectKthLargest(int[] nums, int k, int start, int end){
        while(start < end){
            int biggerTailIdx = start - 1;
            int equalTailIdx = start - 1;
            int pivot = nums[end];
            for(int i = start; i < end; ++i){
                int num = nums[i];
                if(num > pivot){
                    swap(nums, i, ++equalTailIdx);
                    swap(nums, equalTailIdx, ++biggerTailIdx);
                }else if(num == pivot){
                    swap(nums, i, ++equalTailIdx);
                }else{
                    continue;
                }
            }
            swap(nums, ++equalTailIdx, end);
            if(equalTailIdx == k){
                return nums[k];
            }else if(equalTailIdx > k){
                end = equalTailIdx - 1;
            }else{
                start = equalTailIdx + 1;
            }
        }
        return nums[start];
    }
    
    public void wiggleSort(int[] nums) {
        int median = selectKthLargest(nums, (nums.length - 1) / 2, 0, nums.length - 1);
        int[] temp = new int[nums.length];
        for(int i = 0, j = 0, k = nums.length / 2; i < temp.length; ++i){
            temp[i] =(i % 2 == 0)? nums[k++]: nums[j++];
        }
        System.arraycopy(temp, 0, nums, 0, nums.length);
    }
 
    public static void main(String[] args){
        int[] nums = {1, 5, 1, 1, 6, 4};
        System.out.println("nums @ before: " + Arrays.toString(nums));
        Solution sol = new Solution();    
        sol.wiggleSort(nums);
        System.out.println("nums @ after: " + Arrays.toString(nums));
    }
}
