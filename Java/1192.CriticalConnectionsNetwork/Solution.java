/* DFS: Time:O(e), Space:O(e)
 * 1. Use dfs to visit all nodes and returned the cycleyd end with least level if possible
 * 2. We have "node2Degree" to record the in-out number for each node. cycled end "i" doesn't coung if node2Degree[i] = 0
 * 3. The edge(i -> j) is non critical if dfs(j) has cycleyd end returned
 * 4. We do dfs by polling adjacencyList.get(i), so we can prevent duplicated visiting
 *
 * Example:
 *      2
 *     / |
 *    /  |
 *   1   |
 *   | \ |
 *   |  \0--4(pseudo) 
 *   3
 *          <-(return:0)      <-(return:0)     <-(return:0)
 * path:  0--------------->1--------------->2--------------->0 
 *                         \   
 *                          \ <-(return null)
 *                           -------------->3  
 */     


import java.util.*; // Stack

public class Solution {
    int n;
    private long hash(int from, int to){
        int left =(from < to)? from: to;
        int right =(from < to)? to: from;
        return (long)left + (long)right * (long)n;
    }
    
    private int dfs(int curr, int level, Set<Integer> visited, Map<Integer, Deque<Integer>> adjacencyList, 
                    Set<Long> visitedEdges, Set<Long> nonCriticals, Map<Integer, Integer> node2Level, int[] node2Degree){
        node2Level.putIfAbsent(curr, level);
        int earliestCycleEnd =visited.contains(curr)? curr: n;
        visited.add(curr);
        Deque<Integer> nexts = adjacencyList.get(curr);
        while(!nexts.isEmpty()){
            int next = nexts.pollFirst();
            long edge = hash(curr, next);
            if(!visitedEdges.contains(edge)){
                visitedEdges.add(edge);
                node2Degree[curr]--;
                node2Degree[next]--;
                int cycleEnd = dfs(next, level + 1, visited, adjacencyList, visitedEdges, nonCriticals, node2Level, node2Degree);
                node2Degree[curr]--;
                node2Degree[next]--;
                earliestCycleEnd =(node2Level.get(earliestCycleEnd) > node2Level.get(cycleEnd))? cycleEnd: earliestCycleEnd;
                if(cycleEnd != n && node2Degree[cycleEnd] > 0){
                    nonCriticals.add(hash(curr, next));
                }
            }
        }
        return earliestCycleEnd;
    }
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.n = n;
        int[] node2Degree = new int[n + 1];
        Map<Integer, Deque<Integer>> adjacencyList = new HashMap<>();
        connections.add(Arrays.asList(0, n));
        for(List<Integer> connection: connections){
            node2Degree[connection.get(0)]+=2;
            node2Degree[connection.get(1)]+=2;
            adjacencyList.computeIfAbsent(connection.get(0), key -> new LinkedList<>()).add(connection.get(1));
            adjacencyList.computeIfAbsent(connection.get(1), key -> new LinkedList<>()).add(connection.get(0));
        }
        
        Set<Long> nonCriticals = new HashSet<>();
        Map<Integer, Integer> node2Level = new HashMap<>();
        node2Level.put(n, n + 1);
        dfs(n, 0, new HashSet<>(), adjacencyList, new HashSet<>(), nonCriticals, node2Level, node2Degree);
        List<List<Integer>> criticals = new ArrayList<>();
        for(List<Integer> connection: connections){
            if(!nonCriticals.contains(hash(connection.get(0), connection.get(1))) && connection.get(1) != n){
                criticals.add(Arrays.asList(connection.get(0), connection.get(1)));
            }
        }
        return criticals;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 4;
        List<List<Integer>> connections = new ArrayList<>(Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2), Arrays.asList(2, 0), Arrays.asList(1, 3)));
        System.out.println("n:" + n);
        System.out.println("connections:" + connections);
        System.out.println("critical connections:" + sol.criticalConnections(n, connections));
    }
}
