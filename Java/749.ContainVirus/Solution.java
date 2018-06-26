/* DFS: Time:O((n*m)^2), Space:O(n*m)
 * 1. Use getWalls to get the number of uninfected cell and walls for every region region, then change 1 to id
 * 2. Pick the region which threatens the most uninfected cells, and call "isolate", then change id to -1
 * 3. For every region, call "propagate" to expend, and change id back to 1
 */         

import java.util.*;

public class Solution {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private void getWalls(int[][] grid, int y, int x, int id, int[] count, Set<Integer> counted){
        if(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length || grid[y][x] == -1 || grid[y][x] == id){
            return;
        }
        
        int curr = y * grid[0].length + x;
        if(grid[y][x] == 0){
            count[0]++;
            if(!counted.contains(curr)){
                count[1]++;
                counted.add(curr);
            }
        }
        else{
            grid[y][x] = id;
            for(int[] dir: dirs){
                getWalls(grid, y + dir[0], x + dir[1], id, count, counted);
            }
        }
    }
    
    private void isolate(int[][] grid, int y, int x){
        if(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length || grid[y][x] == -1 || grid[y][x] == 0){
            return;
        }
        grid[y][x] = -1;
        for(int[] dir: dirs){
            isolate(grid, y + dir[0], x + dir[1]);
        }
    }
    
    private void propagate(int[][] grid, int y, int x){
        if(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length || grid[y][x] == 1 || grid[y][x] == -1){
            return;
        }
        
        if(grid[y][x] == 0){
            grid[y][x] = 1;
        }
        else{
            grid[y][x] = 1;
            for(int[] dir: dirs){
                propagate(grid, y + dir[0], x + dir[1]);
            } 
        }
    }
    
    public int containVirus(int[][] grid) {
        int[] countAndPos;
        int depth = grid.length;
        int width = (depth > 0)? grid[0].length: 0;
        int count = 0;
        
        while(true){
            countAndPos = new int[]{0, 0, 2};
            for(int y = 0; y < grid.length; ++y){
                for(int x = 0; x < grid[0].length; ++x){
                    if(grid[y][x] == 1){
                        int id = y * width + x + 2;
                        int[] subcount = new int[2];
                        getWalls(grid, y, x, id, subcount, new HashSet<Integer>());
                        if(subcount[1] > countAndPos[1]){
                            countAndPos[0] = subcount[0];
                            countAndPos[1] = subcount[1];
                            countAndPos[2] = id;
                        }
                    }
                }
            }
            
            if(countAndPos[0] == 0){
                break;
            }
            
            count += countAndPos[0];
            isolate(grid, (countAndPos[2] - 2) / width, (countAndPos[2] - 2) % width);
            
            for(int y = 0; y < grid.length; ++y){
                for(int x = 0; x < grid[0].length; ++x){
                    if(grid[y][x] > 0){
                        propagate(grid, y, x);
                    }
                }
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol= new Solution();
        int[][] grid = {{0, 1, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0}};

        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("walls number: " + sol.containVirus(grid));
    }
}
