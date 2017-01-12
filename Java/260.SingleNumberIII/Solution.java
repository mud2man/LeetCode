/* Xor bit manupulation: O(n)
 * 1. Xor all the numbers
 * 2. Use diff ^ -diff to get the last set bit
 * 3. Use this last set bit to divide numbers into two groups, one has the single number a, and the other one has single number b
 * 4. Xor all the number based on the last set bit, because the pair number will appear in the same group
 * 5. Therefore, pairs in each group will be zeros after xor
 */

import java.util.*; // Stack


public class Solution {
    public int[] singleNumber(int[] nums) {
        int[] singles;
        int diff;
        
        singles = new int[2];
        diff = 0;
        for(int num: nums){
            diff ^= num;
        }
        
        // get the last set bit
        diff = diff & -diff;
        
        //dispatch to two group, one has the last set bit, and the other has not 
        for(int num: nums){
            if((diff & num) == 0)
                singles[0] ^= num;
            else
                singles[1] ^= num;
        }
        return singles;
    }

    public static void main(String[] args)
    {
        Solution sol;
        boolean isValid;
        int[] nums = {1, 2, 1, 3, 2, 5};
        int[] singles;

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));

        singles = sol.singleNumber(nums);
        
        System.out.println("singles: " + Arrays.toString(singles));
	}
}
