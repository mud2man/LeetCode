/* Use BFS: O(n*m)
 * 1. Traverse all the rooms, and put the room into queue
 * 2. BFS these room, and every iteration will increase the distence 1 exactly
 * 3. More iteration means longer distence, so we just update the distence only one time
 */

import java.util.*;

public class Solution{
    public void bfs(int depth, int width, int[][] rooms, int[] room, LinkedList<int[]> queue){
        // left, up, right, down
        int[][] shifts = new int[][] {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        
        for(int i = 0; i < shifts.length; ++i){
            int[] shift = shifts[i];
            int y = room[0] + shift[0];
            int x = room[1] + shift[1];
            if((y >= 0) && (y < depth) && (x >= 0) && (x < width)){
                if(rooms[y][x] == Integer.MAX_VALUE){
                    rooms[y][x] = rooms[room[0]][room[1]] + 1;
                    int[] nextRoom = new int[] {y, x};
                    queue.add(nextRoom);
                }
            }
        }
    }
    
    public void wallsAndGates(int[][] rooms) {
        LinkedList<int[]> queue = new LinkedList<int[]>();
        int depth = rooms.length;
        int width = (depth == 0)? 0: rooms[0].length;
       
        //Put the starting points into queue 
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(rooms[y][x] == 0)
                    queue.add(new int[]{y, x});
            }
        }
        
        //BFS
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int[] room = queue.pollFirst();
                bfs(depth, width, rooms, room, queue);
            }
        }
    }

    public static void main(String[] args){
        Solution sol;
        int i;
        int[][] rooms = {{Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                         {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                         {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                         {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}};
        
        sol = new Solution();
        
        System.out.println("before: ");    
        for(i = 0; i < rooms.length; ++i){
            System.out.println(Arrays.toString(rooms[i]));
        }
            
        sol.wallsAndGates(rooms);

        System.out.println("after: ");    
        for(i = 0; i < rooms.length; ++i){
            System.out.println(Arrays.toString(rooms[i]));
        }
    }
}
