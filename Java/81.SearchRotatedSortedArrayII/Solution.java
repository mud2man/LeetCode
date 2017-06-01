/* Binary search: O(logn), the solution on LeetCode is better
 * 1. Find the idx with minimum value
 * 2. Get the starting position with the lowest value nums[minIdx]
 * 3. Binary search the target with the offset minIdx
 */         

import java.util.*;

public class Solution {
    public boolean search(int[] nums, int target) {
        int lb, hb, mid, minIdx, rVal, lVal, tmpIdx;
        
        if(nums.length == 0){
            return false;
        }
        
        //Find the idx with minimum value
        lb = 0;
        hb = nums.length - 1;
        if(nums[0] < nums[nums.length - 1]){
            minIdx = 0;
        }
        else{
            while(lb + 1 < hb){
                mid = (lb + hb) / 2;
                
                //check if the right element of the middle is the lowest
                rVal = nums[mid];
                tmpIdx = mid;
                while(tmpIdx < nums.length - 1 && rVal == nums[mid]){
                    rVal = nums[++tmpIdx];
                }
                if(rVal < nums[mid]){
                    lb = tmpIdx;
                    hb = tmpIdx;
                    break;
                }
                
                //check if the left element of the middle is the highest
                lVal = nums[mid];
                tmpIdx = mid;
                while(tmpIdx > 0 && lVal == nums[mid]){
                    lVal = nums[--tmpIdx];
                }
                if(lVal > nums[mid]){
                    lb = mid;
                    hb = mid;
                    break;
                }
                
                //original binary search for the lowest value
                if(nums[mid] >= nums[lb]){
                    lb = mid;
                }
                else{
                    hb = mid;
                }
                
                if(nums[lb] <= nums[hb]){
                    hb = lb;
                    break;
                }
            }
            
            //pick the lower value between lb and hb, because lb and hb are now adjacent
            if(nums[lb] < nums[hb]){
                minIdx = lb; 
            }
            else{
                minIdx = hb;
            }
        }
        
        //get the starting position with the lowest value nums[minIdx]
        if(nums[minIdx] == nums[nums.length - 1]){
            minIdx = nums.length - 1;
        }
        while(minIdx > 0 && nums[minIdx - 1] == nums[minIdx]){
            minIdx--;
        }
        
        //binary search the target with the offset minIdx
        lb = 0;
        hb = nums.length - 1;
        while(lb <= hb){
            mid = (lb + hb) / 2;
            if(nums[(mid + minIdx) % nums.length] == target){
                return true;
            }
            else if(nums[(mid + minIdx) % nums.length] > target){
                hb = mid - 1;
            }
            else{
                lb = mid + 1;
            }
        }
        
        return false;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {4, 5, 5, 6, 7, 0, 1, 1, 2};
        int target;
        
        target = 7;
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        System.out.println("position: " + sol.search(nums, target));
    }
}
