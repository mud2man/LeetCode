/* Backtrack: Time:O(n!), Space:O(k)
 * 1. Pick a number from "start" to "(n - remain + 1)" until "remain" is 0
 */         

import java.util.*;

public class Solution {
    private void backtrack(int start, int n, int remain, Deque<Integer> combination, List<List<Integer>> combinations){
        if(remain == 0){
            combinations.add(new LinkedList<>(combination));
            return;
        }
        
        for(int i = start; i <= n - remain + 1; ++i){
            combination.add(i);
            backtrack(i + 1, n, remain - 1, combination, combinations);
            combination.pollLast();
        }
    }
    
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        Deque<Integer> combination = new LinkedList<>();
        backtrack(1, n, k, combination, combinations);
        return combinations;
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        int n = 4;
        int k = 2;
        System.out.println("n: " + n + ", k: " + k);
        System.out.println("combinations: " + sol.combine(n, k));
    }
}
