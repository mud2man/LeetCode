/* DFS: Time:O(n), Space:O(n)
 * 1. Vist the rooms with DFS, and use array "visted" to record if it's visted before
 */

import java.util.*;

public class Solution{
    private void dfs(int room, List<List<Integer>> rooms, boolean[] visited, int[] count){
        if(visited[room]){
            return;
        }
        visited[room] = true;
        count[0]++;
        
        for(int key: rooms.get(room)){
            dfs(key, rooms, visited, count);
        }
    }
    
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        int[] count = {0};
        dfs(0, rooms, visited, count);
        return (count[0] == rooms.size());
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        List<List<Integer>> rooms = new ArrayList<List<Integer>>();
        rooms.add(Arrays.asList(new Integer[]{1}));
        rooms.add(Arrays.asList(new Integer[]{2}));
        rooms.add(Arrays.asList(new Integer[]{3}));
        rooms.add(Arrays.asList(new Integer[]{}));

        System.out.println("rooms: " + rooms);
        System.out.println("can visted all room? " + sol.canVisitAllRooms(rooms));
    }
}
