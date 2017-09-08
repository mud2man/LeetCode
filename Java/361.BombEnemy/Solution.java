/* O(m*n)
 * 1. Caculate the enemy# from left, top, right, and bottom
 * 2. enemy[y][x] =  fromLeft[y][x - 1] + fromTop[y - 1][x] + fromRight[y][x + 1] + fromBottom[y + 1][x]
 */

import java.util.*;

public class Solution{
    public int maxKilledEnemies(char[][] grid) {
        int height = grid.length;
        int width = (height > 0) ? grid[0].length: 0;
        int[][] fromLeft = new int[height][width];
        int[][] fromTop = new int[height][width];
        int[][] fromRight = new int[height][width];
        int[][] fromBottom = new int[height][width];
        
        // accumulate from left
        for(int y = 0; y < height; ++y){
            for(int x = 0; x < width; ++x){
                char c = grid[y][x];
                switch (c){
                    case 'W':
                        fromLeft[y][x] = 0;
                        break;
                    case 'E':
                        fromLeft[y][x] = (x > 0)? fromLeft[y][x - 1] + 1: 1;
                        break;
                    default:
                        fromLeft[y][x] = (x > 0)? fromLeft[y][x - 1]: 0;
                        break;
                }
            }
        }
        
        // accumulate from top
        for(int x = 0; x < width; ++x){
            for(int y = 0; y < height; ++y){
                char c = grid[y][x];
                switch (c){
                    case 'W':
                        fromTop[y][x] = 0;
                        break;
                    case 'E':
                        fromTop[y][x] = (y > 0)? fromTop[y - 1][x] + 1: 1;
                        break;
                    default:
                        fromTop[y][x] = (y > 0)? fromTop[y - 1][x]: 0;
                        break;
                }
            }
        }
        
        // accumulate from right
        for(int y = 0; y < height; ++y){
            for(int x = width - 1; x >= 0; --x){
                char c = grid[y][x];
                switch (c){
                    case 'W':
                        fromRight[y][x] = 0;
                        break;
                    case 'E':
                        fromRight[y][x] = (x < width - 1)? fromRight[y][x + 1] + 1: 1;
                        break;
                    default:
                        fromRight[y][x] = (x < width - 1)? fromRight[y][x + 1]: 0;
                        break;
                }
            }
        }
        
        // accumulate from bottom
        for(int x = 0; x < width; ++x){
            for(int y = height - 1; y >= 0; --y){
                char c = grid[y][x];
                switch (c){
                    case 'W':
                        fromBottom[y][x] = 0;
                        break;
                    case 'E':
                        fromBottom[y][x] = (y < height - 1)? fromBottom[y + 1][x] + 1: 1;
                        break;
                    default:
                        fromBottom[y][x] = (y < height - 1)? fromBottom[y + 1][x]: 0;
                        break;
                }
            }
        }
        
        int maxCount = 0;
        for(int y = 0; y < height; ++y){
            for(int x = 0; x < width; ++x){
                char c = grid[y][x];
                if(c == '0'){
                    int countLeft = (x > 0)? fromLeft[y][x - 1]: 0;
                    int countTop = (y > 0)? fromTop[y - 1][x]: 0;
                    int countRight = (x < width - 1)? fromRight[y][x + 1]: 0;
                    int countBottom = (y < height - 1)? fromBottom[y + 1][x]: 0;
                    maxCount = Math.max(maxCount, countLeft + countTop + countRight + countBottom);
                }
            }
        }
        
        return maxCount;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] grid = {{'0', 'E', '0', '0'},
                         {'E', '0', 'W', 'E'},
                         {'0', 'E', '0', '0'}};

        System.out.println("grid: ");
        for(char[] row: grid){
            System.out.println(Arrays.toString(row));
        }
        int maxCount = sol.maxKilledEnemies(grid);
        System.out.println("maximum count: " + maxCount);
    }
}
