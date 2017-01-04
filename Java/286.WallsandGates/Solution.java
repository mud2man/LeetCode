/* Use BFS: O(n*m)
 * 1. Traverse all the rooms, and put the room into queue
 * 2. BFS these room, and every iteration will increase the distence 1 exactly
 * 3. More iteration means longer distence, so we just update the distence only one time
 */

import java.util.*;

public class Solution{
    private class Position{
        int x;
        int y;
        Position(int col, int row){ x = col; y = row;}
    }
    
    public void wallsAndGates(int[][] rooms) {
        Position pos;
        LinkedList<Position> queue;
        
        int size;
        int i;
        int x;
        int y;
        
        if(rooms.length == 0){
            return;
        }
        
        queue = new LinkedList<Position>();
        
        for(y = 0; y < rooms.length; ++y){
            for(x = 0; x < rooms[0].length; ++x){
                if(rooms[y][x] == 0){
                    queue.add(new Position(x, y));
                }
            }
        }
        
        //every iteration will increase distence 1
        while(queue.size() > 0){
            size = queue.size();
            for(i = 0; i < size; ++i){
                pos = queue.poll();
                //up
                if((pos.y > 0) && (Integer.MAX_VALUE == rooms[pos.y - 1][pos.x])){
                    rooms[pos.y - 1][pos.x] =  rooms[pos.y][pos.x] + 1;
                    queue.add(new Position(pos.x, pos.y - 1));
                }
                //down
                if((pos.y < (rooms.length - 1)) && (Integer.MAX_VALUE == rooms[pos.y + 1][pos.x])){
                    rooms[pos.y + 1][pos.x] =  rooms[pos.y][pos.x] + 1;
                    queue.add(new Position(pos.x, pos.y + 1));
                }
                //left
                if((pos.x > 0) && (Integer.MAX_VALUE == rooms[pos.y][pos.x - 1])){
                    rooms[pos.y][pos.x - 1] =  rooms[pos.y][pos.x] + 1;
                    queue.add(new Position(pos.x - 1, pos.y));
                }
                //right
                if((pos.x < (rooms[0].length - 1)) && (Integer.MAX_VALUE == rooms[pos.y][pos.x + 1])){
                    rooms[pos.y][pos.x + 1] =  rooms[pos.y][pos.x] + 1;
                    queue.add(new Position(pos.x + 1, pos.y));
                }
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
