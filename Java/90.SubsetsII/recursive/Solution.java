/* Backtarck: O(2^n)
 * 1. Sort nums, and pick the number which is different with "prev", and append it to path
 */

import java.util.*;

public class Solution{
    private void backtrack(int[] nums, int start, Deque<Integer> path, List<List<Integer>> subsets){
        subsets.add(new LinkedList<>(path));
        if(start == nums.length){
            return;
        }
        
        int prev = nums[start] - 1;
        for(int i = start; i < nums.length; ++i){
            if(nums[i] != prev){
                path.add(nums[i]);
                backtrack(nums, i + 1, path, subsets);
                path.pollLast();
                prev = nums[i];
            }
        }
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new LinkedList<>();
        backtrack(nums, 0, new LinkedList<>(), subsets);
        return subsets;
    }
   
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1,2,2};
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("subsets: " + sol.subsetsWithDup(nums));
    }
}
