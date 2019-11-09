/* Disjoint sets: Time:O(e), Space:O(n)
 * 1. Create an array "roots", roots[i] store the root node for node i
 * 2. Create an array "ranks", ranks[i] store the rank for node i
 * 3. Union: merge by rank, the root which has higher rank keeps the same
 * 4. Find: loop until roots[node] == node
 * 5. Path compression: roots[node] = roots[roots[node]];
 */

import java.util.*;

public class Solution{
    private int getRoot(int[] roots, int node){
        if(roots[node] == node){
            return node;
        }    
        //compression
        roots[node] = roots[roots[node]];
        return getRoot(roots, roots[node]);
    }
    
    public int countComponents(int n, int[][] edges) {
        int count = n;
        int[] roots = new int[n];
        int[] ranks = new int[n];
        
        for(int i = 0; i < n; ++i){
            roots[i] = i;
        }
        
        for(int[] edge: edges){
            int leftNode = edge[0];
            int rightNode = edge[1];
            int leftRoot = getRoot(roots, leftNode);
            int rightRoot = getRoot(roots, rightNode);
            
            if(leftRoot != rightRoot){
                count--;
                if(ranks[leftRoot] == ranks[rightRoot]){
                    roots[leftRoot] = rightRoot;
                    ranks[rightRoot]++;
                }else if(ranks[leftRoot] < ranks[rightRoot]){
                    roots[leftRoot] = rightRoot;
                }else{
                    roots[rightRoot] = leftRoot;
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        int n;
        int count;
        
        n = 5;

        System.out.println("nodes: 0" + " to " + (n -1));
        System.out.println("edges: ");
        
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        
        sol = new Solution();    
        count = sol.countComponents(n, edges);

        System.out.println("#components: " + count);
    }
}
