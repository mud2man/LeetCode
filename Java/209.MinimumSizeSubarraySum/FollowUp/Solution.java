/* Binary search: Time:O(nlogn), Space: O(1)
 * 1. Let nums[i] = sum(nums[0], nums[1], ..., nums[i])
 * 2. Use binary search to find the biggest index i' s.t. sum(nums[i'],nums[i' + 1], ...,nums[i]) >= s
 */         

import java.util.*;

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int min = nums.length + 1;
        
        for(int i = 0; i < nums.length; ++i){
            sum += nums[i];
            nums[i] = sum;
            
            if(sum < s){
                continue;
            }
            
            int lb = 0;
            int hb = i;
            while(lb <= hb){
                int mid = (lb + hb) / 2;
                int left = nums[mid];
                int remain = sum - left;
                if(remain < s){
                    hb = mid - 1;
                }
                else{
                    lb = mid + 1;
                }
            }
            
            min = Math.min(min, i - hb);
        }
        return (min == nums.length + 1)? 0: min;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {2, 3, 1, 2, 4, 3};
        int s = 7;
        
        sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("s:" + s);
        System.out.println("minimum length: " + sol.minSubArrayLen(s, nums));
    }
}
