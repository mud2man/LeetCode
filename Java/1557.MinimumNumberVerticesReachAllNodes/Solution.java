/* Time:O(n), Space:O(n)
 * 1.Get the nodes with in-degree = 0
 */     

import java.util.*; // Stack

public class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] indegree = new int[n];
        for(List<Integer> edge: edges){
            indegree[edge.get(1)]++;
        }
        List<Integer> smallestSetOfVertices = new ArrayList<>();
        for(int i = 0; i < n; ++i){
            if(indegree[i] == 0){
                smallestSetOfVertices.add(i);
            }
        }
        return smallestSetOfVertices;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.asList(0, 1));
        edges.add(Arrays.asList(0, 2));
        edges.add(Arrays.asList(2, 5));
        edges.add(Arrays.asList(3, 4));
        edges.add(Arrays.asList(4, 2));
        int n = 6;
        System.out.println("n:" + n);
        System.out.println("edges:" + edges);
        System.out.println("smallest set of nodes:" + sol.findSmallestSetOfVertices(n, edges));
    }
}
