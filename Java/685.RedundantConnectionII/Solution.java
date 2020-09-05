/* Union & Find: Time:O(n), Space:O(n). LeetCode has DFS solution
 * 1. Put the two edges pointing to the same node to candidates, or the one forming cycle to candidate
 * 2. If the size of candidates is 1(the additional edge point to root), return the edge in candidates
 * 3. Otherwise, return the edge which can still link all nodes to a single group after excluding it
 */     

import java.util.*; // Stack

public class Solution {
    private int getRoot(int[] root, int node){
        if(root[node] == node){
            return node;
        }
        //compression
        root[node] = root[root[node]];
        return getRoot(root, root[node]);
    }
    
    private int join(int[] rank, int[] root, int from, int to, int groupNum, List<int[]> candidates){
        int rootFrom = getRoot(root, from);
        int rootTo = getRoot(root, to);
        if(rootFrom == rootTo){
            candidates.add(new int[]{from, to});
            return groupNum;
        }
        //join by rank
        if(rank[rootFrom] == rank[rootTo]){
            rank[rootFrom]++;
            root[rootTo] = rootFrom;
        }else if(rank[rootFrom] > rank[rootTo]){
            root[rootTo] = rootFrom;
        }else{
            root[rootFrom] = rootTo;
        }
        return groupNum - 1;
    }
    
    private int getGroupNum(int[][] edges, int[] excludeEdge){
        int groupNum = edges.length;
        int[] rank = new int[edges.length];
        int[] root = new int[edges.length];
        Arrays.setAll(root, idx -> idx);
        for(int[] edge: edges){
            if(excludeEdge[0] == edge[0] - 1 && excludeEdge[1] == edge[1] - 1){
                continue;
            }
            groupNum = join(rank, root, edge[0] - 1, edge[1] - 1, groupNum, new ArrayList<>());
        }
        return groupNum;
    }
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] rank = new int[edges.length];
        int[] root = new int[edges.length];
        Arrays.setAll(root, idx -> idx);
        Map<Integer, List<Integer>> child2Parents = new HashMap<>();
        List<int[]> candidates = new ArrayList<>();
        int groupNum = edges.length;
        for(int[] edge: edges){
            child2Parents.computeIfAbsent(edge[1] - 1, key -> new ArrayList<>()).add(edge[0] - 1);
            if(child2Parents.get(edge[1] - 1).size() == 2){
                candidates.add(new int[]{child2Parents.get(edge[1] - 1).get(0), edge[1] - 1});
                candidates.add(new int[]{child2Parents.get(edge[1] - 1).get(1), edge[1] - 1});
                break;
            }
            groupNum = join(rank, root, edge[0] - 1, edge[1] - 1, groupNum, candidates);
        }
        
        if(candidates.size() == 1){
            // the additional edge pointing to the root
            return new int[]{candidates.get(0)[0] + 1, candidates.get(0)[1] + 1};
        }else if(getGroupNum(edges, candidates.get(1)) == 1){
            return new int[]{candidates.get(1)[0] + 1, candidates.get(1)[1] + 1};
        }else{
            return new int[]{candidates.get(0)[0] + 1, candidates.get(0)[1] + 1};
        }
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][]edges = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println("edges:");
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        System.out.println("redundant edge:" + Arrays.toString(sol.findRedundantDirectedConnection(edges)));
    }
}
