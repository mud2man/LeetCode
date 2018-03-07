/* HashMap: Time:O(n), Space:O(n)
 * 1. Have a hashmap "sumToCount" to record the count of sum which is sum of nums[0], nums[1], ... nums[i] 
 * 2. In the loop, we accumulate count if we find (sum - k) in "sumToCount"
 */

import java.util.*;

public class Solution{
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> sumToCount = new HashMap<Integer, Integer>();
        
        int sum = 0;
        int count = 0;
        sumToCount.putIfAbsent(0, 1);
        for(int i = 0; i < nums.length; ++i){
            sum += nums[i];
            int remain = sum - k;
            
            if(sumToCount.containsKey(remain)){
                count += sumToCount.get(remain);
            }
            sumToCount.putIfAbsent(sum, 0);
            sumToCount.put(sum, sumToCount.get(sum) + 1);
        }
        
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 1, 1};
        int k = 2;

        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("the number of subarray: " + sol.subarraySum(nums, k));
    }
}
