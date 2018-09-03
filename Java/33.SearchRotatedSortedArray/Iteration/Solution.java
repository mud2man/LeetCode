/* Binary search: O(logn)
 * 1. Calssify the following scenerios] => mid: *, lb: #, ub: #
 * 2. Case0: nums[ub] < nums[lb] && midVal >= nums[lb]
 *
 *     / |
 *    *  |
 *   /   |
 *  #    | 
 *       |
 *       |  #
 *       | / 
 *
 * 3. Case1: nums[ub] < nums[lb] && midVal < nums[ub]
 *      
 *     / |
 *    #  |
 *       |
 *       |    #
 *       |   /
 *       |  *
 *       | /
 *
 * 4. Case1: nums[ub] > nums[lb]
 *      
 *           #
 *          /
 *         *
 *        /
 *       /
 *      #
 */         

import java.util.*;

public class Solution {
    public int search(int[] nums, int target) {
        int lb, ub, mid, midVal;
        
        lb = 0;
        ub = nums.length - 1;
        
        while(lb <= ub){
            mid = (lb + ub) / 2;
            midVal = nums[mid];

            if(nums[mid] == target){
                return mid;
            }
            else if(nums[ub] < nums[lb]){
                if(midVal >= nums[lb]){
                    if(target > midVal || target < nums[lb]){
                        lb = mid + 1;
                    }
                    else{
                        ub = mid - 1;
                    }
                }
                else{
                    if(target < midVal || target > nums[ub]){
                        ub = mid - 1;
                    } 
                    else{
                        lb = mid + 1; 
                    }
                }
            }
            else{
                if(target > midVal){
                    lb = mid + 1;
                }
                else{
                    ub = mid - 1;
                }
            }
        }
        
        return -1;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target;
        
        target = 7;
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        System.out.println("position: " + sol.search(nums, target));
    }
}
