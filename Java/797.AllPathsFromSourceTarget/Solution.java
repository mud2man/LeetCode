/* DFS: Time:O(e*n), Space:O(e), where e is edge number, and n is node number
 * 1. Visit the nodes recursively
 */

import java.util.*;

public class Solution{
    private void dfs(LinkedList<Integer> path, int source, int target, int[][] graph, List<List<Integer>> paths){
        path.add(source);
        if(source == target){
            paths.add(new ArrayList(path));
            path.pollLast();
            return;
        }
        
        for(int sucessor: graph[source]){
            dfs(path, sucessor, target, graph, paths);
        }
        path.pollLast();
    }
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<List<Integer>>();
        LinkedList<Integer> path = new LinkedList<Integer>();
        dfs(path, 0, graph.length - 1, graph, paths);
        return paths;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        sol = new Solution();

        System.out.println("graph: ");
        for(int[] row: graph){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("paths: " + sol.allPathsSourceTarget(graph));

    }
}
