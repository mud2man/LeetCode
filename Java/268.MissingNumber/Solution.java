/* : Time:O(n), Space:O(1)
 * 1. Iterate all element of nums, and call visit
 * 2. In visit, nums[value] = value, and call visit(nextValue, nums) if nums[value] != value
 */

import java.util.*; // Stack

public class Solution {
    private void visit(int value, int[] nums){
        if(value < 0 || value >= nums.length || nums[value] == value){
            return;
        }

        int nextValue = nums[value];
        nums[value] = value;
        visit(nextValue, nums);
    }
    
    public int missingNumber(int[] nums) {
        for(int i = 0; i < nums.length; ++i){
            visit(nums[i], nums); 
        }
        
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] != i){
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args){
        int[] nums = {0, 1, 3};
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("missing number: " + sol.missingNumber(nums));
    }
}
