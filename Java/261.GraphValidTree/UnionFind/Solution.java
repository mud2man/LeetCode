/* Union and Find: Time:O(e), Space:O(n), where e is the number of edges
 * 1. Return false if edges.length != (n - 1)
 * 2. Use union and find to check if the added edge belongs to the same component
 */

import java.util.*;

public class Solution{
        private int find(int[] roots, int node){
        if(roots[node] == node){
            return node;
        }
        else{
            //compression
            roots[node] = roots[roots[node]];
            return find(roots, roots[roots[node]]);
        }
    }
    
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != (n - 1)){
            return false;
        }
        
        int[] roots = new int[n];
        int[] ranks = new int[n];
        for(int i = 0; i < n; ++i){
            roots[i] = i;
        }
        
        for(int[] edge: edges){
            int root0 = find(roots, edge[0]);
            int root1 = find(roots, edge[1]);
            
            if(root0 == root1){
                return false;
            }
            else{
                //union
                if(ranks[root0] == ranks[root1]){
                    ranks[root0]++;
                    roots[root1] = root0;
                }
                else if(ranks[root0] < ranks[root1]){
                    roots[root0] = root1;
                }
                else{
                    roots[root1] = root0;
                } 
            }
        }
        
        return true;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int n = 5;

        System.out.println("n: " + n);
        System.out.println("edges: ");
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        
        sol = new Solution();    
        System.out.println("is valid tree: " + sol.validTree(n, edges));
    }
}
