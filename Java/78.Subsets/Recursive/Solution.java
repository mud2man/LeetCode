/* Backtrack: O(2^n)
 * 1. Assume nums = [1, 2, 3]
 * 2. 1st: []
 * 3. 2nd: [], [1] => nums[0]
 * 4. 3rd: [], [1], [2], [1, 2] => nums[1]
 * 5. 3rd: [], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3] => nums[2]
 */

import java.util.*;

public class Solution{
    private void backtarck(int[] nums, int idx, List<List<Integer>> answer, List<Integer> path){
        if(idx == nums.length){
            answer.add(path);
            return;
        }
        
        List<Integer> newPath = new ArrayList<>(path);
        path.add(nums[idx]);
        backtarck(nums, idx + 1, answer, path);
        backtarck(nums, idx + 1, answer, newPath);
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        backtarck(nums, 0, answer, new ArrayList<Integer>());
        return answer;
    }
 
    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> subsets;
        int[] nums = {1,2,3};
        
        sol = new Solution();
        
        System.out.println("nums[]: " + Arrays.toString(nums));
        subsets = sol.subsets(nums);
        
        System.out.println("subsets[][]: ");
        for(List<Integer> subset: subsets){
            System.out.println(subset);
        }
    }
}
