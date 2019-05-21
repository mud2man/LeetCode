/* Selection algorithm: Time:O(n) amortized, Space:O(1)
 * 1. Use select to find the k-th element
 * 2. If ptr - lb == k - 1, return pivot;
 * 3. If k-th element on left half, call select(lb, ptr - 1, nums, k)
 * 3. If k-th element on right half, call select(ptr + 1, hb, nums, k - (ptr - lb + 1))
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

    private int select(int lb, int hb, int[] nums, int k){
        int pivot = nums[hb];
        int ptr = lb - 1;
        for(int i = lb; i < hb; ++i){
            if(nums[i] > pivot){
                int temp = nums[++ptr];
                nums[ptr] = nums[i];
                nums[i] = temp;
            }
        }
        nums[hb] = nums[++ptr];
        nums[ptr] = pivot;

        if(ptr - lb == k - 1){
            return pivot;
        }
        else if((ptr - lb + 1) > k){
            return select(lb, ptr - 1, nums, k);
        }
        else{
            return select(ptr + 1, hb, nums, k - (ptr - lb + 1));
        }
    }
    
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        return select(0, nums.length - 1, nums, k);
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
