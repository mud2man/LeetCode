/* Two pointers: Time:O(n^2), Space:O(1)
 * 1. Sort input array, and have three pointers i, j, and k
 * 2. Set i from 0 to (nums.length - 2), and count the number of combinations of j and k
 * 3. Accumulate count
 */

import java.util.*;

public class Solution{
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0; i < (nums.length - 2); ++i){
            int j = i + 1;
            int k = nums.length - 1;
            int subTarget = target - nums[i];
            while(j < k){
                if((nums[j] + nums[k]) < subTarget){
                    count += (k - j);
                    ++j;
                }else{
                    --k;
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {-2, 0, 1, 3};
        int target = 2;

        sol = new Solution();    
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        System.out.println("count: " + sol.threeSumSmaller(nums, target));
    }
}
