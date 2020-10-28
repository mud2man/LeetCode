/* DFS: Time:O(n), Space:O(n)
 * 1. Construct parent2Children and use dfs to return the latency from any given root of sub-tree
 */     

import java.util.*; // Stack

public class Solution {
    private int dfs(int root, Map<Integer, List<Integer>> parent2Children, int[] informTime){
        if(!parent2Children.containsKey(root)){
            return 0;
        }
        List<Integer> children = parent2Children.get(root);
        int latency = 0;
        for(int child: children){
            latency = Math.max(latency, informTime[root] + dfs(child, parent2Children, informTime));
        }
        return latency;
    }
    
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> parent2Children = new HashMap<>();
        for(int child = 0; child < manager.length; ++child){
            parent2Children.computeIfAbsent(manager[child], key -> new ArrayList<>()).add(child);
        }
        return dfs(headID, parent2Children, informTime);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 6;
        int headID = 2;
        int[] manager = {2, 2, -1, 2, 2, 2};
        int[] informTime = {0, 0, 1, 0, 0, 0};
        System.out.println("n:" + n + ", headID:" + headID + ", manager:" + Arrays.toString(manager) + ", informTime:" + Arrays.toString(informTime));
        System.out.println("number of minutes:" + sol.numOfMinutes(n, headID, manager, informTime));
    }
}
