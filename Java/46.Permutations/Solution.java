/* Back Tracke: Time: O(n!), Space:O(n)
 * 1. Use backtrack to find all the permutations
 */

import java.util.*;

public class Solution{
    public void helper(LinkedList<Integer> remain, LinkedList<Integer> permutation, LinkedList<List<Integer>> permutations){
        int i, size;
        
        if(remain.isEmpty()){
            permutations.add(new LinkedList<Integer>(permutation));
            return;
        }
        
        size = remain.size();
        for(i = 0; i < size; ++i){
            int num = remain.pollFirst();
            permutation.add(num);
            helper(remain, permutation, permutations);
            permutation.pollLast();
            remain.add(num);
        }
    }
    
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> remain, permutation;
        LinkedList<List<Integer>> permutations;
        
        remain = new LinkedList<Integer>();
        permutation = new LinkedList<Integer>();
        permutations = new LinkedList<List<Integer>>();
        
        for(int num: nums){
            remain.add(num);
        }
        
        helper(remain, permutation, permutations);
        return permutations;
    }    
    
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 2, 3};
        sol = new Solution();
        
        System.out.println("nums: " + Arrays.toString(nums));    
        System.out.println("permutations: " + sol.permute(nums));    
    }
}
