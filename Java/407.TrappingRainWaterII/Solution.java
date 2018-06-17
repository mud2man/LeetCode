/* Minimum heaf: Time:O(n*mlog(n*m)), Space:O(n*m)
 * 1. Have a datastructre Cell, and put that on the boundry into minimum heap sorted by their height
 * 2. Take head out of heap, and check if any neighbor lower than it
 * 3. If so, add the difference to water, because the head is the has the minimum higher height, it determine the water contained by it
 * 4. And put the neighbor into heap, then repeat 2, 3, 4 untiol heap empty
 */         

import java.util.*;

public class Solution {
    private class Cell{
        int row;
        int col;
        int height;
        Cell(int r, int c, int h){row = r; col = c; height = h;}
    }
    
    private class HeightComparator implements Comparator<Cell>{
        public int compare(Cell a, Cell b){
            return a.height - b.height;
        }
    }
    
    public int trapRainWater(int[][] heightMap) {
        int depth = heightMap.length;
        int width = (depth > 0)? heightMap[0].length: 0;
        boolean[][] visited = new boolean[depth][width];
        
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(new HeightComparator());
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(y == 0 || y == (depth - 1)){
                    minHeap.add(new Cell(y, x, heightMap[y][x]));
                    visited[y][x] = true;
                }
                else if(x == 0 || x == (width - 1)){
                    minHeap.add(new Cell(y, x, heightMap[y][x]));
                    visited[y][x] = true;
                }
                else{
                    continue;
                }
            }
        }
        
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int water = 0;
        while(!minHeap.isEmpty()){
            Cell head = minHeap.poll();
            for(int[] dir: dirs){
                int y = head.row + dir[0];
                int x = head.col + dir[1];
                if(y < depth && y >= 0 && x < width && x >= 0 && visited[y][x] == false){
                    if(head.height > heightMap[y][x]){
                        water += (head.height - heightMap[y][x]);
                        heightMap[y][x] = head.height;
                    }
                    minHeap.add(new Cell(y, x, heightMap[y][x]));
                    visited[y][x] = true;
                }
            }
        }
        return water;
    }

    public static void main(String[] args){
        Solution sol= new Solution();
        int[][] heightMap = {{1, 4, 3, 1, 3, 2},
                             {3, 2, 1, 3, 2, 4},
                             {2, 3, 3, 2, 3, 1}};

        System.out.println("heightMap: ");
        for(int[] row: heightMap){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("trapped water: " + sol.trapRainWater(heightMap));
    }
}
