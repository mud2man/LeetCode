/* DFS: Time:O(e), Space:O(e), where e is the number of edges
 * 1. Have a adjacency list "neighbors", and array "visited" to record if visited before
 * 2. Call DFS recursively, and prevent visit "from" node
 * 3, Check if dfs return true, and number of visited node is n 
 */

import java.util.*;

public class Solution{
    private boolean dfs(int from, int node, boolean[] visited, int[] count, List<Integer>[] adjacencyList){
        if(visited[node] == true){
            return false;
        }
        
        visited[node] = true;
        count[0]++;
        List<Integer> neighbors = adjacencyList[node];
        for(int n: neighbors){
            if(from != n && !dfs(node, n, visited, count, adjacencyList)){
                return false;
            }   
        }
        return true;
    }
    
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != (n - 1)){
            return false;
        }
        
        List<Integer>[] adjacencyList = new ArrayList[n];
        for(int i = 0; i < n; ++i){
            adjacencyList[i] = new ArrayList<>();
        }
        for(int[] edge: edges){
            adjacencyList[edge[0]].add(edge[1]);
            adjacencyList[edge[1]].add(edge[0]);
        }
        
        int[] count = {0};
        boolean[] visited = new boolean[n];
        boolean ret = dfs(-1, 0, visited, count, adjacencyList);
        return (ret & count[0] == n);
    }

    public static void main(String[] args){
        Solution sol;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int n = 5;

        System.out.println("n: " + n);
        System.out.println("edges: ");
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        
        sol = new Solution();    
        System.out.println("is valid tree: " + sol.validTree(n, edges));
    }
}
