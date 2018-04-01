/* DFS: Time:O(n*mlog(n*m)), Space:O(n*m). LeetCode has the better solution
 * 1. Generate the ID matrix for all varience including rotating 90, 180, 270, and reflections
 * 2. Get the track and sort, and put it into hashset if there is no conflict
 */

import java.util.*;

public class Solution{
    List<int[][]> ids; // original, rotate 90, rotate 180, rotate 270, reflect left&right, reflect up&down 

    private void generateId(int[][] grid){
        int length = Math.max(grid.length, grid[0].length);
        ids = new ArrayList<int[][]>();
        for(int i = 0; i < 12; ++i){
            ids.add(new int[length][length]);
        }
        
        for(int y = 0; y < length; ++y){
            for(int x = 0; x < length; ++x){
                int id = x + (2 * y * length);
                ids.get(0)[y][x] = id;
                ids.get(1)[y][x] = id;
                ids.get(2)[y][x] = id;
                ids.get(3)[x][length - y - 1] = id;
                ids.get(4)[x][length - y - 1] = id;
                ids.get(5)[x][length - y - 1] = id;
                ids.get(6)[length - y - 1][length - x - 1] = id;
                ids.get(7)[length - y - 1][length - x - 1] = id;
                ids.get(8)[length - y - 1][length - x - 1] = id;
                ids.get(9)[length - x - 1][y] = id;
                ids.get(10)[length - x - 1][y] = id;
                ids.get(11)[length - x - 1][y] = id;
            }
        }
        
        for(int y = 0; y < length; ++y){
            for(int x = 0; x < (length / 2); ++x){
                for(int i = 1; i < 12; i += 3){
                    int temp = ids.get(i)[y][x];
                    ids.get(i)[y][x] = ids.get(i)[y][length - x - 1];
                    ids.get(i)[y][length - x - 1] = temp;   
                }
            }
        }
        
        for(int x = 0; x < length; ++x){
            for(int y = 0; y < (length / 2); ++y){
                for(int i = 2; i < 12; i += 3){
                    int temp = ids.get(i)[y][x];
                    ids.get(i)[y][x] = ids.get(i)[length - y - 1][x];
                    ids.get(i)[length - y - 1][x] = temp;  
                }
            }
        }
    }
    
    private String getIslandString(List<Integer> island){
        String islandString = "";
        int base = island.get(0);
        for(int id: island){
            islandString += Integer.toString(id - base) + ",";
        }
        return islandString;
    }
    
    private void dfs(int y, int x, int[][] grid, List<List<Integer>> islands){
        if(y < 0 || y == grid.length || x < 0 || x == grid[0].length || grid[y][x] == 0){
            return;
        }
        
        grid[y][x] = 0;
        for(int i = 0; i < 12; ++i){
            islands.get(i).add(ids.get(i)[y][x]);
        }
        
        dfs(y, x + 1, grid, islands);
        dfs(y + 1, x, grid, islands);
        dfs(y, x - 1, grid, islands);
        dfs(y - 1, x, grid, islands);
    }
    
    public int numDistinctIslands2(int[][] grid) {
        generateId(grid);
        HashSet<String> visitedIslands = new HashSet<String>();
        int count = 0;
        
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < grid[0].length; ++x){
                if(grid[y][x] == 1){
                    List<List<Integer>> islands = new ArrayList<List<Integer>>();
                    for(int i = 0; i < 12; ++i){
                        islands.add(new ArrayList<Integer>());
                    }
                    dfs(y, x, grid, islands);
                    List<Integer> originalIsland = islands.get(0);
                    Collections.sort(originalIsland);
                    String islandString = getIslandString(originalIsland);
                    if(visitedIslands.contains(islandString)){ continue; }
                    count++;
                    visitedIslands.add(islandString);
                    for(int i = 1; i < islands.size(); ++i){
                        Collections.sort(islands.get(i));
                        visitedIslands.add(getIslandString(islands.get(i)));
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] grid = {{1, 1, 0, 0, 0},{1, 1, 0, 0, 0},{0, 0, 0, 1, 1},{0, 0, 0, 1, 1}};
        
        sol = new Solution();
        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("distinct island number: " + sol.numDistinctIslands2(grid));
    }
}
