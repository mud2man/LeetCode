/* DFS: Time:O(26 * n), Space:O(26 * n)
 * 1. Call dfs recursively which return char2Count
 * 2. Update counts in each dfs
 */     

import java.util.*; // Stack

public class Solution {
    private int[] dfs(int from, int curr, Map<Integer, List<Integer>> adjacencyList, int[] counts, String labels){
        int[] char2Count = new int[26];
        List<Integer> children = adjacencyList.get(curr);
        for(int child: children){
            if(from != child){
                int[] localChar2Count = dfs(curr, child, adjacencyList, counts, labels);
                for(int i = 0; i < 26; i++){
                    char2Count[i] += localChar2Count[i];
                }
            }
        }
        char label = labels.charAt(curr);
        char2Count[label - 'a']++;
        counts[curr] = char2Count[label - 'a'];
        return char2Count;
    }
    
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for(int[] edge: edges){
            adjacencyList.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
            adjacencyList.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(edge[0]);
        }
        int[] counts = new int[n];
        dfs(-1, 0, adjacencyList, counts, labels);
        return counts;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 7;
        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        String labels = "abaedcd";
        System.out.println("n:" + n);
        System.out.print("edges:");
        for(int[] edge: edges){
            System.out.print(Arrays.toString(edge) + ",");
        }
        System.out.println("");
        System.out.println("labels:" + labels);
        System.out.println("number of nodes with the same label:" + Arrays.toString(sol.countSubTrees(n, edges, labels)));
    }
}
