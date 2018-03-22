/* DFS with memorization: Time:O(n), Space:O(n)
 * 1. This problem can be solved by findning the nodes not hit any cycle
 * 2. Have a memory array "flags" with the following code. 0: not visited, 1: visiting, 2: finished visiting, -1: can hit cycle
 * 3. Use DFS to find the cycle, return if flags[source] != 0, which means it visited before
 */

import java.util.*;

public class Solution{
    //  0: not visited
    //  1: visiting
    //  2: finished visiting
    // -1: in cycle
    
    private boolean dfs(int source, int[] flags, int[][] graph){
        if(flags[source] == 1 || flags[source] == -1){
            return false;
        }
        else if(flags[source] == 2){
            return true;
        }
        else{
            boolean ret = true;
            flags[source] = 1;
            for(int successor: graph[source]){
                ret = ret & dfs(successor, flags, graph);
            }
            flags[source] = ret? 2: -1;
            return ret;
        }
    }
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int size = graph.length;
        int[] flags = new int[size];
        List<Integer> safeNodes = new ArrayList<Integer>();
        
        for(int i = 0; i < size; ++i){
            if(flags[i] == 0){
                dfs(i, flags, graph);
            }
            
            if(flags[i] != -1){
                safeNodes.add(i);
            }
        }
        
        return safeNodes;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] graph = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        
        System.out.println("sucessors: ");
        for(int[] sucessors: graph){
            System.out.println(Arrays.toString(sucessors));
        }
        System.out.println("eventual safe nodes: " + sol.eventualSafeNodes(graph));
    }
}
