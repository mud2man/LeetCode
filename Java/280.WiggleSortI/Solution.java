/* Sort: O(n)
 * 1. When idx is even, check if nums[i] > nums[i + 1]
 * 2. If yes, swap nums[i] and nums[i + 1], because nums[i - 1] is still bigger than nums[i]
 * 3. Swap will not destory the order of previous numbers
 * 4. When idx is odd, check if nums[i] < nums[i + 1]
 * 5. If yes, swap nums[i] and nums[i + 1], because nums[i - 1] is still smaller than nums[i]
 */

import java.util.*;
public class Solution{

    public void swap(int[] nums, int start, int end){
        int tmp;
        
        tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp; 
    }
    
    public void wiggleSort(int[] nums) {
        int i;
        int tmp;

        for(i = 0; i < (nums.length - 1); i++){
            if(i % 2 == 0){
                if(nums[i] > nums[i + 1]){
                    swap(nums, i, i + 1);
                }
            }
            else{
                if(nums[i] < nums[i + 1]){
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {3, 5, 2, 1, 6, 4};
        
        sol = new Solution();

        System.out.println("before wiggle sort:" + Arrays.toString(nums));
        sol.wiggleSort(nums);
        System.out.println("after wiggle sort: " + Arrays.toString(nums));
    }
}
