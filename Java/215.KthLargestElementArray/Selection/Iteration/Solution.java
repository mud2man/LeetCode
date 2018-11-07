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
 
    private int partition(int[] nums, int lb, int hb){
        int target = nums[hb];
        int pointer = lb -1;
        
        for(int i = lb; i < hb; i++){
            if(nums[i] > target){
                int tmp = nums[i];
                nums[i] = nums[++pointer];
                nums[pointer] = tmp;
            }
        }
        
        nums[hb] = nums[++pointer];
        nums[pointer] = target;
        return pointer;
    }
    
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        int lb = 0;
        int hb = nums.length - 1;
        k--;
        while(lb < hb){
            int mid = partition(nums, lb, hb);
            if(mid == k){
                return nums[mid];
            }
            else if(mid > k){
                hb = mid - 1; 
            }
            else{
                lb = mid + 1;
            }
        }
        return nums[lb];
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("Kth largest: " + sol.findKthLargest(nums, k));
    }
}
