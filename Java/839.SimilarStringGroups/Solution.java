/* String: Time:O(n^2 * w), Space:O(n^2). However, we can optimize it with O(n*w*min(n,w^2))
 * 1. Construct the "adjacencyList" by check if "isSimilar" true to determine if there is edge
 * 2. Use DFS to connect all the linked sub-graph, and accumulate count
 */

import java.util.*;


public class Solution{
    private boolean isSimilar(String x, String y){
        int diff = 0;
        for(int i = 0; i < x.length(); ++i){
            diff += (x.charAt(i) != y.charAt(i))? 1: 0;
        }
        return (diff <= 2);
    }
    
    private void dfs(Set<Integer> seen, int curr, Map<Integer, List<Integer>> adjacencyList){
        if(seen.contains(curr)){
            return;
        }
        seen.add(curr);
        for(int next: adjacencyList.getOrDefault(curr, new ArrayList<>())){
            dfs(seen, next, adjacencyList);
        }
    }
    
    public int numSimilarGroups(String[] A) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for(int i = 0; i < A.length; ++i){
            for(int j = i + 1; j < A.length; ++j){
                if(isSimilar(A[i], A[j])){
                    adjacencyList.computeIfAbsent(i, key -> new ArrayList<>()).add(j);
                    adjacencyList.computeIfAbsent(j, key -> new ArrayList<>()).add(i);
                }
            }
        }
        Set<Integer> seen = new HashSet<>();
        int count = 0;
        for(int i = 0; i < A.length; ++i){
            if(!seen.contains(i)){
                dfs(seen, i, adjacencyList);
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] A = {"tars", "rats", "arts", "star"};
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("gropu size:" + sol.numSimilarGroups(A));
    }
}
