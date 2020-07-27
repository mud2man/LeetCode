/* Sort & Two Pointers: Time:O(nlogn), Space:O(n)
 * 1. The number of sequence given the min and max is the same after sorting. In other words, sorting doesn't affect the answer
 * 2. Have two pointers "start" and "end", if nums[start] + nums[end] > target, we move end to left
 * 3. Otherwise, All combinations of {nums[start], ... nums[end]}, {nums[start], ... nums[end - 1]}, ..., {nums[start], ... nums[start]} count
 * 4. The number of combinations of above is 2^(end - start).
 * 5. The we shift start to right
 */     

import java.util.*; // Stack

public class Solution {
    public int numSubseq(int[] nums, int target) {
        int base = 1_000_000_007;
        int[] powOfTwo = new int[nums.length];
        powOfTwo[0] = 1;
        for(int i = 1; i < powOfTwo.length; ++i){
            powOfTwo[i] = (powOfTwo[i - 1] * 2) % base;
        }
        
        Arrays.sort(nums);
        int count = 0;
        int end = nums.length - 1;
        int start = 0;
        while(start <= end){
            int min = nums[start];
            int max = nums[end];
            if(min + max > target){
                end--;
            }else{
                int length = end - start;
                count = (count + powOfTwo[length]) % base;
                start++;
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {3, 5, 6, 7};
        int target = 9;
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("target:" + target);
        System.out.println("subsequences#:" + sol.numSubseq(nums, target));
    }
}
