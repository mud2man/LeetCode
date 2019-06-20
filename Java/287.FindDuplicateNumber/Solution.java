/* Floyd's Tortoise: Time:O(n), Space:O(1). It's the same as LeetCode#142
 *
 *               r 
 *            /    \
 *   *---s---*-m-*--*
 * 
 * 1. Since pointer 0 will not be pointered, so 0 can be the starting point. If there is np cycle in the path, there is no duplicate which is contradicted.
 * 2. Let's start at 0, and next step of ptr is nums[ptr]. We can reformulate this problem to LeetCode#142
 * 3. We can prove that fast pointer always catch up slow pointer, assume it happens on s + m
 * 4. When (fastIdx == slowIdx), s + m + nr = 2s + 2m => nr - m = s
 * 5. Hence, the pointer starting from head take s steps to meet the one starting from (s + m) which takes (nr - m) = s steps
 * 6. And the meeting point is the starting pointer of the cycle
 */

import java.util.*;

public class Solution{
    public int findDuplicate(int[] nums) {
        int slowIdx = 0;
        int fastIdx = 0;
        do{
            slowIdx = nums[slowIdx];
            fastIdx = nums[nums[fastIdx]];
        }while(slowIdx != fastIdx);
        
        int intersectionIdx = slowIdx;
        int ptr0 = 0;
        int ptr1 = intersectionIdx;
        while(ptr0 != ptr1){
            ptr0 = nums[ptr0];
            ptr1 = nums[ptr1];
        }
        return ptr0;
    }
  
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 3, 4, 2, 2};
        sol = new Solution();
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("duplicate: " + sol.findDuplicate(nums));
    }
}
