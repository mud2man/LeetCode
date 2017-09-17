/* Qoickselect: O(n)
 * 1. It's can be proven that there is only one local minimum. And it is also the global minimum. 
 * 2. The minimum is the median. 
 * 3. Use the selecting method in quick sort to find the medium
 */

import java.util.*; // Stack

public class Solution {
    private int quickSelect(int[] nums, int startIdx, int endIdx, int targetIdx){
        int target = nums[endIdx];
        int smallerTailIdx = startIdx - 1;
        for(int idx = startIdx; idx < endIdx; ++idx){
            if(nums[idx] <= target){
                int tmp = nums[++smallerTailIdx];
                nums[smallerTailIdx] = nums[idx];
                nums[idx] = tmp;
            }
        }
        nums[endIdx] = nums[++smallerTailIdx];
        nums[smallerTailIdx] = target;
        
        int lessThanCount = smallerTailIdx - startIdx;
        if((lessThanCount + 1)== targetIdx){
            return target;
        }
        else if((lessThanCount + 1) > targetIdx){
            return quickSelect(nums, startIdx, smallerTailIdx - 1, targetIdx);
        }
        else{
            return quickSelect(nums, smallerTailIdx + 1, endIdx, targetIdx - lessThanCount - 1);
        }
    }
    
    public int minMoves2(int[] nums) {
        int size = nums.length;
        int mediam = quickSelect(nums, 0, size - 1, (size + 1) / 2);
        
        int moves = 0;
        for(int num: nums){
            moves = moves + Math.abs(mediam - num);
        }
        
        return moves;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 2, 3};
        
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("minimum moves#: " + sol.minMoves2(nums));
    }
}
