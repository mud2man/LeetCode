/* Binary search: O(logn)
 * 1. Find the case: nums[lb] <= nums[ub])
 *
 * ex: 4 5 6 7 0 1 2
 *           
 *            * 
 * 1. Case1:  | *   , move right
 *            |/
 *            
 *              *
 *             /|
 *            * | *
 * 2. Case2:    |/ , move right
 *
 *             
 *            /|   *  
 * 3. Case3: * |  / , move left
 *             | *
 *             |/
 */

import java.util.*;

public class Solution {
    public int findMin(int[] nums) {
        int size = nums.length;
        int ub = size - 1;
        int lb = 0;
        while(lb < ub){
            int mid = (ub + lb) / 2;
            if(nums[lb] <= nums[ub]){
                return nums[lb];
            }
            else if(nums[mid] >= nums[lb]){
                lb = mid + 1;
            }
            else{
                ub = mid;
            }
        }
        return nums[lb];
    }

    public static void main(String[] args){
        Solution sol;
        int maximum;
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        sol = new Solution();
        
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("minimum:" + sol.findMin(nums));
    }
}
