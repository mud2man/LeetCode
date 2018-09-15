/* Binary Search: O(logn)
 */          

import java.util.*;

public class Solution {
    public int searchInsert(int[] nums, int target) {
        int lb = 0;
        int hb = nums.length - 1;
        
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int num = nums[mid];
            if(num < target){
                lb = mid + 1;
            }
            else{
                hb = mid - 1;
            }
        }
        return lb;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();;
        int[] nums = {1, 3, 5, 6};
        int target = 5;

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        System.out.println("insert position: " + sol.searchInsert(nums, target));
    }
}
