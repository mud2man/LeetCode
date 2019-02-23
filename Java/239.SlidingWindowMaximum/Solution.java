/* Monotonous Queue: Time:O(n), Space:O(k)
 * 1. Have a monotonous queue store in increasing order from head to tail in the window, where front is the latest visited element
 * 2. Becuase the previous element will be replaced by the current bigger element, we will kill them in "insert" method
 * 3. When sliding window, we delete the tail only if the out-of-window element is equal to it, 
 *    because it must be tail if its (k - 1) sucessors is not able to replace it in the window
 * 4. Because all the element at most go through the queue once. So the amortized time complexity is O(n)  
 */

import java.util.*;

public class Solution{
    private void insert(Deque<Integer> monotonousQ, int addNum){
        while(!monotonousQ.isEmpty() && addNum > monotonousQ.peekFirst()){
            monotonousQ.pollFirst();
        }
        monotonousQ.addFirst(addNum);
    }
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int[0];
        }
        
        Deque<Integer> monotonousQ = new LinkedList<>();
        for(int i = 0; i < k; ++i){
            insert(monotonousQ, nums[i]);
        }
        
        int[] max = new int[nums.length - k + 1];
        for(int i = k; i < nums.length; ++i){
            max[i - k] = monotonousQ.peekLast();
            int addNum = nums[i];
            int deleteNum = nums[i - k];
            if(monotonousQ.peekLast() == deleteNum){
                monotonousQ.pollLast();
            }
            insert(monotonousQ, addNum);
        }
        max[max.length - 1] = monotonousQ.peekLast();
        return max;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maximums: " + Arrays.toString(sol.maxSlidingWindow(nums, k)));
    }
}
