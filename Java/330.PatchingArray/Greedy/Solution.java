/* Dynamic programming: Time:O(logn + m), Space:O(1), where m is nums#
 * 1. We start from lastNumNeedFilled = 1
 * 2. If lastNumNeedFilled < nums[idx], we can say choosing num = lastNumNeedFilled to be filled is the best solution. There are 2 reasons.
 *    3.1 Any num > lastNumNeedFilled is impossible to fill num = "lastNumNeedFilled"
 *    3.2 Any num < lastNumNeedFilled cover less number than num = lastNumNeedFilled
 * 4. Otherwise, we extend the coverage by nums[idx], and update lastNumNeedFilled as (lastNumNeedFilled + nums[idx]). Then shift idx right 
 */

import java.util.*;

public class Solution{
    public static int minPatches(int[] nums, int n) {
        int idx = 0;
        long lastNumNeedFilled = 1;
        int count = 0;
        while(lastNumNeedFilled <= n){
            if(idx >= nums.length || lastNumNeedFilled < nums[idx]){
                count++;
                lastNumNeedFilled = 2 * lastNumNeedFilled; 
            }else{
                lastNumNeedFilled = lastNumNeedFilled + nums[idx++];
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        int[] nums = {1, 3};
        int n = 6;
        System.out.println("nums: " + Arrays.toString(nums));;
        System.out.println("n: " + n);
        System.out.println("patch#: " + minPatches(nums, n));
    }
}
