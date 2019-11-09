/* Selection algorithm: Time:O(n) amortized, Space:O(1)
 * 1. Partition: select nums[hb] as target, and find its position, s.t. all the elements before "pointer" is bigger than target
 * 2. Repeat step1 until lb == hb
 */         

import java.util.*;

public class Solution {
    private void shuffle(int[] nums){
        Random rand = new Random();
        int range = nums.length;
        for(int i = nums.length - 1; i >= 0; --i){
            int idx = rand.nextInt(range--);
            int tmp = nums[i];
            nums[i] = nums[idx];
            nums[idx] = tmp;
        }
    }
    
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int pivot = nums[end];
            int tail = start - 1;
            for(int i = start; i < end; ++i){
                if(nums[i] >= pivot){
                    int tmp = nums[tail + 1];
                    nums[++tail] = nums[i];
                    nums[i] = tmp;
                }
            }
            int tmp = nums[tail + 1];
            nums[++tail] = pivot;
            nums[end] = tmp;
            if(tail == k - 1){
                return pivot;
            }else if(tail < k - 1){
                start = tail + 1;
            }else{
                end = tail - 1;
            }
        }
        return -1;
    }
  
    public static void main(String[] args){
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("Kth largest: " + sol.findKthLargest(nums, k));
    }
}
