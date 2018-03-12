/* Sliding window: Time:O(n), Space:O(1)
 * 1. Have an array "range" to store the shortest sorted window
 * 2. The left index will keep the same or lefter if an element violate the ascending order
 * 3. Keep traverse right if nums[i] >= max
 * 4. Otherwise, update left index by invoking "findLeftIndex", and update right index "range[1]" with current index i;
 */

import java.util.*;

public class Solution{
    private void findLeftIndex(int[] nums, int[] range, int target){
        while(range[0] >= 0 && nums[range[0]] > target){
            range[0]--;
        }
    }
    
    public int findUnsortedSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int[] range = new int[2];
        boolean found = false;
        range[0] = 0;
        range[1] = 0;
        
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] < max){
                found = true;
                range[1] = i;
                findLeftIndex(nums, range, nums[i]);
            }
            else{
                max = nums[i];
                if(!found){
                    range[0] = i;
                    range[1] = i; 
                }
            }
        }
        
        return range[1] - range[0];
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {2, 6, 4, 8, 10, 9, 15};

        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("shortest length: " + sol.findUnsortedSubarray(nums));
    }
}
