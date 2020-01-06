/* Kruskal's algorithm: Time:O(e*loge), Space:O(n)
 * 1. Use join and find to find the minimum spanning tree
 */

import java.util.*;


public class Solution{
    private int find(int[] roots, int source){
        if(roots[source] == source){
            return source;
        }    
        roots[source] = roots[roots[source]]; //compression
        return find(roots, roots[source]);
    }
    
    public int minimumCost(int N, int[][] connections) {
        int[] roots = new int[N + 1];
        for(int i = 0; i <= N; ++i){
            roots[i] = i;
        }
        int[] ranks = new int[N + 1];
        int groupNums = N;
        Arrays.sort(connections, (x, y) -> (x[2] - y[2]));
        int cost = 0;
        for(int[] connection: connections){
            int root0 = find(roots, connection[0]);
            int root1 = find(roots, connection[1]);
            int distance = connection[2];
            if(root0 != root1){
                cost += distance;
                groupNums--;
                if(ranks[root0] > ranks[root1]){
                    roots[root1] = roots[root0]; //join
                }else if(ranks[root0] < ranks[root1]){
                    roots[root0] = roots[root1]; //join
                }else{
                    roots[root1] = roots[root0]; //join
                    ranks[root0]++;
                }
            }
        }
        return (groupNums == 1)? cost: -1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] connections = {{1, 2, 5}, {1, 3, 6}, {2, 3, 1}};
        int N = 3;
        System.out.println("N:" + N);
        System.out.println("connections:");
        for(int[] connection: connections){
            System.out.println(Arrays.toString(connection));
        }
        System.out.println("minimum cost to connect cities:" + sol.minimumCost(N, connections));
    }
}
