/* Use DFS: O(e + n)
 * 1. Create colors[], where white: 0, gray: 1, black: 2
 * 2. If the out going node is black, don NOT invoke dfs
 * 3. If the current node is gray, cycle found return false
 */

import java.util.*; // Stack

public class Solution {
    private boolean dfs(int start, int[] colors, Map<Integer, Set<Integer>> adjacencyList){
        if(colors[start] == 2){
            return true;
        }
        else if(colors[start] == 1){
            // cycle found
            return false;
        }
        else{
            colors[start] = 1;
            Set<Integer> neighbors = (adjacencyList.containsKey(start))? adjacencyList.get(start): new HashSet<>();
            for(int neighbor: neighbors){
                if(!dfs(neighbor, colors, adjacencyList)){
                    return false;
                }
            }
            colors[start] = 2;
            return true;
        }
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        for(int[] prerequisite: prerequisites){
            adjacencyList.putIfAbsent(prerequisite[0], new HashSet<>());
            adjacencyList.get(prerequisite[0]).add(prerequisite[1]);
        }
        //0:white, 1:gray, 2:black
        int[] colors = new int[numCourses];
        for(int i = 0; i < numCourses; ++i){
            if(!dfs(i, colors, adjacencyList)){
                return false;
            }
        }
        return true;
    }
  
    public static void main(String[] args){
        final int numCourses = 5;
        Solution sol;
        int[][] prerequisites;
        boolean canfinish; 

        /*    Relationship graph
         *         0
         *        / \
         *       @   @
         *      1     2
         *     /@     /
         *    @ |    /
         *   3  |   /
         *    \ |  /
         *       @| @
         *        4
         */
        prerequisites = new int [][] 
        {
            {1, 0}, {2, 0}, {3, 1}, {4, 3}, {1, 4} 
        };

        sol = new Solution();
        canfinish = sol.canFinish(numCourses, prerequisites);
        
        if(canfinish)
        {
            System.out.println("No conflict!!!");
        }
        else
        {
            System.out.println("Conflict!!!");
        }
    }
}
