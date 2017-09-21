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
        Set<Integer> walls = new HashSet<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        int depth = maze.length;
        int width = (depth > 0)? maze[0].length: 0;
        int startPos = start[0] * width + start[1];
        int destinationPos = destination[0] * width + destination[1];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(startPos);
        visited.add(startPos);
        
        //up, down, left, right
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int currPos = queue.pollFirst();
                if(currPos == destinationPos){
                    return true;
                }
                else{
                    for(int[] offset: offsets){
                        int nextY = currPos / width;
                        int nextX = currPos % width;
                        while(nextX < width && nextX >= 0 &&
                              nextY < depth && nextY >= 0 &&
                              maze[nextY][nextX] == 0){
                            nextY = nextY + offset[0];
                            nextX = nextX + offset[1];
                        };
                        
                        nextY = nextY - offset[0];
                        nextX = nextX - offset[1];
                        int nexPos = nextY * width + nextX;
                        if(!visited.contains(nexPos)){
                            visited.add(nexPos);
                            queue.add(nexPos);
                        }
                    }
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
