/* Backtrack: Time:O(n!), Space:O(n)
 * 1. Sort input array, and call backtrack recursively
 */

import java.util.*; 

public class Solution{
    public void backtrack(int[] candidates, int residure, Deque<Integer> path, List<List<Integer>> lists, int start){
        if(residure <= 0){
            if(residure == 0){
                lists.add(new ArrayList<Integer> (path));
            }
            return;
        }
        
        for(int idx = start; idx < candidates.length && candidates[idx] <= residure; idx++){
            path.addLast(candidates[idx]);
            backtrack(candidates, residure - candidates[idx], path, lists, idx);
            path.pollLast();
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        backtrack(candidates, target, new LinkedList<>(), lists, 0);
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
