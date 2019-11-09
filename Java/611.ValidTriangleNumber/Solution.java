/* Greedy: Time:O(n^2), Space:O(1).
 * 1. Sort numbers, and fixed k, accumulate count by (j - i) if nums[k] < (nums[i] + nums[j]) and decrease j
 * 2. Otherwise, increase i
 */

import java.util.*;

public class Solution{
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        
        int count = 0;
        for(int k = nums.length - 1; k >= 2; --k){
            int i = 0;
            int j = k - 1;
            while(i < j){
                if(nums[k] < (nums[i] + nums[j])){
                    count += (j - i);
                    j--;
                }else{
                    i++;
                }
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        Integer nums[] = {2, 2, 3, 4};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("triangle number: " + sol.triangleNumber(nums));
    }
}
