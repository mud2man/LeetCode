/* BFS O(m*n*Max(m, n)), where m is width, n is depth
 * 1. Use BFS to traverse up, down, left and right, until hitting a wall
 * 2. If the distance of the stop position is smaller than the current value, 
 *    update it by distance[nextY][nextX] = distance[currY][currX] + diff;
 * 3. In every loop, update minDistance if the current position is the destination
 * 4. Retunr -1 if minDistance == Integer.MAX_VALUE, otherwise return minDistance
 */          

import java.util.*; // Stack

public class Solution {
    int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int width = maze[0].length;
        int depth = maze.length;
        int minDistance = Integer.MAX_VALUE;
        int[][] offsets = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //up, right, down, left
        
        int[][] distance = new int[depth][width];
        for(int[] row: distance){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        
        LinkedList<int[]> queue = new LinkedList<int[]>();
        queue.add(start);
        while(!queue.isEmpty()){
            int[] currPosition = queue.pollFirst();
            int currY = currPosition[0];
            int currX = currPosition[1];
            
            if(currY == destination[0] && currX == destination[1]){
                if(distance[currY][currX] < minDistance){
                    minDistance = distance[currY][currX];
                    continue;
                }
            }
            
            if(distance[currY][currX] > minDistance){
                continue;
            }
            
            for(int[] offset: offsets){
                int nextY = currY;
                int nextX = currX;
                while(nextY >= 0 && nextY < depth && nextX >= 0 && nextX < width && maze[nextY][nextX] != 1){
                    nextY += offset[0];
                    nextX += offset[1];
                }
                nextY -= offset[0];
                nextX -= offset[1];
                
                int diff = Math.abs(nextY - currY) + Math.abs(nextX - currX);
                if(diff == 0){
                    continue;
                }
                else if (distance[nextY][nextX] > (distance[currY][currX] + diff)){
                    distance[nextY][nextX] = distance[currY][currX] + diff;
                    queue.add(new int[]{nextY, nextX});
                }
                else{
                    continue;
                }
            }
        }
        
        return (minDistance == Integer.MAX_VALUE)? -1: minDistance;
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

        sol = new Solution();

        System.out.println("maze: ");
        for(int[] row: maze){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("start: " + Arrays.toString(start));
        System.out.println("destination: " + Arrays.toString(destination));

        int shortestDistance = sol.shortestDistance(maze, start, destination);
        System.out.println("shortestDistance: " + shortestDistance);
    }
}
