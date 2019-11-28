/* HashMap: Time:O(n), Space:O(n)
 * 1. Have a hashmap "sumToCount" to record the count of sum which is sum of nums[0], nums[1], ... nums[i] 
 * 2. In the loop, we accumulate count if we find (sum - k) in "sumToCount"
 *
 * ex: nums: {1, 1, 1}, k = 2
 * Two answers: {1, 1} - {}, {1 ,1 ,1} - {1}
 */

import java.util.*;

public class Solution{
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sum2Count = new HashMap<>();
        int count = 0;
        int sum = 0;
        for(int num: nums){
            sum += num;
            if(sum == k){
                count++;
                count += sum2Count.getOrDefault(0, 0);
            }else{
                int target = sum - k;
                count += sum2Count.getOrDefault(target, 0);
            }
            sum2Count.putIfAbsent(sum, 0);
            sum2Count.put(sum, sum2Count.get(sum) + 1);
        }
        return count;
    }
 
    public static void main(String[] args){
        int[] nums = {1, 1, 1};
        int k = 2;
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("the number of subarray: " + sol.subarraySum(nums, k));
    }
}
