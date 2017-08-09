/* Topological sort: O(e + n)
 * 1. Create an array "inDegrees" to record the indegree count of every node
 * 2. Construct the graph with a 2D "adjecentList"
 * 3. Have a list "roots" to store the nodes with indegree == 0
 * 4. Visit by poping out from "roots" until it is empty
 */

import java.util.*; // Stack

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        List<List<Integer>> adjecentList = new ArrayList<List<Integer>>();
        LinkedList<Integer> roots = new LinkedList<Integer>();
        List<Integer> order = new ArrayList<Integer>();
        
        for(int i = 0; i < numCourses; ++i){
            adjecentList.add(new ArrayList<Integer>());
        }
        
        for(int[] prerequisite: prerequisites){
            int source = prerequisite[1];
            int target = prerequisite[0];
            inDegrees[target]++;
            adjecentList.get(source).add(target);
        }
        
        for(int i = 0; i < numCourses; ++i){
            if(inDegrees[i] == 0){
                roots.add(i);
            }
        }
        
        while(!roots.isEmpty()){
            int size = roots.size();
            for(int i = 0; i < size; ++i){
                int source = roots.pollFirst();
                order.add(source);
                for(Integer target: adjecentList.get(source)){
                    if(--inDegrees[Integer.valueOf(target)] == 0){
                        roots.add(target);
                    }
                }
            }
        }
        
        if(order.size() == numCourses){
            return order.stream().mapToInt(i->i).toArray();
        }
        else{
            return new int[0];
        }
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
         *     /     /
         *    @     /
         *   3     /
         *    \   /
         *     @ @
         *      4
         */
        prerequisites = new int [][]{
            {1, 0}, {2, 0}, {3, 1}, {4, 3}
        };

        sol = new Solution();
        int[] order = sol.findOrder(numCourses, prerequisites);
        System.out.println("order: " + Arrays.toString(order));
    }
}
