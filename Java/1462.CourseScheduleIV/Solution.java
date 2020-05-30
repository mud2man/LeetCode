/* Topological Sort: Time:O(n^3), Space:O(n^2). We can also use Floyd–Warshall algorithm
 * 1. Visited nodes in topological order, and update node2Ancestors
 * 2. Check if node2Ancestors.get(x).contains(y) to see if the query of (y, x) is true
 */     


import java.util.*; // Stack

public class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        int[] inDegree = new int[n];
        for(int[] prerequisite: prerequisites){
            inDegree[prerequisite[1]]++;
            adjacencyList.computeIfAbsent(prerequisite[0], key -> new ArrayList<>()).add(prerequisite[1]);
        }
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; ++i){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        
        Map<Integer, Set<Integer>> node2Ancestors = new HashMap<>();
        while(!queue.isEmpty()){
            int curr = queue.pollFirst();
            List<Integer> nexts = adjacencyList.getOrDefault(curr, new ArrayList<>());
            Set<Integer> ancestors = node2Ancestors.getOrDefault(curr, new HashSet<>());
            ancestors.add(curr);
            for(int next: nexts){
                node2Ancestors.computeIfAbsent(next, key -> new HashSet<>()).addAll(ancestors);
                inDegree[next]--;
                if(inDegree[next] == 0){
                    queue.add(next);
                }
            }
        }
        
        List<Boolean> ret = new ArrayList<>();
        for(int[] query: queries){
            ret.add((node2Ancestors.containsKey(query[1]) && node2Ancestors.get(query[1]).contains(query[0]))? true: false);
        }
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 3;
        int[][] prerequisites = {{1, 2}, {1, 0}, {2, 0}};
        int[][] queries = {{1, 0}, {1, 2}};
        System.out.println("n:" + n);
        System.out.println("prerequisites:");
        for(int[] prerequisite: prerequisites){
            System.out.println(Arrays.toString(prerequisite));
        }
        System.out.println("queries:");
        for(int[] query: queries){
            System.out.println(Arrays.toString(query));
        }
        System.out.println("queries result:" + sol.checkIfPrerequisite(n, prerequisites, queries));
    }
}
