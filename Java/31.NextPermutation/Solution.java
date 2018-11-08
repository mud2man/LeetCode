/* Traversal: O(n)
 * 1. Traverse nums from the last index until nums[i] > nums[i - 1]
 * 2. Since nums[i, size -1] is decending, we don't need to call sort to make it ascend
 * 3. Reverse nums[i, size - 1] to make it ascend
 * 4. And select the smallest one but greater than nums[i - 1] to swap with nums[i - 1]
 */

import java.util.*;

public class Solution{
    public void reverse(int[] nums, int start){
        int i = start;
        int j = nums.length - 1;
        while(i < j){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            ++i;
            --j;
        }
    }
    
    public void nextPermutation(int[] nums) {
        int start = 0;
        for(int i = nums.length - 1; i > 0; --i){
            if(nums[i] > nums[i - 1]){
                start = i;
                for(int j = i; j <= nums.length; ++j){
                    if(j == nums.length || nums[j] <= nums[i - 1]){
                        int tmp = nums[i - 1];
                        nums[i - 1] = nums[j - 1];
                        nums[j - 1] = tmp;
                        break;
                    }
                }
                break;
            }
        }
        reverse(nums, start);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {2, 3, 1};
        System.out.println("before permutation: " + Arrays.toString(nums));
        sol.nextPermutation(nums);
        System.out.println("after permutation: " + Arrays.toString(nums));
	}
}
