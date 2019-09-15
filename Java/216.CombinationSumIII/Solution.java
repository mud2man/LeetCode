/* Backtrack: Time:O(9!), Space:O(9)
 * 1. Use backtrack to find all combinations
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    private void backtrack(int start, int count, int remain, List<List<Integer>> combinations, Deque<Integer> path){
        if(count == 0){
            if(remain == 0){
                combinations.add(new LinkedList<>(path));
            }
            return;
        }else{
            if(remain <= 0){
                return;
            }else{
                for(int i = start; i < 10; ++i){
                    path.add(i);
                    backtrack(i + 1, count - 1, remain - i, combinations, path);
                    path.pollLast();
                }
            }
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        backtrack(1, k, n, combinations, path);
        return combinations;
    }
  
    public static void main(String[] args){
        int k = 3;
        int n = 9;
        Solution sol = new Solution();
        System.out.println("k: " + k + ", n:" + n);
        System.out.println("combinations: " + sol.combinationSum3(k, n));
    }
}
