/* DFS + Dijkstra: Time:O(nlogn), Space:O(n)
 * 1. Use DFS to construct graph "yX2Cost"
 * 2. Find the minimum-cost path by Dijkstra
 */

import java.util.*; // Stack

/* Definition for binary tree */
interface GridMaster {
    boolean canMove(char direction);
    int move(char direction);
    boolean isTarget();
};

public class Solution {
    private void dfs(Map<Integer, Map<Integer, Integer>> yX2Cost, int[] curr, int[] end, 
                     GridMaster master, int firstIdx, Set<String> seen, int cost){
        yX2Cost.computeIfAbsent(curr[0], key -> new HashMap<>()).put(curr[1], cost);
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        char[] moves = {'U','R','D','L'};
        seen.add(Integer.toString(curr[0]) + "#" + Integer.toString(curr[1]));
        if(master.isTarget()){
            end[0] = curr[0];
            end[1] = curr[1];
        }
        for(int i = 0; i < 3; i++){
            int nextIdx = (firstIdx + i) % 4;
            curr[0] += dirs[nextIdx][0];
            curr[1] += dirs[nextIdx][1];
            String nextHash = Integer.toString(curr[0]) + "#" + Integer.toString(curr[1]);
            if(master.canMove(moves[nextIdx]) && !seen.contains(nextHash)){
                int nextCost = master.move(moves[nextIdx]);
                if(nextIdx == 0){
                    dfs(yX2Cost, curr, end, master, 3, seen, nextCost);    
                }else if(nextIdx == 1){
                    dfs(yX2Cost, curr, end, master, 0, seen, nextCost);
                }else if(nextIdx == 2){
                    dfs(yX2Cost, curr, end, master, 1, seen, nextCost);
                }else{
                    dfs(yX2Cost, curr, end, master, 2, seen, nextCost);
                }
            }
            curr[0] -= dirs[nextIdx][0];
            curr[1] -= dirs[nextIdx][1];
        }
        int finalIdx = (firstIdx + 3) % 4;
        if(master.canMove(moves[finalIdx])){
            master.move(moves[finalIdx]);
        }
    }
    
    private int dijkstra(int[] end, Map<Integer, Map<Integer, Integer>> yX2Cost){
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((x, y) -> (x[2] - y[2]));
        minHeap.add(new int[]{0, 0, 0});
        Set<String> seen = new HashSet<>();
        seen.add("0#0");
        while(!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int cost = curr[2];
            if(curr[0] == end[0] && curr[1] == end[1]){
                return cost;
            }
            int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            for(int[] dir: dirs){
                int nextY = curr[0] + dir[0];
                int nextX = curr[1] + dir[1];
                String nextHash = Integer.toString(nextY) + "#" + Integer.toString(nextX);
                if(!seen.contains(nextHash) && yX2Cost.containsKey(nextY) && yX2Cost.get(nextY).containsKey(nextX)){
                    seen.add(nextHash);
                    minHeap.add(new int[]{nextY, nextX, cost + yX2Cost.get(nextY).get(nextX)});
                }
            }
        }
        return -1;
    }
    
    public int findShortestPath(GridMaster master) {
        Map<Integer, Map<Integer, Integer>> yX2Cost = new HashMap<>();
        int[] end = new int[2];
        int[] start = new int[2];
        dfs(yX2Cost, start, end, master, 0, new HashSet<>(), 0);
        return (end[0] == 0 && end[1] == 0)? -1: dijkstra(end, yX2Cost);
    }
  
    public static void main(String[] args){
        System.out.println("no example");
    }
}
