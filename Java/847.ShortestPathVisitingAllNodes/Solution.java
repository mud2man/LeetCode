/* BFS + Bit manupulation (NPC problem): Time:O(e!), Space:O(e!).
 * 1. Let status = (currentNode << n) | (visitedStatus);
 * 2. If visitedStatus == (1 << n) - 1, then return from the loop since all nodes are visited
 * 3. Let node2Visited[node][visited] to record the visited status w.r.t the current node
 * 4. We skip loop if node2Visited[next][nextVisited] == true, since there is no point to revisit as long as the cost is increasing
 */

import java.util.*; // Stack


public class Solution {
        public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int visitedAll = (1 << n) - 1;
        boolean[][] node2Visited = new boolean[n][1 << n];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int state = (i << n) | (1 << i);
            queue.add(state);
            node2Visited[i][1 << i] = true;
        }
    
        int cost = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int front = queue.pollFirst();
                int node = front >> n;
                int visited = front & visitedAll;
                if (visited == visitedAll){
                    return cost;  
                } 
                for (int next: graph[node]) {
                    int nextVisited = (1 << next) | visited;
                    int newState = (next << n) | nextVisited;
                    if (!node2Visited[next][nextVisited]) {
                        queue.add(newState);
                        node2Visited[next][nextVisited] = true;
                    }
                }
            }
            cost++;
        }
        return -1;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] graph = {{1, 2, 3}, {0}, {0}, {0}};
        System.out.println("graph:");
        for(int[] row: graph){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("shortest path:" + sol.shortestPathLength(graph));
    }
}
