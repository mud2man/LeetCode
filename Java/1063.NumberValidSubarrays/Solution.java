/* Monotonic stack: Time:O(n), Space:O(n)
 * 1. The stack store leftmost elements of the subarray in non-decreasing order
 * 2. Traverse "nums" and pop out the violated left most element, because they will never be used anymore
 * 3. Accumulate count by stack.size
 * 
 * ex: nums: {1, 4, 2, 5, 3}
 * time[0]: count = 1, stack = {1}
 * time[1]: count = 3, stack = {1, 4}
 * time[2]: count = 5, stack = {1, 2}
 * time[3]: count = 8, stack = {1, 2, 5}
 * time[4]: count = 11, stack = {1, 2, 3}
 */

import java.util.*; 
import java.math.*;

public class Solution {
    public int validSubarrays(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int count = 0;
        for(int num: nums){
            while(!stack.isEmpty() && stack.peekLast() > num){
                stack.pollLast();
            }
            stack.add(num);
            count += stack.size();
        }
        return count;
    }
  
    public static void main(String[] args){
        int[] nums = {1, 4, 2, 5, 3};
        Solution sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("valid subarrays#: " + sol.validSubarrays(nums));
    }
}
