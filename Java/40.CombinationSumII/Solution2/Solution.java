/* Backtrack + Sort: O(2^n)
 * 1. Sort and then call backtrack
 * 2. Use backtarck to traverse "nums" from "idx", prevent use the same number by comparing "prev"
 * 3. The reason don't take the dulicate is the first shot of for loop will take them
 * 4. Before leaving backtrack, pop the backtracking log "path"
 */          

import java.util.*; // Stack

public class Solution {
    private void backtrack(int[] candidates, int start, int reamin, Deque<Integer> path, List<List<Integer>> ret){
        if(start == candidates.length || reamin <= 0){
            if(reamin == 0){
                ret.add(new ArrayList<>(path));
            }
            return;
        }
        
        int prev = candidates[start] - 1;
        for(int i = start; i < candidates.length; ++i){
            if(candidates[i] != prev){
                path.add(candidates[i]);
                backtrack(candidates, i + 1, reamin - candidates[i], path, ret);
                path.pollLast();
                prev = candidates[i];
            }
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        backtrack(candidates, 0, target, new LinkedList<>(), ret);
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> combinationSums;

        sol = new Solution();

        System.out.println("candidates: " + Arrays.toString(candidates));
        System.out.println("target: " + target);

        combinationSums = sol.combinationSum2(candidates, target);

        System.out.println("combination sums: ");
        for(List<Integer> sum: combinationSums){
            System.out.println(sum);
        }
    }
}
