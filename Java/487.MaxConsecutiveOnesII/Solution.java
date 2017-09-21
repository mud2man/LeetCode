/* Queue O(n)
 * 1. Use two pointers to store the latest two index of zeros
 * 3. If nums[i] == 0, update ptr0 = ptr1, ptr1 = i
 * 4. Update maxLen = max(maxLen, i - ptr0);
 *
 * ex: nums{1, 0, 1, 1, 0, 0, 1}
 * idx = 0, nums[idx] = 1, ptr0 = -1, ptr1 = -1, currLen = 1
 * idx = 1, nums[idx] = 0, ptr0 = -1, ptr1 = 1, currLen = 1 - (-1) = 2
 * idx = 2, nums[idx] = 1, ptr0 = -1, ptr1 = 1, currLen = 2 - (-1) = 3
 * idx = 3, nums[idx] = 1, ptr0 = -1, ptr1 = 1, currLen = 3 - (-1) = 4
 * idx = 4, nums[idx] = 0, ptr0 = 1, ptr1 = 4, currLen = 4 - 1 = 3
 * idx = 5, nums[idx] = 0, ptr0 = 4, ptr1 = 5, currLen = 5 - 4 = 1
 * idx = 6, nums[idx] = 1, ptr0 = 4, ptr1 = 5, currLen = 6 + 4 = 2
 */          

import java.util.*; // Stack

public class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ptr0 = -1;
        int ptr1 = -1;
        int maxLen = 1;
        
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] == 0){
                ptr0 =  ptr1;
                ptr1 = i;
            }
            maxLen = Math.max(maxLen, i - ptr0);
        }
        
        return maxLen;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 0, 1, 1, 0};
        int maxConsecutiveOnes;

        sol = new Solution();
        maxConsecutiveOnes = sol.findMaxConsecutiveOnes(nums);

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maxConsecutiveOnes: " + maxConsecutiveOnes);
    }
}
