/* Use binary search 
 * 1. There must one local maximum in the array
 * 2. Keep searching if lb < hb
 * 3. Shift hb to mid if midValue > rightValue
 * 4. Otherwise, shift lb to mid + 1
 * 5. Return lb, which is the answer
 */
import java.util.*;

public class Solution {
    public int findPeakElement(int[] nums){
        int lb = 0;
        int hb = nums.length - 1;
        while(lb < hb){
            int mid = (lb + hb) / 2;
            int midValue = nums[mid];
            int rightValue = ((mid + 1) < nums.length)? nums[mid + 1]: Integer.MIN_VALUE;
            
            if(midValue > rightValue){
                hb = mid;
            }
            else{
                lb = mid + 1;
            }
        }
        return lb;
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
