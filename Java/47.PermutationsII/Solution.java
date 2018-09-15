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
    private void backtrack(Deque<Integer> queue, List<List<Integer>> permutations, Deque<Integer> path){
        if(queue.isEmpty()){
            permutations.add(new LinkedList<>(path));
            return;
        }
        
        int prev = queue.peekFirst() - 1;
        int size = queue.size();
        for(int i = 0; i < size; i++){
            int curr = queue.pollFirst();
            if(prev != curr){
                path.add(curr);
                backtrack(queue, permutations, path);
                path.pollLast();
            }
            queue.add(curr);
            prev = curr;
        }
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        Deque<Integer> queue = new LinkedList<>();
        for(int num: nums){
            queue.add(num);
        }
        List<List<Integer>> permutations = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        backtrack(queue, permutations, path);
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
