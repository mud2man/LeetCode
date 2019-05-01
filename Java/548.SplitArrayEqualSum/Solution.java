/* HashSet: Time:O(n^2), Space:O(n)
 * 1. Iterate j, and iterate i, then put the equal sums on left part into the set "lefts"
 * 2. Iterate k, if find equals sums, check if it in the lefts. If so return true
 */         

import java.util.*;

public class Solution {
    public boolean splitArray(int[] nums) {
        int length = nums.length;
        int[] sum = new int[length];
        
        for(int i = 0; i < length; ++i){
            sum[i] = (i > 0)? nums[i] + sum[i - 1]: nums[i];
        }
        
        for(int j = 3; j <= length - 4; ++j){
            int leftSum = sum[j - 1];
            HashSet<Integer> lefts = new HashSet<Integer>();
            for(int i = 1; i < j - 1; ++i){
                if(sum[i - 1] == (leftSum - sum[i])){
                    lefts.add(sum[i - 1]);
                }
            }
            
            for(int k = j + 2; k < length - 1; ++k){
                if((sum[k - 1] - sum[j]) == (sum[length - 1] - sum[k])){
                    if(lefts.contains(sum[k - 1] - sum[j])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Solution sol; 
        int[] nums = {1, 2, 1, 2, 1, 2, 1};
        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can be splitted: " + sol.splitArray(nums));
    }
}
