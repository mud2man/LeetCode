/* Dynamic programming: O(2^n)
 * 1. Sort nums, and add num into the latest "subsets.size()" subset (j - i) times, where nums[i] = nums[i + 1] = ... nums[i + j - 1]
 * 1. Assume nums = [1, 2, 2]
 * 2. 1st: []
 * 3. 2nd: [], [1] => nums[0]
 * 4. 3rd: [], [1], [2], [1, 2], [2, 2], [1, 2, 2] => nums[1] ~ nums[2]
 */

import java.util.*;

public class Solution{
    private void helper(int i, int j, List<List<Integer>> subsets, int[] nums){
        int length = subsets.size();
        int start = 0;
        
        for(int k = 0; k < (j - i); ++k){
            for(int l = 0; l < length; ++l){
                List<Integer> subset = new ArrayList<Integer>(subsets.get(start + l));
                subset.add(nums[i]);
                subsets.add(subset);
            }
            start += length;
        }
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<List<Integer>>();
        subsets.add(new ArrayList<Integer>());
        int i = 0;
        
        while(i < nums.length){
            int prevNum = nums[i];
            int j = i;
            while(j < nums.length && nums[j] == prevNum){
                ++j;
            }
            helper(i, j, subsets, nums);
            i = j;
        }
        
        return subsets;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1,2,2};
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("subsets: " + sol.subsetsWithDup(nums));
    }
}
