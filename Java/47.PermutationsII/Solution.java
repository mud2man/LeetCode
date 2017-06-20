/* Sort + Backtrack: O(n!)
 * 1. Sort
 * 2. Call the helper recursively
 * 3. Only insert the number from remain only the current value is different with the previous one
 *
 * ex: nums={2, 1, 1}, remain={1, 1, 2}, permutation={}, 
 *
 * time[0]: remain={1, 2}, permutation = {1}
 *   time[1]: remain={2}, permutation = {1 ,1}
 *     time[2]: remain={}, permutation = {1 ,1, 2} => return
 *   time[3]: remain={1}, permutation = {1 ,2}
 *     time[4]: remain={}, permutation = {1 ,2, 1} => return
 * time[5]: remain={1, 1}, permutation = {2}
 *   time[6]: remain={1}, permutation = {2, 1}
 *     time[7]: remain={}, permutation = {2, 1, 1} => return
 */          

import java.util.*;

public class Solution {
    public void helper(LinkedList<Integer> remain, LinkedList<Integer> permutation, List<List<Integer>> permutations){
        int i, preVal, size;
        
        if(remain.isEmpty()){
            permutations.add(new LinkedList<Integer>(permutation));
            return;
        }
        
        preVal = remain.get(0) - 1;
        size = remain.size();
        for(i = 0; i < size; ++i){
            if(preVal != remain.get(i)){
                preVal = remain.get(i);
                permutation.add(remain.remove(i));
                helper(remain, permutation, permutations);
                remain.add(i, permutation.pollLast());
            }
        }
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<Integer> permutation, remain;
        List<List<Integer>> permutations;
        
        permutation = new LinkedList<Integer>();
        remain = new LinkedList<Integer>();
        permutations = new LinkedList<List<Integer>>();
        
        Arrays.sort(nums);
        for(int num: nums){
            remain.add(num);
        }
        
        helper(remain, permutation, permutations);
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
