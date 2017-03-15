/* Greedy: NP-complete
 * 1. Sort the input nums by accending order
 * 2. Select the longest match first, and check if it can form a valid edge
 * 3. Repeat step2 four times
 *
 * ex: nums = {1, 1, 2, 2, 2}
 * time[0]: edge[0] = {2}, edge[1] = {}, edge[2] = {}, edge[3] = {}
 * time[1]: edge[0] = {2}, edge[1] = {2}, edge[2] = {}, edge[3] = {}
 * time[2]: edge[0] = {2}, edge[1] = {2}, edge[2] = {2}, edge[3] = {}
 * time[3]: edge[0] = {2}, edge[1] = {2}, edge[2] = {2}, edge[3] = {1,}
 * time[4]: edge[0] = {2}, edge[1] = {2}, edge[2] = {2}, edge[3] = {1, 1}
 */

import java.util.*; // Stack

public class Solution {
    public boolean backTracker(int[] tagList, int[] nums, int residure, int tailIdx){
        int i;
        
        if(residure == 0){
            return true;
        }
        
        for(i = tailIdx; i >= 0; --i){
            if(tagList[i] == 0 && residure >= nums[i]){
                tagList[i] = 1;
                if(backTracker(tagList, nums, residure - nums[i], i - 1) == true){
                    return true;   
                }
                tagList[i] = 0;
            }
        }
        return false;
    }
    
    public boolean makesquare(int[] nums) {
        int[] tagList;
        int len;
        
        len = 0;
        tagList = new int[nums.length];
        Arrays.sort(nums);
        
        for(int num: nums){
            len += num;   
        }
        
        if((len % 4) != 0 || len == 0){
            return false;
        }
        len = len / 4;
        
        for(int i = 0; i < 4; ++i){
            if(backTracker(tagList, nums, len, nums.length - 1) == false){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 1, 2, 2, 2};
		
		sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("isSquare: " + sol.makesquare(nums));
    }
}
