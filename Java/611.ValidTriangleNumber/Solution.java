/* Greedy: Time:O(n^2*logn), Space:O(1). LeetCode has O(n^2) solution
 * 1. Sort numbers, and fixed i and k, and binary search j, because if nums[i] + nums[j] > nums[k], then there is a triangle
 * 2. Accumulate count with (k - j)
 */

import java.util.*;

public class Solution{
    private int binarySearch(int i, int k, int[] nums){
        int lb = i;
        int hb = k;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if((nums[i] + nums[mid]) > nums[k] && i != mid){
                hb = mid - 1;
            }
            else{
                lb = mid + 1;
            }
        }
        return lb;
    }
    
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        
        int count = 0;
        for(int k = 2; k < nums.length; ++k){
            for(int i = 0; i < k - 1; ++i){
                int j = binarySearch(i, k, nums);
                count += (k > j)? (k - j): 0;
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int nums[] = {2, 2, 3, 4};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("triangle number: " + sol.triangleNumber(nums));
    }
}
