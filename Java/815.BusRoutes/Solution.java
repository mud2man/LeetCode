/* BFS: Time:O(n), Space:O(n), where n is number of stopss
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T){
            return 0;
        }
        
        Map<Integer, Set<Integer>> stop2Bus = new HashMap<>();
        for(int bus = 0; bus < routes.length; ++bus){
            for(int stop: routes[bus]){
                stop2Bus.putIfAbsent(stop, new HashSet<>());
                stop2Bus.get(stop).add(bus);
            }
        }
        
        Set<Integer> visited = new HashSet<>();
        visited.add(S);
        Set<Integer> unVisited = new HashSet<>();
        if(!stop2Bus.containsKey(S)){
            return -1;
        }
        //the first stops, which can be reached by 1 bus
        for(int bus: stop2Bus.get(S)){
            for(int stop: routes[bus]){
                unVisited.add(stop);
            }
        }
        
        //BFS
        int dis = 1;
        while(!unVisited.isEmpty()){
            Set<Integer> nexts = new HashSet<>();
            for(int stop: unVisited){
                if(stop == T){
                    return dis;
                }
                visited.add(stop);
                for(int bus: stop2Bus.get(stop)){
                    for(int nextStop: routes[bus]){
                        if(!visited.contains(nextStop)){
                            nexts.add(nextStop);
                        }
                    }
                }
            }
            unVisited = nexts;
            dis++;
        }
        return -1;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int S = 1;
        int T = 6;
        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        System.out.println("S: " + S + ", T:" + T);
        System.out.println("routes: ");
        for(int[] route: routes){
            System.out.println(Arrays.toString(route));
        }
        System.out.println("least number of buses: " + sol.numBusesToDestination(routes, S, T));
    }
}
