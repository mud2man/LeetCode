/* DFS: Time:O(n + e), Space:O(n + e)
 * 1. Have a array "colors" for every node  
 * 2. Use DFS to visit the unvisited node which its color is 0
 * 3. Assign the neighbor with "-color:, and see if conflict happend, then return false
 */

import java.util.*;

public class Solution {
        private boolean dfs(int node, int[] colors, int color, List<List<Integer>> edges){
        if(colors[node] != 0){
            if(colors[node] != color){
                return false;
            }
            else{
                return true;
            }
        }
        
        colors[node] = color;
        for(int neighbor: edges.get(node)){
            if(!dfs(neighbor, colors, -color, edges)){
                return false;
            }
        }
        return true;
    }
    
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] colors = new int[N + 1];
        List<List<Integer>> edges = new ArrayList<List<Integer>>();
        for(int i = 0; i <= N; ++i){
            edges.add(new ArrayList<Integer>());
        }
        
        for(int[] dislike: dislikes){
            edges.get(dislike[0]).add(dislike[1]);
            edges.get(dislike[1]).add(dislike[0]);
        }
        
        for(int i = 1; i <= N; ++i){
            if(colors[i] == 0){
                if(!dfs(i, colors, 1, edges)){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 4; 
        int[][] dislikes = {{1, 2}, {1, 3}, {2, 4}};
        System.out.println("N: " + N);
        System.out.print("dislikes: ");
        for(int[] dislike: dislikes){
            System.out.print(Arrays.toString(dislike) + ", ");
        }
        System.out.println("");
        System.out.println("can bipartition: " + sol.possibleBipartition(N, dislikes));
    }
}
