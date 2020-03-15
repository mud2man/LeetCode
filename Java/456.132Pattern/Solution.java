/* Stack:Time:O(n), Space:O(n).
 * 1. Have an arrary "min", where  min[i] = min(nums[0], nums[1], ... nums[i])
 * 2. Maintain a statck of number which is > min[i]. Note: min[i] >= min[j] when i > j
 * 3. Poll stack as long as min[i] <= monotonousStack.peekFirst(). And the top of stack is > min[i]
 * 4. We check if 132 pattern by nums[i] > monotonousStack.peekFirst() && monotonousStack.peekFirst() > min[i - 1]
 */

import java.util.*;

public class Solution {
   public boolean find132pattern(int[] nums) {
        int[] min = new int[nums.length];
        for(int i = 0; i < nums.length; ++i){
            int leftMin =(i == 0)? Integer.MAX_VALUE: min[i - 1];
            min[i] = Math.min(nums[i], leftMin);
        }
        
        Deque<Integer> monotonousStack = new LinkedList<>();
        monotonousStack.add(Integer.MIN_VALUE);
        for(int i = nums.length - 1; i > 0; --i){
            int leftMin = min[i - 1];
            while(!monotonousStack.isEmpty() && leftMin >= monotonousStack.peekFirst()){
                monotonousStack.pollFirst();
            }
            if(!monotonousStack.isEmpty() && nums[i] > monotonousStack.peekFirst() && monotonousStack.peekFirst() > leftMin){
                return true;
            }
            monotonousStack.addFirst(nums[i]);
        }
        return false;
    }
  
    public static void main(String[] args){
        int[] nums = {3, 1, 4, 2};
        Solution sol = new Solution();
        System.out.println("num:" + Arrays.toString(nums));
        System.out.println("132 pattern found:" + sol.find132pattern(nums));
    }
}
