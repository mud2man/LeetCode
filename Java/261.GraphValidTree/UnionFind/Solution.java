/* Union and Find: Time:O(e), Space:O(n), where e is the number of edges
 * 1. Return false if edges.length != (n - 1)
 * 2. Use union and find to check if the added edge belongs to the same component
 */

import java.util.*;

public class Solution{
    private int getRoot(int node, int[] root){
        if(root[node] == node){
            return node;
        }else{
            root[node] = root[root[node]];
            return getRoot(root[root[node]], root);
        }
    }
    
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1){
            return false;
        }
        
        int[] root = new int[n];
        int[] rank = new int[n];
        for(int i = 0; i < n; ++i){
            root[i] = i;
        }
        for(int[] edge: edges){
            int root0 = getRoot(edge[0], root);
            int root1 = getRoot(edge[1], root);
            if(root0 == root1){
                return false;
            }else{
                if(rank[root0] == rank[root1]){
                    root[root1] = root0;
                    rank[root0]++;
                }else if(rank[root0] > rank[root1]){
                    root[root1] = root0;
                }else{
                    root[root0] = root1;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int n = 5;
        System.out.println("n: " + n);
        System.out.println("edges: ");
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        System.out.println("is valid tree: " + sol.validTree(n, edges));
    }
}
