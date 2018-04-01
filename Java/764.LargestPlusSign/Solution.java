/* Dynamaic programming: Time:O(n^2), Space:O(n^2)
 * 1. Create grid[][], grid[y][x] = true if it's a minus
 * 2. Compute orders[y][x][0] from left, where orders[y][x][0] is the longest order to left
 * 3. Compute orders[y][x][1] from left, where orders[y][x][1] is the longest order to right
 * 4. Compute orders[y][x][2] from left, where orders[y][x][2] is the longest order to top
 * 5. Compute orders[y][x][3] from left, where orders[y][x][3] is the longest order to bottom
 * 6. Traverse orders[y][x][i], and get the order with center grid[y][x], and update maxOrder at the same time 
 *
 */

import java.util.*;

public class Solution{
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        boolean[][] grid = new boolean[N][N];
        
        for(int[] minus: mines){
            grid[minus[0]][minus[1]] = true;
        }
        
        int[][][] orders = new int[N][N][4]; //left, right, up, down
        
        //from left
        for(int y = 0; y < N; ++y){
            int closestX = -1;
            for(int x = 0; x < N; ++x){
                closestX = (grid[y][x] == true)? x: closestX;
                orders[y][x][0] = (grid[y][x] == true)? 0: (x - closestX);
            }
        }
        
        //from right
        for(int y = 0; y < N; ++y){
            int closestX = N;
            for(int x = N - 1; x >= 0; --x){
                closestX = (grid[y][x] == true)? x: closestX;
                orders[y][x][1] = (grid[y][x] == true)? 0: (closestX - x);
            }
        }
        
        //from top
        for(int x = 0; x < N; ++x){
            int closestY = -1;
            for(int y = 0; y < N; ++y){
                closestY = (grid[y][x] == true)? y: closestY;
                orders[y][x][2] = (grid[y][x] == true)? 0: (y - closestY);
            }
        }
        
        //from bottom
        for(int x = 0; x < N; ++x){
            int closestY = N;
            for(int y = N - 1; y >= 0; --y){
                closestY = (grid[y][x] == true)? y: closestY;
                orders[y][x][3] = (grid[y][x] == true)? 0: (closestY - y);
            }
        }
        
        int maxOrder = 0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                int minOrder = N;
                for(int i = 0; i < 4; ++i){
                    minOrder = Math.min(minOrder, orders[y][x][i]);
                }
                maxOrder = Math.max(maxOrder, minOrder);
            }
        }
        
        return maxOrder;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] mines = {{4, 2}};
        int N = 5;
        
        sol = new Solution();
        System.out.println("mines: ");
        for(int[] minus: mines){
            System.out.println(Arrays.toString(minus));
        }
        System.out.println("N: " + N);
        System.out.println("maximum order: " + sol.orderOfLargestPlusSign(N, mines));
    }
}

