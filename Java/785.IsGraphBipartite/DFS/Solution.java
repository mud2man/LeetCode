/* DFS: Time:O(n), Space:O(n)
 * 1. Use DFS to color all nodes
 * 2. If the node x has been colored, but no mathch the current color. Then, return false
 * 3. If the node x has been colored and mathch the current color. Return true
 * 4. If the node x has not been colored, color it. Then call "dfs" with its children, return false if any of them failed
 */

import java.util.*;

public class Solution{
    //  color 0: not visited
    //  color 1: white
    //  color 2: black
    private boolean dfs(int start, int[][] graph, int color, int[] colors){
        if(colors[start] == color){
            return true;
        }else if(colors[start] != 0 && colors[start] != color){
            return false;
        }else{
            colors[start] = color;
            int[] nexts = graph[start];
            int nextColor = (color == 1)? 2: 1;
            for(int next: nexts){
                if(!dfs(next, graph, nextColor, colors)){
                    return false;
                }
            }
            return true;
        }
    }
    
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for(int i = 0; i < graph.length; ++i){
            if(colors[i] == 0 && !dfs(i, graph, 1, colors)){
                return false;
            }
        }
        return true;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        
        System.out.println("graph: ");
        for(int[] sucessors: graph){
            System.out.println(Arrays.toString(sucessors));
        }
        System.out.println("is bipartite: " + sol.isBipartite(graph));
    }
}
