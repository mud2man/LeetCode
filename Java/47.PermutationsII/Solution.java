/* DFS + Sort + HashMap: O(n!)
 * 1. Sort and caculated used list to store (first index - 1) for every distinct element
 * 2. Call the DFS helper recursively
 * 3. For every candidate, their index must be accumulated to prevent dupliucated permutation
 * 4. Do push and pop
 *
 * ex: nums={1, 1, 2}, remain={0, 1, 2}, used={{1, -1}, {2, 1}}, permutation={}
 *
 * time[0]: remain={}, used={{1, 1}, {2, 2}}, permutation={1, 1, 2} (O)
 * time[1]: remain={}, used={{1, 1}, {2, 2}}, permutation={1, 2, 1} (O)
 * time[2]: remain={0, 2}, used={{1, -1}, {2, 1}}, permutation={}, candidate=1 (skip, because candidate=1, which is not equal to -1+1)
 * time[3]: remain={}, used={{1, 1}, {2, 2}}, permutation={2, 1, 1} (O)
 * time[4]: remain={0}, used={{1, -1}, {2, 2}}, permutation={2}, candidate=1 (skip, because candidate=1, which is not equal to -1+1)
 */          

import java.util.*;

public class Solution {
    public void dfs(int[] nums, List<List<Integer>> permutations, ArrayList<Integer> permutation, HashMap<Integer, Integer> used, ArrayList<Integer> remain){
        int idx, size, candidate;
        
        if(remain.isEmpty()){
            permutations.add(new ArrayList<Integer>(permutation));
            return;
        }
        
        size = remain.size();
        for(idx = 0; idx < size; idx++){
            candidate = remain.get(idx);

            if(used.get(nums[candidate]) != candidate - 1){
                continue;
            }
            
            //push
            permutation.add(nums[candidate]);
            used.put(nums[candidate], candidate);
            remain.remove(idx);
                    
            dfs(nums, permutations, permutation, used, remain);
                    
            //pop
            permutation.remove(permutation.size() - 1);
            used.put(nums[candidate], candidate - 1);
            remain.add(idx, candidate);
        }
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutations;
        ArrayList<Integer> permutation;
        HashMap<Integer, Integer> used;
        ArrayList<Integer> remain;
        
        Arrays.sort(nums);
        
        permutations = new ArrayList<List<Integer>>(); 
        permutation = new ArrayList<Integer>();
        used = new HashMap<Integer, Integer>();
        remain = new ArrayList<Integer>();
        
        for(int idx = 0; idx < nums.length; idx++){
            remain.add(idx);
            if(idx == 0 || nums[idx - 1] != nums[idx]){
                used.put(nums[idx], idx - 1);
            }
        }
        
        dfs(nums, permutations, permutation, used, remain);
        
        return permutations;
    }
  
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 1, 2};
        List<List<Integer>> permutations;

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));

        permutations = sol.permuteUnique(nums);

        System.out.println("permutations: ");
        for(List<Integer> permutation: permutations){
            System.out.println(permutation);
        }
    }
}
