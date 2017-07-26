/* Backtrack: O(n!)
 */

import java.util.*; 

public class Solution{
	public void helper(int[] candidates, int residure, List<Integer> list, List<List<Integer>> lists, int start){
        int idx;
        
        if(residure == 0){
            lists.add(new ArrayList<Integer> (list));
            return;
        }
        
        for(idx = start; idx < candidates.length && candidates[idx] <= residure; idx++){
            list.add(candidates[idx]);
            helper(candidates, residure - candidates[idx], list, lists, idx);
            list.remove(list.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        helper(candidates, target, new ArrayList<Integer>(), lists, 0);
        return lists;
    }
 
    public static void main(String[] args){
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> combinationSums;
        Solution sol = new Solution();
        
        System.out.println("candidates: " + Arrays.toString(candidates));
        System.out.println("target: " + target);

        combinationSums = sol.combinationSum(candidates, target);

        System.out.println("combinationSums: " );
        for(List<Integer> combination: combinationSums){
            System.out.println(combination);
        }
    }
}
