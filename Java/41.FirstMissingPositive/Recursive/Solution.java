/* Recursive: Time:O(n), Space:O(1)
 * 1. Because the first missing positive integer must between 1 and nums.length, we can use the input array "nums" to track
 * 2. Replace nums[num - 1] with num, if nums[num - 1] != num in the method "dfs"
 * 3. Then call visit recursively
 * 4. Because every num in nums will be replaced at most once, the time complexity is O(n)
 * 5. Traverse nums again, return i + 1 if (nums[i] != (i + 1))
 */         

import java.util.*;

public class Solution {
    private void dfs(int[] nums, int num){
        if(num < 1 || num > nums.length || nums[num - 1] == num){
            return;
        }
        int prevNum = nums[num - 1];
        nums[num - 1] = num;
        dfs(nums, prevNum);
    }
    
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; ++i){
            dfs(nums, nums[i]);
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
