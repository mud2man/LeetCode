/* Topological sort: Time:O(e), Space:O(e)
 * 1. Use BFS-based topological sort to take class. Only put class with zero indegree to queue
 * 2. Accumulate count when indegree is zero
 * 3. Return the "level" if count == N. Otherwise, we return -1
 */

import java.util.*;

public class Solution {
    public int minimumSemesters(int N, int[][] relations) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        int[] indegree = new int[N];
        for(int[] relation: relations){
            indegree[relation[1] - 1]++;
            adjacencyList.computeIfAbsent(relation[0] - 1, key -> new HashSet<>()).add(relation[1] - 1);
        }
        
        Deque<Integer> queue = new LinkedList<>();
        int count = 0;
        for(int i = 0; i < N; ++i){
            if(indegree[i] == 0){
                queue.add(i);
                count++;
            }
        }
        
        int level = 0;
        while(!queue.isEmpty()){
            level++;
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int front = queue.pollFirst();
                Set<Integer> nexts = adjacencyList.getOrDefault(front, new HashSet<>());
                for(int next: nexts){
                    indegree[next]--;
                    if(indegree[next] == 0){
                        queue.add(next);
                        count++;
                    }
                }
            }
        }
        return (count == N)? level: -1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] relations = {{1, 3}, {2, 3}};
        int N = 3;
        System.out.println("N:" + N);
        System.out.println("relations:");
        for(int[] relation: relations){
            System.out.println(Arrays.toString(relation));
        }
        System.out.println("minimum number of semesters:" + sol.minimumSemesters(N, relations));
    }
}
