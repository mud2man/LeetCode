/* BFS: Time:O(n), Space:O(n), where n is number of stops
 * 1. Have a reversed index "stop2Bus"
 * 2. Apply BFS to find the least number of bus reaching "T"
 * 3. Have "takenBus" and "vistedStops" to prevent repeating
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
        
        if(!stop2Bus.containsKey(S)){
            return -1;
        }
        
        //BFS
        Set<Integer> takenBus = new HashSet<>();
        Set<Integer> vistedStops = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.add(S);
        int dis = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int stop = queue.pollFirst();
                if(stop == T){
                    return dis;
                }
                if(vistedStops.contains(stop)){
                    continue;
                }
                vistedStops.add(stop);
                for(int bus: stop2Bus.get(stop)){
                    if(takenBus.contains(bus)){
                        continue;
                    }
                    takenBus.add(bus);
                    for(int nextStop: routes[bus]){
                        queue.add(nextStop);
                    }
                }
            }
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
