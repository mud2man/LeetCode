/* BFS O(m*n*Max(m, n)), where m is width, n is depth
 * 1. Use BFS to traverse up, down, left and right, until hitting a wall
 * 2. If the stop position was never visited, then put it in the queue
 * 3. Repeat until the BFS queue is empty
 */          

import java.util.*; // Stack

public class Solution {
    private class Possition{
        int yPos;
        int xPos;
        Possition(int y, int x){yPos = y; xPos = x;}
    }
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Queue<Possition> queue;
        Possition currPos, stopPos;
        int size, idx, depth, width;
        
        queue = new LinkedList<Possition>();
        currPos = new Possition(start[0], start[1]);
        stopPos = new Possition(start[0], start[1]);
        queue.offer(currPos);
        depth = maze.length;
        width = maze[0].length;
        
        // BFS
        int level = 0;
        while(!queue.isEmpty()){
            size = queue.size();
            for(idx = 0; idx < size; idx++){
                stopPos = queue.poll();
                
                if(stopPos.yPos == destination[0] && stopPos.xPos == destination[1]){
                    return true;
                }
                
                // roll up
                currPos = new Possition(stopPos.yPos, stopPos.xPos);
                while(currPos.yPos > 0  && maze[currPos.yPos - 1][currPos.xPos] != 1){
                    currPos.yPos = currPos.yPos - 1;
                }
                if(stopPos.yPos != currPos.yPos && maze[currPos.yPos][currPos.xPos] != -1){
                    queue.offer(new Possition(currPos.yPos, currPos.xPos));
                    maze[currPos.yPos][currPos.xPos] = -1;
                }
                
                // roll down
                currPos = new Possition(stopPos.yPos, stopPos.xPos);
                while(currPos.yPos < depth - 1 && maze[currPos.yPos + 1][currPos.xPos] != 1){
                    currPos.yPos = currPos.yPos + 1;
                }
                if(stopPos.yPos != currPos.yPos && maze[currPos.yPos][currPos.xPos] != -1){
                    queue.offer(new Possition(currPos.yPos, currPos.xPos));
                    maze[currPos.yPos][currPos.xPos] = -1;
                }
                
                // roll left
                currPos = new Possition(stopPos.yPos, stopPos.xPos);
                while(currPos.xPos > 0 && maze[currPos.yPos][currPos.xPos - 1] != 1){
                    currPos.xPos = currPos.xPos - 1;
                }
                if(stopPos.xPos != currPos.xPos && maze[currPos.yPos][currPos.xPos] != -1){
                    queue.offer(new Possition(currPos.yPos, currPos.xPos));
                    maze[currPos.yPos][currPos.xPos] = -1;
                }
                
                // roll right
                currPos = new Possition(stopPos.yPos, stopPos.xPos);
                while(currPos.xPos < width - 1 && maze[currPos.yPos][currPos.xPos + 1] != 1){
                    currPos.xPos = currPos.xPos + 1;
                }
                if(stopPos.xPos != currPos.xPos && maze[currPos.yPos][currPos.xPos] != -1){
                    queue.offer(new Possition(currPos.yPos, currPos.xPos));
                    maze[currPos.yPos][currPos.xPos] = -1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] maze = {{0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0},
                        {1, 1, 0, 1, 1},
                        {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};
        boolean hasPath;

        sol = new Solution();

        System.out.println("maze: ");
        for(int[] row: maze){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("start: " + Arrays.toString(start));
        System.out.println("destination: " + Arrays.toString(destination));

        hasPath = sol.hasPath(maze, start, destination);
        System.out.println("hasPath: " + hasPath);
    }
}
