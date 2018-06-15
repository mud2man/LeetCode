/* Math: Time:O(n*m), Space:O(n)
 * 1. Have "row" to store the number of people on y-th row
 * 2. Have "col" to store the number of people on x-th column
 * 3. Start from up-left corner
 * 4. When shift right, add distance with the people count on left side, and subtract distance with that on right side
 * 5. When shift down, Add distance with the people count on up side, and subtract distance with that on down side
 * 6. Get the minimum distance after traverse all positions
 */

import java.util.*;

public class Solution{
    public int minTotalDistance(int[][] grid) {
        int depth = grid.length;
        int width = (depth > 0)? grid[0].length: 0;
        int initDis = 0;
        int[] col = new int[width];
        int[] row = new int[depth];
        int peopleCount = 0;
        
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(grid[y][x] == 1){
                    initDis += (y + x);
                    col[x]++;
                    row[y]++;
                    peopleCount++;
                }
            }
        }
        
        int up = 0;
        int bottom = peopleCount;
        int minDis = initDis;
        for(int y = 0; y < depth; ++y){
            int dis = initDis;
            int left = 0;
            int right = peopleCount;
            for(int x = 0; x < width; ++x){
                minDis = Math.min(minDis, dis);
                left += col[x];
                right -= col[x];
                dis = dis + left - right;
            }
            up += row[y];
            bottom -= row[y];
            initDis = initDis + up - bottom;
        }
        return minDis;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] grid = {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        System.out.println("grid: ");
        for(int[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("minimum distance: " + sol.minTotalDistance(grid));
    }
}
