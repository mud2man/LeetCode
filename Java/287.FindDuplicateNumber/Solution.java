/* Binary search: O(n*logn)
 * 1. Because the range is between 1 and n, we can binary search with lb = 1, hb = n
 * 2. If the number of elements <= mid is bigger than mid, we learn that the duplicated number is between 1 and mid, set hb = mid -1
 * 3. Otherwise, set lb = mid + 1
 */

import java.util.*;

public class Solution{
    private int getCount(int[] nums, int target){
        int count = 0;
        for(int num: nums){
            count = (num <= target)? count + 1: count;
        }
        return count;
    }
    
    public int findDuplicate(int[] nums) {
        int hb = nums.length - 1;
        int lb = 1;
        
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int equalLessCount = getCount(nums, mid);
            if(equalLessCount > mid){
                hb = mid - 1;
            }
            else{
                lb = mid + 1;
            }
        }
        
        return lb;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 3, 4, 2, 2};
        sol = new Solution();
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("duplicate: " + sol.findDuplicate(nums));
    }
}
