/* Time:O(n), Space:O(1)
 * 1. This problem is equal to find the longest cycle
 * 2. Chnage nums[idx] to -1, if we visit idx
 * 3. Traverse index from left, and get the bigger number between max and getLength
 */

import java.util.*;

public class Solution{
    private int getLength(int[] nums, int idx){
        int l = 0;
        while(nums[idx] >= 0){
            l++;
            int nextIdx = nums[idx];
            nums[idx] = -1;
            idx = nextIdx;
        }
        return l;
    }
    
    public int arrayNesting(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; ++i){
            max = Math.max(max, getLength(nums, i));
        }
        return max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {5, 4, 0, 3, 1, 6, 2};
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("longest length: " + sol.arrayNesting(A));
    }
}
