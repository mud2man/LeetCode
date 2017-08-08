/* Use DFS: O(e + n)
 * 1. Create colors[], where white: 0, gray: 1, black: 2
 * 2. If the outgoing node is black, don NOT invoke dfs
 * 3. If the current node is gray, cycle found return false
 */

import java.util.*; // Stack

public class Solution {
    private boolean dfs(int[] colors, List<List<Integer>> adjacentList, int root){
        if(colors[root] == 1){
            return false;
        }
        
        colors[root] = 1;
        for(Integer v: adjacentList.get(root)){
            if(colors[Integer.valueOf(v)] == 2){
                continue;
            }
            else{
                if(!dfs(colors, adjacentList, Integer.valueOf(v))){
                    return false;
                }
            }
        }
        colors[root] = 2;
        return true;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // white: 0, gray: 1, black: 2
        int[] colors = new int[numCourses];
        Set<Integer> roots = new HashSet<Integer>();
        List<List<Integer>> adjacentList = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < numCourses; ++i){
            adjacentList.add(new ArrayList<Integer>());
        }
        
        for(int[] prerequisite: prerequisites){
            int source = prerequisite[1];
            int target = prerequisite[0];
            adjacentList.get(source).add(target);
        }
        
        for(int i= 0; i < numCourses; ++i){
            if(!dfs(colors, adjacentList, i)){
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
