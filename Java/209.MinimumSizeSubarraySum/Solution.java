/* Two pointers: Time:O(n), Space: O(1)
 * 1. Have two pointers ptr0 and ptr1 to represent the window, where ptr0 is inclusive and ptr1 is exclusive
 * 2. Shift ptr1 roght until sum >= s, and ptr0 untiol sum < s
 * 3. Check if the sum of [ptr0 - 1, ptr0, ptr0 + 1, .... ptr1 - 1] >= sum, then update minLength
 */         

import java.util.*;

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        int h = -1;
        
        for(int t = 0; t < nums.length; ++t){
            sum += nums[t];
            while(sum >= s && h < t){
                minLength = Math.min(t - h, minLength);
                sum -= nums[++h];
            }
        }
        
        return (minLength == Integer.MAX_VALUE)? 0: minLength; 
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
