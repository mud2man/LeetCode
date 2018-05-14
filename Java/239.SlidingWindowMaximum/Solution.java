/* Monotonous Stack: Time:O(n), Space:O(n)
 * 1. Have a monotonous stack store in decending order from head to tail, where tail is the latest visited element
 * 2. Becuase the previous element will be replaced by the current bigger element, we will kill them in "update" method
 * 3. When sliding window, we delete the haed of stack only if the out-of-window element is equal to the head 
 * 4. Because all the element at most go through the stack once. So the amortized time complexity is O(n)  
 */

import java.util.*;

public class Solution{
    private void update(LinkedList<Integer> monotonousStack, int num){
        while(!monotonousStack.isEmpty() && monotonousStack.peekLast() < num){
            monotonousStack.pollLast();
        }
        monotonousStack.add(num);
    }
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0){
            return new int[]{};
        }
        
        LinkedList<Integer> monotonousStack = new LinkedList<Integer>();
        
        for(int i = 0; i < (k - 1); ++i){
            update(monotonousStack, nums[i]);
        }
        
        int[] maximums = new int[nums.length - k + 1];
        for(int i = k - 1; i < nums.length; ++i){
            update(monotonousStack, nums[i]);
            maximums[i - k + 1] = monotonousStack.peekFirst();
            int deleteNum = nums[i - k + 1];
            if(monotonousStack.peekFirst() == deleteNum){
                monotonousStack.pollFirst();
            }
        }
        return maximums;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maximums: " + Arrays.toString(sol.maxSlidingWindow(nums, k)));
    }
}
