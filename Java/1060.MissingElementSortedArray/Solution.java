/* Binary search: Time:O(logn), Space:O(1)
 * 1. Have lb a,d hb to narrow down the solution
 * 2. Get the missing count between nums[0] and nums[mid] = (nums[mid] - nums[0] + 1) - (mid + 1)
 * 3. Shift lb to mid + 1 if missingCount < k, otherwise hb = mid - 1
 * 4. Get the final missing count (nums[hb] - nums[0] + 1) - (hb + 1), and return nums[hb] + k - missingCount
 */

import java.util.*; // Stack

public class Solution {
    public int missingElement(int[] nums, int k) {
        int missingCount = (nums[nums.length - 1] - nums[0] + 1) - nums.length;
        if(missingCount < k){
            return nums[nums.length - 1] + k - missingCount;
        }
        
        int lb = 0;
        int hb = nums.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            missingCount = (nums[mid] - nums[0] + 1) - (mid + 1);
            if(missingCount < k){
                lb = mid + 1;
            }else{
                hb = mid - 1;
            }
        }
        missingCount = (nums[hb] - nums[0] + 1) - (hb + 1);
        int kMissNumber = nums[hb] + k - missingCount;
        return kMissNumber;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {4, 7, 9, 10};
        int k = 1;
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("k:" + k);
        System.out.println("missing element:" + sol.missingElement(nums, k));
    }
}
