/* Topological sort: Time:O(e), Space:O(n). LeetCode has a postorder-based DFS solution
 * 1. Have in-degree map "indegree" and adjacent list "adjList" given edge set "richer"
 * 2. Apply tological sort to get the least quiet calue for each person, since the grapg is DAG
 * 3. Return the answer with reverse of quiet
 */         

import java.util.*;

public class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        for(int[] r: richer){
            adjList.putIfAbsent(r[0], new ArrayList<Integer>());
            adjList.get(r[0]).add(r[1]);
            indegree.putIfAbsent(r[1], 0);
            indegree.put(r[1], indegree.get(r[1]) + 1);
        }
        
        int[] quiet2Person = new int[quiet.length];
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        for(int i = 0; i < quiet.length; ++i){
            quiet2Person[quiet[i]] = i;
            if(!indegree.containsKey(i)){
                queue.add(i);
            }
        }
        
        //topological sort
        while(!queue.isEmpty()){
            int p = queue.poll();
            int q = quiet[p];
            if(!adjList.containsKey(p)){
                continue;
            }
            List<Integer> child = adjList.get(p);
            for(int c: child){
                quiet[c] = Math.min(q, quiet[c]);
                indegree.put(c, indegree.get(c) - 1);
                if(indegree.get(c) == 0){
                    queue.add(c);
                }
            }
        }
        
        for(int i = 0; i < quiet.length; ++i){
            quiet[i] = quiet2Person[quiet[i]];
        }
        
        return quiet;
    }
 
    public static void main(String[] args){
        Solution sol= new Solution();
        int[][] richer = {{1, 0},{2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};

        System.out.println("richer: ");
        for(int[] row: richer){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("quiet: " + Arrays.toString(quiet));
        System.out.println("answer: " + Arrays.toString(sol.loudAndRich(richer, quiet)));
    }
}
