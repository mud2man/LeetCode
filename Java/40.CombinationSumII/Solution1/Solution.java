 /* DFS + Sort: O(2^n)
 * 1. Sort and get the count map "counts"
 * 2. Use backtarck to traverse the given number nums.get(idx)
 * 3. Before leaving backtrack, pop the backtracking log "path"
 */          

import java.util.*; // Stack

public class Solution {
    private void backtrack(List<Integer> nums, int idx, Map<Integer, Integer> counts, LinkedList<Integer> path, List<List<Integer>> combinations, int target){
        if(target == 0){
            combinations.add(new ArrayList(path));
            return;
        }
        else if(target < 0 || idx == nums.size()){
            return;
        }
        
        int num = nums.get(idx);
        int count = counts.get(num);
        for(int i = 0; i <= count; ++i){
            backtrack(nums, idx + 1, counts, path, combinations, target);
            path.add(num);
            target -= num;
        }
        
        for(int i = 0; i <= count; ++i){
            path.pollLast();
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> sortNums = new ArrayList<>();
        Map<Integer, Integer> counts = new HashMap<>();
        for(int num: nums){
            if(sortNums.isEmpty() || sortNums.get(sortNums.size() - 1) != num){
                sortNums.add(num);
            }
            counts.putIfAbsent(num, 0);
            counts.put(num, counts.get(num) + 1);
        }
        backtrack(sortNums, 0, counts, new LinkedList<Integer>(), combinations, target);
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
