/* Backtrack + Sort: O(2^n)
 * 1. Sort and then call backtrack
 * 2. Use backtarck to traverse "nums" from "idx", prevent use the same number by comparing "prev"
 * 3. Before leaving backtrack, pop the backtracking log "combination"
 */          

import java.util.*; // Stack

public class Solution {
        private void backtrack(int[] candidates, int idx, int remain, Deque<Integer> combination, List<List<Integer>> combinations){
        if(idx == candidates.length){
            if(remain == 0){
                combinations.add(new LinkedList<>(combination));
            }
            return;
        }   
        
        if(remain == 0){
            combinations.add(new LinkedList<>(combination));
        }
        else if(remain < 0){
            return;
        }
        else{ 
            for(int i = idx, prev = candidates[idx] - 1; i < candidates.length; ++i){
                if(prev != candidates[i]){
                    combination.add(candidates[i]);
                    backtrack(candidates, i + 1, remain - candidates[i], combination, combinations);
                    combination.pollLast();
                }
                prev = candidates[i];
            }
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        Deque<Integer> combination = new LinkedList<>();
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, combination, combinations);
        return combinations;
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
