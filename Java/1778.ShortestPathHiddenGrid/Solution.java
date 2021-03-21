/* BFS + DFS: Time:O(n), Space:O(n)
 * 1. Use DFS to construct the grid "y2xs"
 * 2. Find the shortest by BFS
 */

import java.util.*; // Stack

/* Definition for binary tree */
interface GridMaster {
    boolean canMove(char direction);
    void move(char direction);
    boolean isTarget();
};

public class Solution {
    private void dfs(Map<Integer, Set<Integer>> y2xs, int[] curr, int[] end, GridMaster master, 
                     int firstIdx, Set<String> seen){
        y2xs.computeIfAbsent(curr[0], key -> new HashSet<>()).add(curr[1]);
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
                master.move(moves[nextIdx]);
                if(nextIdx == 0){
                    dfs(y2xs, curr, end, master, 3, seen);    
                }else if(nextIdx == 1){
                    dfs(y2xs, curr, end, master, 0, seen);
                }else if(nextIdx == 2){
                    dfs(y2xs, curr, end, master, 1, seen);
                }else{
                    dfs(y2xs, curr, end, master, 2, seen);
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
           
    private int bfs(int[] end, Map<Integer, Set<Integer>> y2xs){
        if(end[0] == 0 && end[1] == 0){
            return -1;
        }
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int distance = 0;
        Set<String> seen = new HashSet<>();
        seen.add("0#0");
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] curr = queue.pollFirst();
                if(curr[0] == end[0] && curr[1] == end[1]){
                    return distance;
                }
                for(int j = 0; j < 4; j++){
                    int nextY = curr[0] + dirs[j][0];
                    int nextX = curr[1] + dirs[j][1];
                    String nextHash = Integer.toString(nextY) + "#" + Integer.toString(nextX);
                    if(!seen.contains(nextHash) && y2xs.containsKey(nextY) && y2xs.get(nextY).contains(nextX)){
                        queue.add(new int[]{nextY, nextX});
                        seen.add(nextHash);
                    }
                }
            }
            distance++;
        }
        return -1;
    }        
        
    public int findShortestPath(GridMaster master) {
        Map<Integer, Set<Integer>> y2xs = new HashMap<>();
        int[] end = new int[2];
        int[] start = {0, 0};
        dfs(y2xs, start, end, master, 0, new HashSet<>());
        return bfs(end, y2xs);
    }
  
    public static void main(String[] args){
        System.out.println("no example");
    }
}
