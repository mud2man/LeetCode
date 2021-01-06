/* Greedy and UnionFind: Time:O(nlogn), Space:O(n)
 * 1. Have a list of edges = {house1, house2, cost}, where the edge of well is {house1, house2, cost}
 * 2. Sort edges by cost
 * 3. Use "hasWater" to record if node has water. i.e. hasWater[root] = true, means node root has water
 * 4. Ther are 2 cases for a valid edge: 1)edges[1] == edges[2] and !hasWater[root] 2)edges[1] != edges[2] and neither root0 nor root1 has water
 * 5. One valid edge can contribute one node to have water. So we find the first n valid edges
 * 6. The first n valid edges can have the minimum cost to distribute water
 */         

import java.util.*;

public class Solution {
    private int getRoot(int node, int[] roots){
        if(roots[node] == node){
            return node;
        }
        roots[node] = roots[roots[node]]; //compression
        return getRoot(roots[node], roots);
    }
    
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        List<int[]> edges = new ArrayList<>();
        for(int i = 0; i < wells.length; i++){
            edges.add(new int[]{i, i, wells[i]});
        }
        for(int[] pipe: pipes){
            edges.add(new int[]{pipe[0] - 1, pipe[1] - 1, pipe[2]});
        }
        Collections.sort(edges, (x, y) -> (x[2] - y[2]));
        
        boolean[] hasWater = new boolean[n];
        int[] ranks = new int[n];
        int[] roots = new int[n];
        for(int i = 0; i < roots.length; i++){
            roots[i] = i;
        }
        
        int needWaterCount = n;
        int cost = 0;
        for(int i = 0; i < edges.size() && needWaterCount > 0; i++){
            if(edges.get(i)[0] == edges.get(i)[1]){
                int root = getRoot(edges.get(i)[0], roots);
                if(!hasWater[root]){
                    hasWater[root] = true;
                    needWaterCount--;
                    cost += edges.get(i)[2];
                }
            }else{
                int root0 = getRoot(edges.get(i)[0], roots);
                int root1 = getRoot(edges.get(i)[1], roots);
                if(root0 != root1 && (!hasWater[root0] || !hasWater[root1])){
                    if(ranks[root0] >= ranks[root1]){
                        ranks[root0] +=(ranks[root0] == ranks[root1])? 1: 0;
                        roots[root1] = root0;
                        hasWater[root0] =(hasWater[root0] || hasWater[root1])? true : false;
                    }else if(ranks[root0] < ranks[root1]){
                        roots[root0] = root1;
                        hasWater[root1] =(hasWater[root0] || hasWater[root1])? true : false;
                    }
                    needWaterCount--;
                    cost += edges.get(i)[2];
                }
            }
        }
        return cost;
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        int n = 3;
        int[] wells = {1, 2, 2}; 
        int[][] pipes = {{1, 2, 1}, {2, 3, 1}};
        System.out.println("n:" + n);
        System.out.println("wells:" + Arrays.toString(wells));
        System.out.print("pipes:");
        for(int[] pipe: pipes){
            System.out.print(Arrays.toString(pipe) + ", ");
        }
        System.out.println("\nmin cost: " + sol.minCostToSupplyWater(n, wells, pipes));
    }
}
