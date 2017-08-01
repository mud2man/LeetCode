/* Use binary search 
 * 1. if comes with mid - 1, must check mid > 0
 */
import java.util.*;

public class Solution {
    public int findPeakElement(int[] nums){
        int lb = 0;
        int hb = nums.length - 1;
        while(lb < hb){
            int mid = (lb + hb) / 2;
            if(mid == 0 && nums[mid] > nums[mid + 1]){
                return mid;
            }
            else if(mid > 0 && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]){
                return mid;
            }
            else{
                if((nums[mid] <= nums[lb] && mid != lb) || ( mid > 0 && nums[mid - 1] > nums[mid])){
                    hb = mid - 1;
                }
                else{
                    lb = mid + 1;
                }
            }
        }
        return hb; 
    }
    
    public static void main(String[] args){
        int[] nums = {3, 2, 1};
        int peak;
        Solution sol;
        
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        peak = sol.findPeakElement(nums);
        System.out.print("peak idx: " + peak);
    }

}
