/* BFS + topological sort: Time:O(n), Space:O(n)
 * 1. Let tree T' be the tree after all the leaves were cutted off from the original tree T, then MHT height = MHT' height - 1
 * 2. Otherwise, the root of MHT cannot be true
 * 3. Use BFS to shrink the graph with the initial queue contains all leaves
 * 4. Add the node into queue only if the degree is equal to 1
 * 5. The last group of nodes in queue is the answer, which can construct the MHT
 */

import java.util.*;

public class Solution{
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<Integer, List<Integer>>();
        int[] degree = new int[n];
        
        for(int[] edge: edges){
            adjacencyList.putIfAbsent(edge[0], new ArrayList<Integer>());
            adjacencyList.get(edge[0]).add(edge[1]);
            degree[edge[0]]++;
            adjacencyList.putIfAbsent(edge[1], new ArrayList<Integer>());
            adjacencyList.get(edge[1]).add(edge[0]);
            degree[edge[1]]++;
        }
        
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < degree.length; ++i){
            if(degree[i] == 1){
                queue.add(i);
            }
        }
        
        //BFS
        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(0);
        while(!queue.isEmpty()){
            int size = queue.size();
            candidates = new ArrayList<Integer>();
            for(int i = 0; i < size; i++){
                int node = queue.pollFirst();
                candidates.add(node);
                if(adjacencyList.containsKey(node)){
                    for(int neighbor: adjacencyList.get(node)){
                        degree[neighbor]--;
                        if(degree[neighbor] == 1){
                            queue.add(neighbor);
                        }
                    }  
                }
            }
        }
        
        return candidates;
    }

    public static void main(String[] args){
        Solution sol;
        int pathNum;
        int n = 6;
        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};

        sol = new Solution();
        System.out.println("n:" + n);
        System.out.print("edges:");
        for(int[] edge: edges){
            System.out.print(Arrays.toString(edge) + ", ");
        }
        System.out.println("");
        System.out.println("MHT: " + sol.findMinHeightTrees(n, edges));
    }
}
