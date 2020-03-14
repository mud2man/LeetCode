/* Monotonous stack:Time:O(n), Space:O(n).
 * 1. Have 2 arraris "min" and "max", min[i] = min(nums[0], nums[1], ... nums[i]), max[i] = max(nums[0], nums[1], ... nums[i])
 * 2. Collect monotonous strict decreasing queue from right end of nums
 * 3. Poll stack as long as max[i] <= monotonousStack.peekFirst(), sicne we don't need the number which > leftMax
 * 4. We check if 132 pattern by nums[i] > monotonousStack.peekFirst() && monotonousStack.peekFirst() > min[i - 1]
 */

import java.util.*;

public class Solution {
    public boolean find132pattern(int[] nums) {
        int[] min = new int[nums.length];
        int[] max = new int[nums.length];
        for(int i = 0; i < nums.length; ++i){
            int leftMin =(i == 0)? Integer.MAX_VALUE: min[i - 1];
            int leftMax =(i == 0)? Integer.MIN_VALUE: max[i - 1];
            min[i] = Math.min(nums[i], leftMin);
            max[i] = Math.max(nums[i], leftMax);
        }
        
        Deque<Integer> monotonousStack = new LinkedList<>();
        monotonousStack.add(Integer.MIN_VALUE);
        for(int i = nums.length - 1; i > 0; --i){
            int leftMax = max[i];
            while(!monotonousStack.isEmpty() && leftMax <= monotonousStack.peekFirst()){
                monotonousStack.pollFirst();
            }
            if(!monotonousStack.isEmpty() && nums[i] > monotonousStack.peekFirst() && monotonousStack.peekFirst() > min[i - 1]){
                return true;
            }
            if(monotonousStack.isEmpty() || nums[i] > monotonousStack.peekFirst()){
                monotonousStack.addFirst(nums[i]);
            }
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
