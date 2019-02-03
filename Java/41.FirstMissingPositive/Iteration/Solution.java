/* Time:O(n), Space:O(1)
 * 1. Because the first missing positive integer must between 1 and nums.length, we can use the input array "nums" to track
 * 2. Replace nums[num - 1] with num, if nums[num - 1] != num in the method "swap"
 * 3. Repeat step 2 until nums[num - 1] == num
 */         

import java.util.*;

public class Solution {
    private void swap(int[] nums, int num){
        while(num > 0 && num <= nums.length && nums[num - 1] != num){
            int prevNum = nums[num - 1];
            nums[num - 1] = num;
            num = prevNum;
        }
    }
    
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; ++i){
            swap(nums, nums[i]);
        }
        
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return (nums.length + 1);
    }

    public static void main(String[] args){
        Solution sol; 
        int[] nums = {3, 4, -1, 1};
        sol = new Solution();
        
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("the first missing positive integer: " + sol.firstMissingPositive(nums));
    }
}
