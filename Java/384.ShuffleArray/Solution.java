/* Time:O(n), Space:O(n)
 * 1. Have array "buffer" to store the current permutation, "original" to store the original permutation
 * 2. When shuffle, random pick the buffer[index], where idx between 0 and length, and swap buffer[index] with buffer[length - 1]
 * 3. After that, decrease length 1, so we pick idx between 0 and (length - 1) nex time
 */

import java.util.*;

public class Solution {
    int[] buffer;
    int[] shuffled;
    int[] original;
    
    public Solution(int[] nums) {
        original = nums;
        buffer = nums.clone();
        shuffled = new int[nums.length];
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        buffer = original.clone();
        return buffer;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random rand = new Random();
        int length = buffer.length;
        for(int i = 0; i < buffer.length; ++i){
            int idx = rand.nextInt(length);
            shuffled[i] = buffer[idx];
            int temp = buffer[idx];
            buffer[idx] = buffer[length - 1];
            buffer[--length] = temp;
        }
        return shuffled;
    }
 
    public static void main(String[] args){
        int[] nums = {1, 2, 3};
        Solution sol = new Solution(nums);
        System.out.println("nums: " + Arrays.toString(nums));

        System.out.println("shuffle: " + Arrays.toString(sol.shuffle()));
        System.out.println("reset: " + Arrays.toString(sol.reset()));
        System.out.println("shuffle: " + Arrays.toString(sol.shuffle()));

    }
}
