/* Graph: Time:O(n^2 * logn), Space:O(n)
 * 1. We pick the next root of sub-tree given the reachable nodes of current root
 * 2. The next root is the node with the most connect count
 * 3. We need to do 2 validation for 0-way and 2-way
 * 4. 0-way check: check if the reachable nodes of current root contains all ancestors in path, and anysub-tree is 0-way
 * 5. 2-way check: check if reachable nodes of current root# == reachable nodes of its parent#, and if any sub-tree is 2-way while no sub-tree is 0-way
 */          

import java.util.*; // Stack

public class Solution {
    private int dfs(int root, Deque<Integer> path, Map<Integer, Set<Integer>> node2Reachable, boolean[] saw){
        Set<Integer> reachable = node2Reachable.get(root);
        for(int ancestor: path){
            if(!reachable.contains(ancestor)){
                return 0;
            }
        }
        int currReachableCount = node2Reachable.get(root).size();
        int prevReachableCount =(path.isEmpty())? 0: node2Reachable.get(path.peekLast()).size();
        int ways =(currReachableCount == prevReachableCount)? 2: 1;
        saw[root] = true;
        List<int[]> nodeAndReachableCounts = new ArrayList<>();
        for(int child: reachable){
            if(!saw[child]){
                nodeAndReachableCounts.add(new int[]{child, node2Reachable.get(child).size()});
            }
        }
        Collections.sort(nodeAndReachableCounts, (x, y) -> (y[1] - x[1]));
        path.add(root);
        for(int[] nodeAndReachableCount: nodeAndReachableCounts){
            int child = nodeAndReachableCount[0];
            if(!saw[child]){
                ways *= dfs(child, path, node2Reachable, saw);
                if(ways == 0){
                    return 0;
                }
            }
        }
        path.pollLast();
        return (ways > 1)? 2: 1;
    }
    
    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> node2Reachable = new HashMap<>();
        for(int[] pair: pairs){
            node2Reachable.computeIfAbsent(pair[0], key -> new HashSet<>()).add(pair[1]);
            node2Reachable.computeIfAbsent(pair[1], key -> new HashSet<>()).add(pair[0]);
        }
        
        for(Map.Entry<Integer, Set<Integer>> entry: node2Reachable.entrySet()){
            if(entry.getValue().size() == node2Reachable.size() - 1){
                int root =  entry.getKey();
                return dfs(root, new LinkedList<>(), node2Reachable, new boolean[501]);
            }
        }
        return 0;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] pairs = {{1, 2}, {2, 3}};
        System.out.println("pairs:");
        for(int[] pair: pairs){
            System.out.println(Arrays.toString(pair));
        }
        System.out.println("ways#:" + sol.checkWays(pairs));
    }
}
