/* Monotonous queue: Time:O(n), Space:O(k)
 * 1. Keep the monotonous queue with element = {i, max score reaching i}
 * 2. Poll queue until monotonousQueue.peekLast()[1] > tail[1]
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int maxResult(int[] nums, int k) {
        Deque<int[]> monotonousQueue = new LinkedList<>();
        for(int i = 0; i < nums.length; i++){
            if(monotonousQueue.isEmpty()){
                monotonousQueue.add(new int[]{i, nums[i]});
            }else{
                int[] tail = {i, nums[i] + monotonousQueue.peekFirst()[1]};
                if(nums[i] >= 0){
                    monotonousQueue.clear();
                }else{
                    if(i - monotonousQueue.peekFirst()[0] == k){
                        monotonousQueue.pollFirst();
                    }
                    while(!monotonousQueue.isEmpty() && monotonousQueue.peekLast()[1] <= tail[1]){
                        monotonousQueue.pollLast();
                    }
                }
                monotonousQueue.add(tail);
            }
        }
        return monotonousQueue.peekLast()[1];
    }
  
    public static void main(String[] args){
        int[] nums = {1, -1, -2, 4, -7, 3};
        int k = 2;
        Solution sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums) + ", k:" + k);
        System.out.println("max score:" + sol.maxResult(nums, k));
    }
}
