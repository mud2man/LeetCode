/* Greedy + stack: Time:O(max(k^3, k * n)), Space:O(k). 
 * 1. Iterate all length combination of nums1 and nums2
 * 2. Given the length, we use getMaxSubNums to select the max subset of nums with the given length
 *    ex: {3, 2, 1, 7, 6, 5}, len = 3
 *    iteration 2: {3, 2, 1}, top = 2, remain = 3
 *    iteration 3: {7}, top = 0, remain = 0
 *    iteration 4: {7, 6}, top = 1, remain = 0
 *    iteration 5: {7, 6, 5}, top = 2, remain = 0
 * 3. After select the according sub set of nums from nums1 and nums2. We use "getMaxNums" to merge to the max nums "curr", then update "max"
 */

import java.util.*; // Stack

public class Solution {
    private int[] getMaxNums(int[] nums1, int[] nums2, int len){
        int[] maxNums = new int[len];
        for(int i = 0, j = 0; i < nums1.length || j < nums2.length; ){
            int num1 =(i < nums1.length)? nums1[i]: -1;
            int num2 =(j < nums2.length)? nums2[j]: -1;
            if(num1 > num2){
                maxNums[i + j] = num1;
                i++;
            }else if(num1 < num2){
                maxNums[i + j] = num2;
                j++;
            }else{
                if(isBigger(nums1, i, nums2, j)){
                    maxNums[i + j] = num1;
                    i++;
                }else{
                    maxNums[i + j] = num2;
                    j++;
                }
            }
        }
        return maxNums;
    }
    
    private boolean isBigger(int[] nums1, int i, int[] nums2, int j){
        while(i <= nums1.length && j <= nums2.length){
            if(j == nums2.length){
                return true;
            }else if(i == nums1.length){
                return false;
            }else if(nums1[i] > nums2[j]){
                return true;
            }else if(nums1[i] < nums2[j]){
                return false;
            }else{
                i++;
                j++;
            }
        }
        return true;
    }
    
    private int[] getMaxSubNums(int[] nums, int len) {
        int[] stack = new int[len];
        int top = -1;
        int remain = nums.length - len;
        for(int num: nums){
            while(top >= 0 && stack[top] < num && remain > 0){
                top--;
                remain--;
            }
            if(top < len - 1){
                stack[++top] = num;
            }else{
                remain--;
            }
        }
        return stack;
    }
    
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] max = new int[k];
        for(int len1 = 0; len1 <= k && len1 <= nums1.length; len1++){
            int len2 = k - len1;
            if(len2 <= nums2.length){
                int[] maxSubNums1 = getMaxSubNums(nums1, len1);
                int[] maxSubNums2 = getMaxSubNums(nums2, len2);
                int[] curr = getMaxNums(maxSubNums1, maxSubNums2, k);
                max =isBigger(curr, 0, max, 0)? curr: max;
            }
        }
        return max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums1 = {3, 4, 6, 5}; 
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int k = 5; 
        System.out.println("nums1:" + Arrays.toString(nums1));
        System.out.println("nums2:" + Arrays.toString(nums2));
        System.out.println("k:" + k);
        System.out.println("max number:" + Arrays.toString(sol.maxNumber(nums1, nums2, k)));
    }
}
