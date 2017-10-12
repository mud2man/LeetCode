/* Backtrack + Hashset: time = O(2^n), space = O(n)stack + O(n^2)hashset
 * 1. Traverse from the leftest position of nums, and put the number num into the new list subsequence, if num never be used
 * 2. In the backtracker, if the length of subsequence > 1, add it into subsequences
 * 3. Travese the from startIndex, and put the never-meet and increasing element into subsequence
 */

import java.util.*;

public class Solution{
    private void backtracker(List<List<Integer>> subsequences, LinkedList<Integer> subsequence, int[] nums, int startIndex){
        if(subsequence.size() > 1){
            subsequences.add(new LinkedList(subsequence));
        }
        
        Set<Integer> used = new HashSet<Integer>();
        for(int i = startIndex; i < nums.length; ++i){
            if(subsequence.peekLast() <= nums[i] && !used.contains(nums[i])){
                subsequence.add(nums[i]);
                backtracker(subsequences, subsequence, nums, i + 1);
                subsequence.pollLast();
                used.add(nums[i]);
            }
        }
    }
    
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> subsequences = new LinkedList<List<Integer>>();
        Set<Integer> used = new HashSet<Integer>();
        
        for(int i = 0; i < (nums.length - 1); ++i){
            if(!used.contains(nums[i])){
                LinkedList<Integer> subsequence = new LinkedList<Integer>();
                subsequence.add(nums[i]);
                backtracker(subsequences, subsequence, nums, i + 1);
                used.add(nums[i]);
            }
        } 
        
        return subsequences;
    }

    public static void main(String[] args){
        Solution sol;
        List<Integer> topK;
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> subSeqs;

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));

        subSeqs = sol.findSubsequences(nums);
        
        System.out.println("subSeqs: ");
        for(List<Integer>subSeq : subSeqs)
            System.out.println(subSeq);
	}
}
