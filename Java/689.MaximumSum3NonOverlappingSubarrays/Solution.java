/* Dynamic programming: Time:O(n), Space:O(n)
 * 1. Compute the array "subArrays" of k-subarray's sum
 * 2. Compute the "maxLeftSubArray", where maxLeftSubArrays[i] = max(subArrays[0], subArrays[1], ..., subArrays[i])
 * 3. Compute the "maxRightSubArrays", where maxRightSubArrays[i] = max(subArrays[i], subArrays[i + 1], ..., subArrays[length - 1])
 * 4. Traverse all the valid 2nd subarrays, get the maximum three sums, and determine the 2nd index of subarray
 * 5. Now, we have the left sum and the right sum. So we can traverse from left to find the lexicographically smallest index
 * 
 * ex: nums[]              = {1, 2, 1, 2, 6, 7, 5, 1}, k = 2
 *     subArrays[]         = {3, 3, 3, 8, 13, 12, 6}
 *     maxLeftSubArrays[]  = {3, 3, 3, 8, 13, 13, 13}
 *     maxRightSubArrays[] = {13, 13, 13, 13, 13, 12, 6}
 *     valid index of 2nd sub-array is {2, 3, 4}
 *     maxThreeSum = max{(3 + 3 + 13), (3 + 8 + 12), (3 + 13 + 6)} => 2nd index is 3 
 */

import java.util.*;

public class Solution{
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int length = nums.length;
        int[] subArrays = new int[length - k + 1];
        
        int sum = 0;
        for(int i = 0; i < (k - 1); ++i){
            sum += nums[i];
        }
        
        for(int i = 0; i < subArrays.length; ++i){
            sum += nums[k + i - 1];
            subArrays[i] = sum;
            sum -= nums[i];
        }
        
        int[] maxLeftSubArrays = new int[length - k + 1];
        int maxLeftSubArray = 0;
        for(int i = 0; i < maxLeftSubArrays.length; ++i){
            maxLeftSubArray = Math.max(maxLeftSubArray, subArrays[i]);
            maxLeftSubArrays[i] = maxLeftSubArray;
        }
        
        int[] maxRightSubArrays = new int[length - k + 1];
        int maxRightSubArray = 0;
        for(int i = maxRightSubArrays.length - 1; i >= 0; --i){
            maxRightSubArray = Math.max(maxRightSubArray, subArrays[i]);
            maxRightSubArrays[i] = maxRightSubArray;
        }
        
        int maxThreeSum = 0;
        int[] threeSubArrays = new int[3];
        for(int i = k; i <= (length - 2 * k); ++i){
            int currentThreeSum = subArrays[i] + maxLeftSubArrays[i - k] + maxRightSubArrays[i + k];
            if(maxThreeSum < currentThreeSum){
                threeSubArrays[0] = i - k;
                threeSubArrays[1] = i;
                threeSubArrays[2] = i + k;
                maxThreeSum = currentThreeSum;
            }
        }
        
        int left = maxLeftSubArrays[threeSubArrays[0]];
        int right = maxRightSubArrays[threeSubArrays[2]];
        for(int i = 0; i <= threeSubArrays[0]; ++i){
            if(left == subArrays[i]){
                threeSubArrays[0] = i;
                break;
            }
        }
        
        for(int i = threeSubArrays[2]; i < subArrays.length; ++i){
            if(right == subArrays[i]){
                threeSubArrays[2] = i;
                break;
            }
        }
        
        return threeSubArrays;
    }
 
    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> subsets;
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;
        
        sol = new Solution();
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("three subarrays: " + Arrays.toString(sol.maxSumOfThreeSubarrays(nums, k)));
    }
}
