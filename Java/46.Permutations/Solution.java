/* Back Tracke: Time: O(n!), Space:O(n)
 * 1. Use backtrack to find all the permutations
 */

import java.util.*;

public class Solution{
    public void helper(Deque<Integer> remain, Deque<Integer> permutation, List<List<Integer>> permutations){
        if(remain.isEmpty()){
            permutations.add(new LinkedList<Integer>(permutation));
            return;
        }
        
        int size = remain.size();
        for(int i = 0; i < size; ++i){
            int num = remain.pollFirst();
            permutation.add(num);
            helper(remain, permutation, permutations);
            permutation.pollLast();
            remain.add(num);
        }
    }
    
    public List<List<Integer>> permute(int[] nums) {
        Deque<Integer> remain = new LinkedList<Integer>();
        Deque<Integer> permutation = new LinkedList<Integer>();
        List<List<Integer>> permutations = new LinkedList<List<Integer>>();
        for(int num: nums){
            remain.add(num);
        }
        helper(remain, permutation, permutations);
        return permutations;
    }
  
    public static void main(String[] args){
        int[] nums = {1, 2, 3};
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));    
        System.out.println("permutations: " + sol.permute(nums));    
    }
}
