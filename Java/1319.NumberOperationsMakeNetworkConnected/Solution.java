/* Union find: Time:O(n), Space:O(n)
 */

import java.util.*; // Stack


public class Solution {
    private int find(int[] root, int node){
        if(root[node] == node){
            return node;
        }
        //compression
        root[node] = root[root[node]];
        return find(root, root[node]);
    }
    
    public int makeConnected(int n, int[][] connections) {
        int[] rank = new int[n];
        int[] root = new int[n];
        for(int i = 0; i < n; i++){
            root[i] = i;
        }
        
        int budget = 0;
        int group = n;
        for(int[] connection: connections){
            int a = connection[0];
            int b = connection[1];
            int rootA = find(root, a);
            int rootB = find(root, b);
            if(rootA == rootB){
                budget++;
            }else{
                //union
                group--;
                if(rank[rootA] == rank[rootB]){
                    rank[rootA]++;
                    root[rootB] = rootA;
                }else if(rank[rootA] > rank[rootB]){
                    root[rootB] = rootA;
                }else{
                    root[rootA] = rootB;
                }
            }
        }
        int minCost = group - 1;
        return (minCost > budget)? -1: minCost;  
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 6;
        int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        System.out.println("n:" + n);
        for(int[] connection: connections){
           System.out.println(Arrays.toString(connection));
        }
        System.out.println("min:" + sol.makeConnected(n, connections));
    }
}
