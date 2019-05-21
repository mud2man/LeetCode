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
        int l = 0;
        int r = nums.length - 1;
        k = k - 1;
        while(l < r){
            int pivot = nums[r];
            int i = l - 1;
            for(int j = l; j < r; ++j){
                if(nums[j] > pivot){
                    int tmp = nums[j];
                    nums[j] = nums[++i];
                    nums[i] = tmp;
                }
            }
            nums[r] = nums[++i];
            nums[i] = pivot;
            
            if(i == k){
                return nums[i];
            }else if(i > k){
                r = i - 1;
            }else{
                l = i + 1;
            }
        }
        return nums[l];
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
