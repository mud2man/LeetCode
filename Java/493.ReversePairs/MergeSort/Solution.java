/* Merge Sort: Time:O(nlogn), Space:O(n). LeetCode has binary index tree solution
 * 1. During merge sort, divide the array into two parts "left" and "right"
 * 2. We get the important reverse pair# from "left" and "right"
 * 3. Get the reverse pair#, where left element is in left, and right element is in right
 * 4. Then do merge sort among "left" and "right", and return count
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    private int mergeSort(int lb, int hb, int[] nums){
        if(lb >= hb){
            return 0;
        }
        
        int mid = (lb + hb) / 2;
        int count = mergeSort(lb, mid, nums) + mergeSort(mid + 1, hb, nums);
        for(int leftIdx = lb, rightIdx = mid + 1; leftIdx <= mid; ++leftIdx){
            while(rightIdx <= hb && (long)nums[leftIdx] > 2L * (long)nums[rightIdx]){
                rightIdx++;
            }
            count += (rightIdx - mid - 1);
        }
        
        Deque<Integer> mergedList = new LinkedList<>();
        int leftIdx = lb;
        int rightIdx = mid + 1;
        while(leftIdx <= mid || rightIdx <= hb){
            int leftVal = (leftIdx <= mid)? nums[leftIdx]: Integer.MAX_VALUE;
            int rightVal = (rightIdx <= hb)? nums[rightIdx]: Integer.MAX_VALUE;
            if(leftVal <= rightVal && leftIdx <= mid){
                mergedList.add(nums[leftIdx++]);
            }else{
                mergedList.add(nums[rightIdx++]);
            }
        }
    
        for(int i = lb; i <= hb; ++i){
            nums[i] = mergedList.pollFirst();
        }
        return count;
    }
    
    public int reversePairs(int[] nums) {
        return mergeSort(0, nums.length - 1, nums);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 3, 2, 3, 1}; 
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("reverse pairs count: " + sol.reversePairs(nums));
    }
}
