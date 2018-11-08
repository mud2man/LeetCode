/* Backtrack: O((n^0.5)!), Most n^0.5 factors, so combination# is (n^0.5)!
 * 1. In the backtracker, add {path + remain} to factors, since we keep the remain > the last number of path
 * 2. Iterate all the possible factors from start to n^0.5
 * 3. Before enter the next-level backtracker, push the factor into factors
 * 4. Afetr leave the next-level backtracker, pop the last factor from factors
 */

import java.util.*;

public class Solution{
   private void backtrack(int start, int remain, Deque<Integer> path, List<List<Integer>> factors){
        List<Integer> factor = new ArrayList<>(path);
        factor.add(remain);
        factors.add(factor);
        
        for(int i = start; i <= (int)Math.sqrt(remain); ++i){
            if(remain % i == 0){
                path.add(i);
                backtrack(i, remain / i, path, factors);
                path.pollLast();
            }
        }
    }
    
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> factors = new ArrayList<>();
        for(int i = 2; i <= (int)Math.sqrt(n); ++i){
            if(n % i == 0){
                Deque<Integer> path = new LinkedList<>();
                path.add(i);
                backtrack(i, n / i, path, factors);
            }
        }
        return factors;
    }
  
    public static void main(String[] args){
        Solution sol;
        int n = 32;

        sol = new Solution();

        System.out.println("n: " + n);
        List<List<Integer>> factorsList = sol.getFactors(n);
        System.out.println("factor list:");
        for(List<Integer> factors: factorsList){
            System.out.println(factors);
        }
    }
}
