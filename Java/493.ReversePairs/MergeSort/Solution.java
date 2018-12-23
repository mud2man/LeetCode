/* Merge Sort: Time:O(nlogn), Space:O(n). LeetCode has binary index tree solution
 * 1. During merge sort, divide the array into two parts "left" and "right"
 * 2. We get the important reverse pair# from "left" and "right"
 * 3. Get the reverse pair#, where left element is in left, and right element is in right
 * 4. Then do merge sort among "left" and "right", and return count
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    private int mergeSort(int[] nums, int start, int end){
        if(start >= end){
            return 0;
        }
        
        // count the number of important reverse pair, where left element is in left, and right element is in right
        int mid = (start + end) / 2;
        int leftCount = mergeSort(nums, start, mid);
        int rightCount = mergeSort(nums, mid + 1, end);
        int count = leftCount + rightCount;
        for(int leftIdx = start, rightIdx = mid + 1; leftIdx <= mid; leftIdx++){
            int target = (nums[leftIdx] > 0)? (nums[leftIdx] - 1) / 2: nums[leftIdx] / 2 - 1;
            while(rightIdx <= end && nums[rightIdx] <= target){
                rightIdx++;
            }
            count += (rightIdx - (mid + 1));
        }
        
        // merge sort
        Deque<Integer> queue = new LinkedList<>();
        int leftIdx = start;
        int rightIdx = mid + 1;
        while(leftIdx <= mid || rightIdx <= end){
            int leftVal = (leftIdx <= mid)? nums[leftIdx]: Integer.MAX_VALUE;
            int rightVal = (rightIdx <= end)? nums[rightIdx]: Integer.MAX_VALUE;
            if(leftVal <= rightVal && leftIdx <= mid){
                queue.add(nums[leftIdx++]);
            }
            else{
                queue.add(nums[rightIdx++]);
            }
        }
        for(int i = start; i <= end; ++i){
            nums[i] = queue.pollFirst();
        }
        return count;
    }
    
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 3, 2, 3, 1}; 
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("reverse pairs count: " + sol.reversePairs(nums));
    }
}
