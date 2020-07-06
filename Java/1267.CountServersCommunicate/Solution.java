/* Hash: Time:O(n * m), Space:O(n * m)
 * 1. Have occupiedRows/occupiedCols to store the first server hash which in the row/column
 * 2. Put the communicated servers to communicatedServers, and return the size
 */     

import java.util.*; // Stack

public class Solution {
    public int countServers(int[][] grid) {
        Set<Integer> communicatedServers = new HashSet<>();
        int colNum = grid[0].length;
        int[] occupiedRows = new int[grid.length];
        Arrays.fill(occupiedRows, -1);
        int[] occupiedCols = new int[colNum];
        Arrays.fill(occupiedCols, -1);
        for(int y = 0; y < grid.length; ++y){
            for(int x = 0; x < colNum; ++x){
                if(grid[y][x] == 0){
                    continue;
                }
                int hash = y * colNum + x;
                if(occupiedRows[y] != -1 || occupiedCols[x] != -1){
                    communicatedServers.add(hash);
                    communicatedServers.add(occupiedRows[y]);
                    communicatedServers.add(occupiedCols[x]);
                }
                occupiedRows[y] =(occupiedRows[y] == -1)? hash: occupiedRows[y];
                occupiedCols[x] =(occupiedCols[x] == -1)? hash: occupiedCols[x];
            }
        }
        communicatedServers.remove(-1);
        return communicatedServers.size();
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] grid = {{1, 0}, {1, 1}};
        System.out.println("grid:");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("servers#:" + sol.countServers(grid));
    }
}
