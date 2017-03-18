/* Qoickselect: O(n)
 * 1. It's can be proven that there is only one local minimum. And it is also the global minimum. 
 * 2. The minimum is the median. 
 * 3. Use the selecting method in quick sort to find the medium
 */

import java.util.*; // Stack

public class Solution {
    public int quickSelect(int[] nums, int headIdx, int tailIdx){
        int target, smallIdx, idx, tmp;
        
        target = nums[tailIdx];
        smallIdx = headIdx - 1;
   
        for(idx = headIdx; idx < tailIdx; ++idx){
            if(nums[idx] < target){
                tmp = nums[idx];
                nums[idx] = nums[++smallIdx];
                nums[smallIdx] = tmp;
            }
        }
        nums[tailIdx] = nums[++smallIdx];
        nums[smallIdx] = target;
        
        return smallIdx - headIdx;
    }
    
    public int minMoves2(int[] nums) {
        int kIdx, headIdx, tailIdx, idx, medium, minMove;
        
        headIdx = 0;
        tailIdx = nums.length - 1;
        kIdx = nums.length / 2;
        
        idx = -1;
        while(true){
            idx = quickSelect(nums, headIdx, tailIdx);
            
            if(kIdx > idx){
                headIdx = headIdx + (idx + 1);
                kIdx = kIdx - (idx + 1);
            }
            else if (kIdx < idx){
                tailIdx = headIdx + (idx - 1);
            }
            else{
                break;
            }
        }
        
        medium = nums[headIdx + idx];
        minMove = 0;
        for(int num: nums){
            minMove += Math.abs(num - medium);   
        }
        
        return minMove;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 2, 3};
        
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("minimum moves#: " + sol.minMoves2(nums));
    }
}
