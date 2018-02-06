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
        int ptr1 = 0;
        int ptr0 = 0;
        
        while(ptr1 < nums.length){
            while(ptr1 < nums.length && sum < s){
                sum += nums[ptr1++];
            }
            
            while(ptr0 < ptr1 && sum >= s){
                sum -= nums[ptr0++];
            }
            
            if(ptr0 > 0){
                if((sum + nums[ptr0 - 1]) >= sum){
                    minLength = Math.min(minLength, ptr1 - ptr0 + 1);
                }
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
