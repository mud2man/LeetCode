/* Time:O(n), Space:O(n)
 * 1. Have array "shuffled" to store the current permutation, "original" to store the original permutation
 * 2. When shuffle, random pick the shuffled[index], where idx between 0 and i, and swap shuffled[index] with shuffled[i]
 */

import java.util.*;

public class Solution {
    int[] shuffled;
    int[] original;
    
    public Solution(int[] nums) {
        original = nums;
        shuffled = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random rand = new Random();
        for(int i = shuffled.length - 1; i >= 0; --i){
            int idx = rand.nextInt(i + 1);
            int temp = shuffled[i];
            shuffled[i] = shuffled[idx];
            shuffled[idx] = temp;
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
