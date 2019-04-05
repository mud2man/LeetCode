/* Greedy: Time:O(n), Space:O(1)
 * 1. Have "currLimit" to record the current limit
 * 2. If current < i, which means i is never be reached. So target cannot be reached
 * 3. Update currLimit every time
 */

import java.util.*;

//Definition for singly-linked list.
public class Solution{
    public boolean canJump(int[] nums) {
        int target = nums.length - 1;
        int currLimit = 0;
        for(int i = 0; i <= target; ++i){
            if(currLimit < i){
                return false;
            }
            currLimit = Math.max(nums[i] + i, currLimit);
            if(currLimit >= target){
                break;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("can jump:" + sol.canJump(nums));
    }
}
