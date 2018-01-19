/* Recursive: Time:O(n), Space:O(1)
 * 1. Because the first missing positive integer must between 1 and nums.length, we can use the input array "nums" to track
 * 2. Replace nums[num - 1] with num, if nums[num - 1] != num in the method "visit"
 * 3. Then call visit recursively
 * 4. Because every num in nums will be replaced at most once, the time complexity is O(n)
 * 5. Traverse nums again, return i + 1 if (nums[i] != (i + 1))
 */         

import java.util.*;

public class Solution {
    private void visit(int[] nums, int num){
        if(num >= 1 && num <= nums.length){
            if(nums[num - 1] != num){
                int next = nums[num - 1];
                nums[num - 1] = num;
                visit(nums, next);
            }
        }   
    }
    
    public int firstMissingPositive(int[] nums) {
        for(int num: nums){
            visit(nums, num);
        }
        
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] != (i + 1)){
                return (i + 1);
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
