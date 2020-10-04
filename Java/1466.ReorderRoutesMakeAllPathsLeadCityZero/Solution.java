/* DFS: Time:O(n), Space:O(n). It can also be resolved by BFS
 * 1. Have "from2Tos" and "to2Froms" to store directed edges
 * 2. Use dfs to visited each node, and accumulate 1 when seeing outgoing edge
 */     

import java.util.*; // Stack

public class Solution {
    private int dfs(int prev, int curr, Map<Integer, List<Integer>> from2Tos, Map<Integer, List<Integer>> to2Froms){
        int count = 0;
        List<Integer> tos = from2Tos.getOrDefault(curr, new ArrayList<>());
        for(int to: tos){
            if(to != prev){
                count += (dfs(curr, to, from2Tos, to2Froms) + 1);
            }
        }
        List<Integer> froms = to2Froms.getOrDefault(curr, new ArrayList<>());
        for(int from: froms){
            if(from != prev){
                count += dfs(curr, from, from2Tos, to2Froms);
            }
        }
        return count;
    }
    
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Integer>> from2Tos = new HashMap<>();
        Map<Integer, List<Integer>> to2Froms = new HashMap<>();
        for(int[] connection: connections){
            from2Tos.computeIfAbsent(connection[0], key -> new ArrayList<>()).add(connection[1]);
            to2Froms.computeIfAbsent(connection[1], key -> new ArrayList<>()).add(connection[0]);
        }
        return dfs(-1, 0, from2Tos, to2Froms);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        int n = 6;
        System.out.println("n:" + n);
        System.out.print("connections:");
        for(int[] connection: connections){
            System.out.print(Arrays.toString(connection) + ", ");
        }
        System.out.println();
        System.out.println("minimum reorder routes:" + sol.minReorder(n, connections));
    }
}
