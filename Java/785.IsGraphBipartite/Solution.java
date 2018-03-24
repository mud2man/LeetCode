/* BFS: Time:O(n), Space:O(n)
 * 1. Use BFS to color all nodes
 * 2. If the node x has been colored, but no mathch the current color. Then, return false
 * 3. If the node x has been colored and mathch the current color. Continue
 * 4. If the node x has not been colored, color it. And put its child into queue, which is prepared to be colored the different color
 */

import java.util.*;

public class Solution{
    //  color 0: not visited
    //  color 1: white
    //  color 2: black
        private boolean bfs(LinkedList<Integer> queue, int[] colors, int[][] graph){
        int color = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int node = queue.pollFirst();
                if(colors[node] != 0 && colors[node] != color){
                    return false;
                }
                else if(colors[node] != 0){
                    continue;
                }
                else{
                    colors[node] = color;
                    for(int child: graph[node]){
                        queue.add(child); 
                    }
                }
            }
            color = color % 2 + 1;
        }
        return true;
    }
    
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for(int i = 0; i < colors.length; ++i){
            if(colors[i] == 0){
                LinkedList<Integer> queue = new LinkedList<Integer>();
                queue.add(i);
                if(bfs(queue, colors, graph) == false){
                    return false;
                }   
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
