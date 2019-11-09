/* DFS: Time:O(e), Space:O(n + e)
 * 1. Have an array "visited" to record the visited position
 * 2. Have an map "adjList"
 * 3. Travere each node and call dfs if visited[node] == false
 */

import java.util.*;

public class Solution{
   private void dfs(int start, boolean[] visited, Map<Integer, List<Integer>> adjList){
        if(visited[start] == true){
            return;
        }    
        visited[start] = true;
        List<Integer> neighbors = adjList.getOrDefault(start, new ArrayList<>());
        for(int nb: neighbors){
            dfs(nb, visited, adjList);
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge: edges){
            adjList.putIfAbsent(edge[0], new ArrayList<>());
            adjList.putIfAbsent(edge[1], new ArrayList<>());
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; ++i){
            if(visited[i] == false){
                count++;
                dfs(i, visited, adjList);
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        int n;
        int count;
        
        n = 5;

        System.out.println("nodes: 0" + " to " + (n -1));
        System.out.println("edges: ");
        
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        
        sol = new Solution();    
        count = sol.countComponents(n, edges);

        System.out.println("#components: " + count);
    }
}
