/* BFS: O(m*n)
 * 1. Push the border of ocean into queue
 * 2. BFS the border of Pacific and Atlantic respectively
 * 3. Return the intersection
 */

import java.util.*;

public class Solution{
    private HashSet<Integer> bfs(int[][] matrix, Deque<Integer> queue, HashSet<Integer> visited){
        int depth = matrix.length;
        int width = matrix[0].length;
        int[][] offsets = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; //left, up, right, down      
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int position = queue.pollFirst();
                int x = position % width;
                int y = position / width;
                for(int[] offset: offsets){
                    int nextY = y + offset[0];
                    int nextX = x + offset[1];
                    if(nextY < depth && nextY >= 0 && nextX < width && nextX >= 0){
                        int nextPosition = (nextY * width) + nextX;
                        if(matrix[nextY][nextX] >= matrix[y][x] && !visited.contains(nextPosition)){
                            visited.add(nextPosition);
                            queue.add(nextPosition);
                        }
                    }
                }
            }
        }
        return visited;
    }
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if(matrix.length == 0){
            return new LinkedList<>();
        }
        
        int depth = matrix.length;
        int width = matrix[0].length;

        //climb from Atlantic
        Deque<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> fromAtlantic = new HashSet<Integer>();
        for(int y = 0; y < depth; ++y){
            fromAtlantic.add(y * width +  width - 1);
            queue.add(y * width +  width - 1);
        }
        for(int x = 0; x < width; ++x){
            fromAtlantic.add(width * (depth - 1) +  x);
            queue.add(width * (depth - 1) +  x);
        }
        fromAtlantic = bfs(matrix, queue, fromAtlantic);
        
        //climb from Pacific
        queue = new LinkedList<Integer>();
        HashSet<Integer> fromPacific = new HashSet<Integer>();
        for(int y = 0; y < depth; ++y){
            fromPacific.add(y * width);
            queue.add(y * width);
        }
        for(int x = 0; x < width; ++x){
            fromPacific.add(x);
            queue.add(x);
        }
        fromPacific = bfs(matrix, queue, fromPacific);
        
        List<List<Integer>> edges = new LinkedList<>();
        for(int atlantic: fromAtlantic){
            if(fromPacific.contains(atlantic)){
                edges.add(Arrays.asList(atlantic / width, atlantic % width));
            }
        }
        return edges;
    }
 
    public static void main(String[] args){
        int[][] matrix = {{1, 2, 2, 3, 5},
                          {3, 2, 3, 4, 4},
                          {2, 4, 5, 3, 1},
                          {6, 7, 1, 4, 5},
                          {5, 1, 1, 2, 4}};
        
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");
        Solution sol = new Solution();
        System.out.println("topList: " + sol.pacificAtlantic(matrix));
    }
}
