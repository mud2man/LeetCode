/* DFS: O(n*m)
 * 1. If the grid[y][x] == '1', DFS all directions and set grid[y'][x'] = '0', if grid[y'][x'] = 1
 * 2. Otherwise, continue
 */

import java.util.*;

public class Solution{
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    ++count;
                }
            }
        }    
        return count;
    }

    private void DFSMarking(char[][] grid, int i, int j) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                           {'1', '0', '1', '1', '1'},
                           {'1', '1', '1', '1', '1'},
                           {'1', '0', '0', '1', '0'}};

        System.out.println("matrix: ");
        for(char[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        int islandNum = sol.numIslands(matrix);
        System.out.println("islandNum: " + islandNum);
    }
}
