/* BFS: Time:O(n), Space:O(n)
 * 1. longestPaths[i] = the longest path of node i
 * 2. Use degree[i] to determine if node i is leaf, and we start BFS from leaves
 * 3. Keep update max by max(max, currLongestPath + 1 + longestPaths[next], and longestPaths[next] by max(longestPaths[next], currLongestPath + 1)
 * 4. We only put node which becomes leaf to queue
 * 5. Repeat until queue is empty
 */

import java.util.*;

public class Solution {
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        int[] longestPaths = new int[n];
        int[] degree = new int[n];
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        for(int[] edge: edges){
            degree[edge[0]]++;
            degree[edge[1]]++;
            adjacencyList.computeIfAbsent(edge[0], key -> new HashSet<>()).add(edge[1]);
            adjacencyList.computeIfAbsent(edge[1], key -> new HashSet<>()).add(edge[0]);
        }
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; ++i){
            if(degree[i] == 1){
                queue.add(i);
            }
        }
        
        int max = 0;
        while(!queue.isEmpty()){
            int curr = queue.pollFirst();
            int currLongestPath = longestPaths[curr];
            Set<Integer> nexts = adjacencyList.get(curr);
            for(int next: nexts){
                max = Math.max(max, currLongestPath + 1 + longestPaths[next]);
                longestPaths[next] = Math.max(longestPaths[next], currLongestPath + 1);
                degree[curr]--;
                degree[next]--;
                adjacencyList.get(curr).remove(next);
                adjacencyList.get(next).remove(curr);
                if(degree[next] == 1){
                    queue.add(next);
                }
            }
        }
        return max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] edges = {{0, 1}, {0, 2}};
        System.out.println("edges:");
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        System.out.println("diameter:" + sol.treeDiameter(edges));
    }
}
