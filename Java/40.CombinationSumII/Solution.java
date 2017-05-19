/* DFS + Sort: O(2^n)
 * 1. Sort and merger the original numbers
 * 2. Call the DFS helper recursively
 * 3. Before leaving helper, pop the backtracking log "sum"
 *
 */          

import java.util.*; // Stack

public class Solution {
    private class Node{
        int val;
        int count;
        Node(int x, int y){val = x; count = y;}
    }
    
    public void helper(List<Node> nodes, int target, List<List<Integer>> combinationSums, ArrayList<Integer> sum, int idx){
        int count, val, i;
        
        if(target == 0){
            combinationSums.add(new  ArrayList<Integer>(sum));
            return;
        }
                
        if(idx == nodes.size() || nodes.get(idx).val > target){
            return;
        }
        
        val = nodes.get(idx).val;
        for(count = 0; count <= nodes.get(idx).count; count++){
            helper(nodes, (target - count * val), combinationSums, sum, idx + 1);
            if(target >= (count + 1) * val){
                sum.add(val);
            }
            else{
                break;
            }
        }
        
        //pop
        for(i = 0; i < count ; ++i){
            sum.remove(sum.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinationSums;
        ArrayList<Integer> sum;
        int idx, size, firstVal, count;
        Node node;
        List<Node> nodes;
        
        combinationSums = new ArrayList<List<Integer>>();
        sum = new ArrayList<Integer>();
        idx = 0;
        size = candidates.length;
        nodes = new ArrayList<Node>();
        
        Arrays.sort(candidates);
        
        //merge
        while(idx < size){
            firstVal = candidates[idx];
            count = 0;
            while(idx < size &&  firstVal == candidates[idx]){
                count++;
                idx++;
            }
            nodes.add(new Node(firstVal, count));
        }
        
        helper(nodes, target, combinationSums, sum, 0);
        
        return combinationSums;
    }
  
    public static void main(String[] args){
        Solution sol;
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> combinationSums;

        sol = new Solution();

        System.out.println("candidates: " + Arrays.toString(candidates));
        System.out.println("target: " + target);

        combinationSums = sol.combinationSum2(candidates, target);

        System.out.println("combination sums: ");
        for(List<Integer> sum: combinationSums){
            System.out.println(sum);
        }
    }
}
