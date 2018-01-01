/* DFS: O(n), We can use union-find with a much shorter code, although the time complexity is O(n*logn)
 * 1. Create an adjacentList, priority 2D array, visited array
 * 2. If visited[curr] == 1, means the curr node is visisted, cycle existed. Then find the edge with max priority from the stack
 * 3. Else, call dfs with the next position list. 
 * 4. However, if dfs return non-null, retunr the edge
 */

import java.util.*;

public class Solution{
    private int[] dfs(int from, int curr, Map<Integer, List<Integer>> adjacentList, int[][] priority, int[] visited, Stack<Integer> stack){

        if(visited[curr] == 1){
            int end = curr;
            int start = stack.pop();
            int[] edge = new int[]{Math.min(start, end), Math.max(start, end)};
            int maxPriority = priority[end][start];
            while(start != curr){
                end = start;
                start = stack.pop();
                if(maxPriority < priority[end][start]){
                    maxPriority = priority[end][start];
                    edge[0] = Math.min(start, end);
                    edge[1] = Math.max(start, end);
                }
            }
            return edge;
        }
    
        visited[curr]++;
        stack.push(curr);
        List<Integer> nextPositions = adjacentList.get(curr);
        for(int nextPosition: nextPositions){
            if(nextPosition != from){
                int[] edge = dfs(curr, nextPosition, adjacentList, priority, visited, stack);
                if(edge != null){
                    return edge; 
                }
            }
        }
        stack.pop();
        visited[curr]++;
        return null;
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> adjacentList = new HashMap<Integer, List<Integer>>();
        int[][] priority = new int[edges.length + 1][edges.length + 1];
        for(int i = 0; i < edges.length; ++i){
            int start = edges[i][0];
            int end = edges[i][1];
            if(!adjacentList.containsKey(start)){
                adjacentList.put(start, new ArrayList<Integer>());
            }
            adjacentList.get(start).add(end);
            priority[start][end] = i + 1;
            
            if(!adjacentList.containsKey(end)){
                adjacentList.put(end, new ArrayList<Integer>());
            }
            adjacentList.get(end).add(start);
            priority[end][start] = i + 1;
        }
        
        int[] visited = new int[edges.length + 1];
        Stack<Integer> stack = new Stack<Integer>();
        int[] addEdge = dfs(-1, edges[0][0], adjacentList, priority, visited, stack);
        return addEdge;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};

        System.out.println("edges: ");
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        
        sol = new Solution();    
        int[] redundantEdge = sol.findRedundantConnection(edges);
        System.out.println("redundantEdge: " + Arrays.toString(redundantEdge));
    }
}
