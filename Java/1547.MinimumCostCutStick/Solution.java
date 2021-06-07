/* Divide and conqouer: Time:O(n^3), Space:O(n^2). It can also be done with dynamic programming
 * 1. Use head2tail2Cost to remember the seen minCoster with head and tail
 * 2. Get the mincost between head and tail by traverse from startIdx to endIdx of cuts using divideConquer
 */

import java.util.*; // Stack


public class Solution {
    private int divideConquer(int head, int tail, int startIdx, int endIdx, int[] cuts,
                              Map<Integer, Map<Integer, Integer>> head2tail2Cost){
        if(startIdx > endIdx){
            return 0;
        }
        if(head2tail2Cost.containsKey(head) && head2tail2Cost.get(head).containsKey(tail)){
            return head2tail2Cost.get(head).get(tail);
        }
        int minCost = Integer.MAX_VALUE;
        for(int i = startIdx; i <= endIdx; i++){
            int leftMinCost = divideConquer(head, cuts[i], startIdx, i - 1, cuts, head2tail2Cost);
            int rightMinCost = divideConquer(cuts[i], tail, i + 1, endIdx, cuts, head2tail2Cost);
            minCost = Math.min(minCost, leftMinCost + rightMinCost + (tail - head));
        }
        head2tail2Cost.computeIfAbsent(head, key -> new HashMap<>()).put(tail, minCost);
        return minCost;
    }
    
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        Map<Integer, Map<Integer, Integer>> head2tail2Cost = new HashMap<>();
        int minCost = Integer.MAX_VALUE;
        for(int i = 0; i < cuts.length; ++i){
            int leftMinCost = divideConquer(0, cuts[i], 0, i - 1, cuts, head2tail2Cost);
            int rightMinCost = divideConquer(cuts[i], n, i + 1, cuts.length - 1, cuts, head2tail2Cost);
            minCost = Math.min(minCost, leftMinCost + rightMinCost + n);
        }
        return minCost;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 7;
        int[] cuts = {1, 3, 4, 5};
        System.out.println("n:" + n + ", cuts:" + Arrays.toString(cuts));
        System.out.println("min cost:" + sol.minCost(n, cuts));
    }
}
