/* Dynamic Programming: Time:O(n), Space: O(k). Leetcode has a shorter answer
 * 1. Reduce nums[i] as nums[i] % k
 * 2. Have a hashmap "rightSumMap" to store the accumulated sum {nums[nums.length-1], nums[nums.length-1] + nums[nums.length-2], ..}
 * 3. Traverse from index 0, and check if rightSumMap contains the key of totalSum s.t. exist a sequence can cut the total sum to 
      multiple of k
 * 
 * ex: k = 6, nums = {23, 2, 4, 6, 7} => {5, 2, 4, 0, 1}
 *     rightSumMap = {1:2, 5:1}
 */         

import java.util.*;

public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length < 2){
            return false;
        }
        
        if(k == 0){
            int count = 0;
            for(int num: nums){
                count = (num == 0)? count + 1: 0;                
                if(count >= 2){
                    return true;
                }
            }
            return false;
        }
        
        k = Math.abs(k);
        HashMap<Integer, Integer> rightSumMap = new HashMap<Integer, Integer>();
        int rightSum = 0;
        int totalSum = 0;
        
        for(int i = nums.length - 1; i >= 2; --i){
            nums[i] = nums[i] % k;
            rightSum += nums[i];
            rightSum = rightSum % k;
            rightSumMap.putIfAbsent(rightSum, 0);
            rightSumMap.put(rightSum, rightSumMap.get(rightSum) + 1);
        }
        
        totalSum = rightSum;
        nums[0] = nums[0] % k;
        nums[1] = nums[1] % k;
        totalSum = (totalSum + nums[0] + nums[1]) % k;
        
        int previousNum = 0;
        for(int i = 0; i < (nums.length - 1); ++i){
            totalSum = (totalSum - previousNum + k) % k;
            if(totalSum == 0 || rightSumMap.containsKey(totalSum)){
                return true;
            }
            
            previousNum = nums[i];
            if((i + 2) < nums.length){
                rightSumMap.put(rightSum, rightSumMap.get(rightSum) - 1);
                if(rightSumMap.get(rightSum) == 0){
                    rightSumMap.remove(rightSum);
                }
                long temp = ((long)rightSum - (long)nums[i + 2] + (long)k) % (long)k;
                rightSum = (int) temp;
            }
        }

        return false;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        
        sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("k:" + k);
        System.out.println("continuous subarray with sum = n*k exists: " + sol.checkSubarraySum(nums, k));
    }
}
