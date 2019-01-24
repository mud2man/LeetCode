/* Binary Search: Time:O(logn), Space:O(1)
 * 1. Move left if left part has odd numbers. Otherwise, move right
 */

import java.util.*; // Stack

public class Solution {
    public int singleNonDuplicate(int[] nums) {
        int lb = 0;
        int hb = nums.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int left = (mid > 0)? nums[mid - 1]: nums[mid] - 1;
            int right = (mid < nums.length - 1)? nums[mid + 1]: nums[mid] + 1;
            if(nums[mid] != left && nums[mid] != right){
                return nums[mid];
            }
            else if(nums[mid] == left){
                int leftCount = mid - 1 - lb;
                if(leftCount % 2 == 1){
                    hb = mid - 2;
                }
                else{
                    lb = mid + 1;
                }
            }
            else{
                int leftCount = mid - lb;
                if(leftCount % 2 == 1){
                    hb = mid - 1;
                }
                else{
                    lb = mid + 2;
                }
            }
        }
        
        //dead code
        return 0;
    }  

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("single number: " + sol.singleNonDuplicate(nums));
    }
}
