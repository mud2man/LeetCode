/* BFS: Time:O(n), Space:O(n). LeetCode has one-pass solution
 * 1. Scan from leaves where indegree[leave] = 0
 * 2. Accumulate count with counts[leave] if value[leave] == 0. Otherwise, accumulate counts[parent[leave]] with counts[leave]
 * 3. Accumulate value[parent[leave]] with value[leave]
 */

import java.util.*;

/* Definition for binary tree */
public class Solution{
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        int[] counts = new int[nodes];
        Arrays.fill(counts, 1);
        Set<Integer> queue = new HashSet<>();
        for(int i = 0; i < nodes; ++i){
            queue.add(i);
        }
        int[] indegree = new int[nodes];
        for(int i = 0; i < nodes; ++i){
            queue.remove(parent[i]);
            if(parent[i] != -1){
                indegree[parent[i]]++;
            }
        }
        
        int count = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            Set<Integer> nextQueue = new HashSet<>();
            for(int leave: queue){
                if(value[leave] == 0){
                    count += counts[leave];
                }
                if(parent[leave] != -1){
                    counts[parent[leave]] += (value[leave] != 0)? counts[leave]: 0;
                    value[parent[leave]] += value[leave];
                    indegree[parent[leave]]--;
                    if(indegree[parent[leave]] == 0){
                        nextQueue.add(parent[leave]);
                    }
                }
            }
            queue = nextQueue;
        }
        return nodes - count;
    }
  
    public static void main(String[] args){
        int nodes = 7;
        int[] parent = {-1, 0, 0, 1, 2, 2, 2};
        int[] value = {1, -2, 4, 0, -2, -1, -1};
        Solution sol = new Solution();
        System.out.println("nodes: " + nodes);
        System.out.println("parent: " + Arrays.toString(parent));
        System.out.println("value: " + Arrays.toString(value));
        System.out.println("remaining nodes: " + sol.deleteTreeNodes(nodes, parent, value));
    }
}
