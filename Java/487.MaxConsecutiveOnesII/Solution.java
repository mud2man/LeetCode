/* Queue O(n)
 * 1. Use a queue to store the latest two index of zeros
 * 2. If nums[idx] == 1, accumulate current length with 1
 * 3. If nums[idx] == 0, current length is equal to (idx - the head of queue)
 * 4. In every step, compare the current length and maximum length, and select the bigger one
 *
 * ex: nums{1, 0, 1, 1, 0, 0, 1}
 * idx = 0, nums[idx] = 1, queue = {-1}, currLen = 1
 * idx = 1, nums[idx] = 0, queue = {-1, 1}, currLen = 1 - (-1) = 2
 * idx = 2, nums[idx] = 1, queue = {-1, 1}, currLen = 2 + 1 = 3
 * idx = 3, nums[idx] = 1, queue = {-1, 1}, currLen = 3 + 1 = 4
 * idx = 4, nums[idx] = 0, queue = {1, 4}, currLen = 4 - 1 = 3
 * idx = 5, nums[idx] = 0, queue = {4, 5}, currLen = 5 - 4 = 1
 * idx = 6, nums[idx] = 1, queue = {4, 5}, currLen = 1 + 1 = 1
 */          

import java.util.*; // Stack

public class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int idx, maxLen, currLen;
        Queue<Integer> queue;
        
        queue = new LinkedList<Integer>();
        maxLen = 0;
        currLen = 0;
        queue.offer(-1);
        
        for(idx = 0; idx < nums.length; idx++){
            if(nums[idx] != 0){
                currLen++;
            }
            else{
                if(queue.size() < 2){
                    queue.offer(idx);
                    currLen = idx - queue.peek();
                }
                else{
                    queue.poll();
                    queue.offer(idx);
                    currLen = idx - queue.peek();
                }
            }
            maxLen = Math.max(maxLen, currLen);
        }
        
        return maxLen;
    } 

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 0, 1, 1, 0};
        int maxConsecutiveOnes;

        sol = new Solution();
        maxConsecutiveOnes = sol.findMaxConsecutiveOnes(nums);

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maxConsecutiveOnes: " + maxConsecutiveOnes);
    }
}
