/* Binary search: Time:O(n*logk), Space: O(1), where k is sum of nums
 * 1. Get the lower bound l(max(nums[0], nums[1], ...)), and upper bound r(nums[0] + nums[1] + ,...)
 * 2. Because the minimum largest sum is in the region, we can use binary search to pinpoint it
 * 3. However, we need to know which one (l or r) should be return
 * 4. We can assume mid is the answer, and l = r = mid, r would be (mid - 1). So we know we should return l
 * 
 */         

import java.util.*;

public class Solution {
    public int splitArray(int[] nums, int m) {
        int l = 0;
        int r = 0;
        
        for(int num: nums){
            l = Math.max(l, num);
            r += num;
        }
        
        while(l <= r){
            int mid = (int)(((long)l + (long)r) / 2);
            int groupCount = 0;
            int sum = 0;
            for(int num: nums){
                sum += num;
                if(sum > mid){
                    groupCount++;
                    sum = num;
                }
            }
            groupCount = (sum > 0)? groupCount + 1: groupCount;

            if(groupCount <= m){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        
        return l; 
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        
        sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("2:" + m);
        System.out.println("minimum largest sum: " + sol.splitArray(nums, m));
    }
}
