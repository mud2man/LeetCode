/* DFS + Map: Time:O(n), Space:O(n)
 * 1. Use getSumAndCount to get node2SuccessorNumber and the sum of distances of node 0
 * 2. Vist nodes from node 0 and assign sumOfDistances[curr] with prevSum - successorNumber + predecessorNumber - 1;
 */     

import java.util.*; // Stack

public class Solution {
    private int[] getSumAndCount(int prev, int curr, Map<Integer, List<Integer>> adjacentList, Map<Integer, Integer> node2SuccessorNumber){
        List<Integer> children = adjacentList.getOrDefault(curr, new ArrayList<>());
        int[] totalSumAndCount = new int[2];
        for(int child: children){
            if(child != prev) {
                int[] sumAndCount = getSumAndCount(curr, child, adjacentList, node2SuccessorNumber);
                totalSumAndCount[0] += sumAndCount[0];
                totalSumAndCount[1] += sumAndCount[1];
            }
        }
        node2SuccessorNumber.put(curr, totalSumAndCount[1]);
        totalSumAndCount[0] += totalSumAndCount[1];
        totalSumAndCount[1]++;
        return totalSumAndCount;
    }
    
    private void dfs(int n, int prev, int curr, int[] sumOfDistances, Map<Integer, List<Integer>> adjacentList, Map<Integer, Integer> node2SuccessorNumber){
        if(prev != -1){
            int prevSum = sumOfDistances[prev];
            int successorNumber = node2SuccessorNumber.getOrDefault(curr, 0);
            int predecessorNumber = n - successorNumber - 1;
            sumOfDistances[curr] = prevSum - successorNumber + predecessorNumber - 1;
        }
        
        List<Integer> children = adjacentList.getOrDefault(curr, new ArrayList<>());
        for(int child: children){
            if(child != prev){
                dfs(n, curr, child, sumOfDistances, adjacentList, node2SuccessorNumber);
            }
        }
    }
    
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        Map<Integer, Integer> node2SuccessorNumber = new HashMap<>();
        Map<Integer, List<Integer>> adjacentList = new HashMap<>();
        for(int[] edge: edges){
            adjacentList.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
            adjacentList.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(edge[0]);
        }
        int[] sumAndCount = getSumAndCount(-1, 0, adjacentList, node2SuccessorNumber);
        int[] sumOfDistances = new int[N];
        sumOfDistances[0] = sumAndCount[0];
        dfs(N, -1, 0, sumOfDistances, adjacentList, node2SuccessorNumber);
        return sumOfDistances;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 6;
        int[][] edges = {{0,1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        System.out.println("N:" + N);
        System.out.print("edges:");
        for(int[] edge: edges){
            System.out.print(Arrays.toString(edge) + ",");
        }
        System.out.println("");
        System.out.println("sum of distances:" + Arrays.toString(sol.sumOfDistancesInTree(N, edges)));
    }
}
