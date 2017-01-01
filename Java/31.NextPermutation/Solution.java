/* Traversal: O(n)
 * 1. Traverse nums from the last index until nums[i] > nums[i - 1]
 * 2. Since nums[i, size -1] is decending, we don't need to call sort to make it ascend
 * 3. Reverse nums[i, size - 1] to make it ascend
 * 4. And select the smallest one but greater than nums[i - 1] to swap with nums[i - 1]
 */

import java.util.*;

public class Solution{
    public void reverse(int[] nums, int fromIdx, int toIdx){
        int tmp;
        
        while(fromIdx < toIdx){
            tmp = nums[fromIdx];
            nums[fromIdx] = nums[toIdx];
            nums[toIdx] = tmp;
            fromIdx++;
            toIdx--;
        }
    }
    
    public void nextPermutation(int[] nums) {
        int lowIdx;
        int highIdx;
        int size;
        int tmp;
        
        size = nums.length;
        
        //permutation, if decending gap found
        for(lowIdx = size - 1; lowIdx >= 0; --lowIdx){
            if((lowIdx > 0) && (nums[lowIdx] > nums[lowIdx - 1])){
                reverse(nums, lowIdx, size - 1);
                for(highIdx = lowIdx; highIdx < size; ++highIdx){
                    if(nums[highIdx] > nums[lowIdx - 1]){
                        tmp = nums[lowIdx - 1];
                        nums[lowIdx - 1] = nums[highIdx];
                        nums[highIdx] = tmp;
                        return;
                    }
                }
            }
        }
    
        //reverse nums, because no decending gap found
        reverse(nums, 0, size - 1);
    }

    public static void main(String[] args){
		Solution sol;
		int[] nums = {2, 3, 1};
		
		sol = new Solution();
		
        System.out.println("before permutation: " + Arrays.toString(nums));
		sol.nextPermutation(nums);
        System.out.println("after permutation: " + Arrays.toString(nums));
	}
}
