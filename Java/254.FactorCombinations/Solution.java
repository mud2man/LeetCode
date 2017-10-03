/* Backtrack: O(logn * n^0.5), because n^0.5*((0.5n)^0.5)*((0.25n)^0.5)
 * 1. In the backtracker, if the last factor is smaller or equal to n, put it factor list
 * 2. Iterate all the possible factors from last factor to n^0.5
 * 3. Before enter the next-level backtracker, push the factor into factors
 * 4. Afetr leave the next-level backtracker, pop the last factor from factors
 */

import java.util.*;

public class Solution{
    private void backtracker(LinkedList<Integer> factors, List<List<Integer>> factorsList, int n){
        int lastFactor = factors.peekLast();
        
        if(lastFactor <= n){
            LinkedList<Integer> addFactors = new LinkedList<Integer>(factors);
            addFactors.add(n);
            factorsList.add(addFactors);
        }
        
        for(int factor = lastFactor; factor <= (int)Math.sqrt(n); ++factor){
            if(n % factor == 0){
                //push
                factors.add(factor);
                
                backtracker(factors, factorsList , n / factor);
                
                //pop
                factors.pollLast();
            }
        }
    }
    
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> factorsList = new LinkedList<List<Integer>>();
        for(int factor = 2; factor <= (int)Math.sqrt(n); ++factor){
            if(n % factor == 0){
                LinkedList<Integer> factors = new LinkedList<Integer>();
                factors.add(factor);
                backtracker(factors, factorsList, n / factor);
            }
        }
        return factorsList;
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
