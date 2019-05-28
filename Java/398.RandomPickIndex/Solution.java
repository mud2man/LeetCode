/* Reservoir Sampling: O(n)
 * 1. When pick, replace the next index with the probability of (1 / count), where count is the number of appearing time
 */

import java.util.*; // Stack

public class Solution {
    int[] nums;
    Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }
    
    public int pick(int target) {
        int result = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target){
                continue;
            }else{
                result = (random.nextInt(++count) == 0)? i: result;
            }
        }
        return result;
    }
 
    public static void main(String[] args){
        int[] nums = {1, 2, 3, 3, 3};
        int target = 3;
        Solution sol = new Solution(nums);
        for(int i = 0; i < 10; ++i){
            System.out.println("pick(" + target + "): " + sol.pick(target));
        }
    }
}
