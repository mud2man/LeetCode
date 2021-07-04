/* BFS: Time:O(n^2*m^2), Space:O(n*m).
 * 1. The concept is to memorize the combinations of (box position, stand poistion) and prevent duplication
 * 2. Have a BFS helper "getStandPostionsNextBox" to return all the reachable position next box given start position
 * 3. Use BFS to propagate all possible combinations of (box position, stand poistion) until target was reached
 * 4. Use hash=(box[0] * width + box[1]) * area + (stand[0] * width + stand[1])
 */

import java.util.*; // Stack


public class Solution {
    private List<int[]> getStandPostionsNextBox(int[] start, int[] box, char[][] grid){
        int depth = grid.length;
        int width = grid[0].length;
        grid[box[0]][box[1]] = '#';
        Set<Integer> reachables = new HashSet<>();
        reachables.add(start[0] * width + start[1]);
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        List<int[]> standPostions = new LinkedList<>();
        Deque<int[]> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] front = queue.pollFirst();
                if((front[0] == box[0] && Math.abs(front[1] - box[1]) == 1) || (front[1] == box[1] && Math.abs(front[0] - box[0]) == 1)){
                    standPostions.add(new int[]{front[0], front[1]});
                }
                for(int[] dir: dirs){
                    int nextY = front[0] + dir[0];
                    int nextX = front[1] + dir[1];
                    int nextHash = nextY * width + nextX;
                    if(nextY >= 0 && nextY < depth && nextX >= 0 && nextX < width 
                       && (grid[nextY][nextX] != '#')
                       && !reachables.contains(nextHash)){
                        queue.add(new int[]{nextY, nextX});
                        reachables.add(nextY * width + nextX);
                    }
                }
            }
        }
        grid[box[0]][box[1]] = '.';
        return standPostions;
    }
    
    public int minPushBox(char[][] grid) {
        int[] start = new int[2];
        int[] target = new int[2];
        int[] box = new int[2];
        int depth = grid.length;
        int width = grid[0].length;
        int area = depth * width;
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                if(grid[y][x] == 'T'){
                    target = new int[]{y, x};
                    grid[y][x] = '.';
                }else if(grid[y][x] == 'B'){
                    box = new int[]{y, x};
                    grid[y][x] = '.';
                }else if(grid[y][x] == 'S'){
                    start = new int[]{y, x};
                    grid[y][x] = '.';
                }
            }
        }
        
        int moves = 0;
        List<int[]> sources = getStandPostionsNextBox(start, box, grid);
        Set<Integer> used = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        for(int[] source: sources){
            queue.add((box[0] * width + box[1]) * area + (source[0] * width + source[1]));
        }
        used.addAll(queue);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int front = queue.pollFirst();
                int[] currentStand = new int[]{(front % area) / width, front % width};
                int[] currBox = new int[]{(front / area / width), (front / area % width)};
                if(currBox[0] == target[0] && currBox[1] == target[1]){
                    return moves;
                }
                List<int[]> standPositions = getStandPostionsNextBox(currentStand, currBox, grid);
                int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; //stand right, left, up, down
                for(int[] standPosition: standPositions){
                    for(int[] dir: dirs){
                        if(standPosition[0] == currBox[0] + dir[0] && standPosition[1] == currBox[1] + dir[1]){
                            int[] nextBox = new int[]{currBox[0] - dir[0] , currBox[1] - dir[1]};
                            int[] nextStandPosition = new int[]{standPosition[0] - dir[0], standPosition[1] - dir[0]};
                            int nextHash = (nextBox[0] * width + nextBox[1]) * area + (nextStandPosition[0] * width + nextStandPosition[1]);
                            if(nextBox[1] >= 0 && nextBox[1] < width && nextBox[0] >= 0 && nextBox[0] < depth && 
                               grid[nextBox[0]][nextBox[1]] == '.' && !used.contains(nextHash)){
                                queue.add(nextHash);
                                used.add(nextHash);
                            }
                            break;
                        }
                    }
                } // for
            }// for
            moves++;
        }
        return -1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] grid = {{'#', '#', '#', '#', '#', '#'},
                         {'#', 'T', '#', '#', '#', '#'}, 
                         {'#', '.', '.', 'B', '.', '#'}, 
                         {'#', '.', '#', '#', '.', '#'}, 
                         {'#', '.', '.', '.', 'S', '#'}, 
                         {'#', '#', '#', '#', '#', '#'}};
        System.out.println("grid:");
        for(char[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("min moves:" + sol.minPushBox(grid));
    }
}
