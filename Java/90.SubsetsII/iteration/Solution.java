/* Dynamic programming: Time:O(2^n), Space:O(2^n)
 * 1. Sort nums, and use "queue" to stroe the previos subset, where the length of subset in the queue increase 1 after every loop
 * 2. We put the starting index in the last position of subset
 *
 * ex:nums = {1, 2, 2}
 * time[0], queue={{0}}, subsets={}
 * time[1], queue={{1, 1}, {2, 2}}, subsets={{1}, {2}}
 * time[2], queue={{1, 2, 2}, {2, 2, 3}}, subsets={{1}, {2}, {1, 2}, {2, 2}}
 * time[3], queue={{1, 2, 2, 3}}, subsets={{1}, {2}, {1, 2}, {2, 2}, {1, 2, 2}}
 * time[4], queue={}, subsets={{1}, {2}, {1, 2}, {2, 2}, {1, 2, 2}}
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
