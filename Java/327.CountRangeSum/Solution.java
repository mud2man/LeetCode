/* Merget sort: Time:O(nlogn), Space:O(n).
 * 1. Transfrom the array "nums" to accumulated sum starting from left. ex: {-2, 5, -1} => {-2, 3, 2}
 * 2. So we can refomulate this proble to: For 0 < i < length, get the number of range where lower <= nums[i] - nums[j] <= upper, j < i
 * 3. We can use merge sort to do divide and conquer
 * 4. First, divide nums to two subarray (start, mid) and (mid + 1, end)
 * 5. Accumulate "count" with returned value of helper with the two subarray as input
 * 6. Caculate the number of qualified range sum where start <= i <= mid, mid < j <= end
 * 7. Do merge sort for these two array
 */

import java.util.*;

public class Solution{
    private int mergeSortBasedHelper(long[] nums, int start, int end, long lower, long upper){
        if(start >= end){
            return (start == end && nums[start] >= lower && nums[start] <= upper)? 1: 0;
        }
        
        //caculate the number of qualified range sum where start <= i <= mid, mid < j <= end 
        int count = 0;
        int mid = (start + end) / 2;
        count += mergeSortBasedHelper(nums, start, mid, lower, upper);
        count += mergeSortBasedHelper(nums, mid + 1, end, lower, upper);
        int leftLbIdx = start;
        int leftUbIdx = start;
        for(int i = mid + 1; i <= end; ++i){
            long rightVal = nums[i];
            long leftUpper = rightVal - lower;
            long leftLower = rightVal - upper;
            while(leftLbIdx <= mid && nums[leftLbIdx] < leftLower){
                leftLbIdx++;
            }
            while(leftUbIdx <= mid && nums[leftUbIdx] <= leftUpper){
                leftUbIdx++;
            }
            count += (leftUbIdx - leftLbIdx);
        }
        
        //merge sort
        List<Long> mergedList = new ArrayList<>();
        int leftIdx = start;
        int rightIdx = mid + 1;
        while(leftIdx <= mid || rightIdx <= end){
            long leftVal = (leftIdx <= mid)? nums[leftIdx]: Long.MAX_VALUE;
            long rightVal = (rightIdx <= end)? nums[rightIdx]: Long.MAX_VALUE;
            if(leftVal <= rightVal){
                mergedList.add(leftVal);
                leftIdx++;
            }else{
                mergedList.add(rightVal);
                rightIdx++;
            }
        }
        for(int i = 0, j = start; i < mergedList.size(); ++i, ++j){
            nums[j] = mergedList.get(i);
        }
        return count;
    }
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] longNums = new long[nums.length];
        for(int i = 0; i < nums.length; ++i){
            longNums[i] = (i > 0) ? (long)nums[i] + longNums[i - 1]: (long)nums[i];
        }
        return mergeSortBasedHelper(longNums, 0, longNums.length - 1, (long)lower, (long)upper);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {-2, 5, -1};
        int lower = -2;   
        int upper = 2;
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("lower:" + lower);
        System.out.println("upper:" + upper);
        System.out.println("qualified range#:" + sol.countRangeSum(nums, lower, upper));
    }
}
