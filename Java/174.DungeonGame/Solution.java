/* Dynamaic programming: Time:O(n*m), Space:O(1)
 * 1. dp[y][x] = the minimum life to pass this room and rescue the princess
 * 2. Start calculating from right-bottom corner, and pick up the easier direction (right or bottom)
 * 3. Get the minimum health point to pass the room (point = 1 after pass), and rescue princess (point = dp[y'][x'] after pass)
 * 4. dp[y][x] = minHealthPoint - dungeon[y][x];
 */

import java.util.*;

public class Solution{
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = dungeon;
        
        for(int y = dp.length - 1; y >= 0; --y){
            for(int x = dp[0].length - 1; x >= 0; --x){
                if(y == (dp.length - 1) && x == (dp[0].length - 1)){
                    dp[y][x] = (dungeon[y][x] > 0)? 0: 1 - dungeon[y][x];
                }
                else{
                    int toRight = (x < dp[0].length - 1)? dp[y][x + 1]: Integer.MAX_VALUE;
                    int toBottom = (y < dp.length - 1)? dp[y + 1][x]: Integer.MAX_VALUE;
                    int toMinDirc = Math.min(toRight, toBottom);
                    int minHealthPoint = Math.max(toMinDirc, 1);
                    dp[y][x] = minHealthPoint - dungeon[y][x];
                }
            }
        }
        
        return Math.max(1, dp[0][0]);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] dungeon = {{-2, -3, 3},{-5, -10, 1},{10, 30, -5}};
        
        System.out.println("dungeon:");
        for(int[] row: dungeon){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("minimum initial health: " + sol.calculateMinimumHP(dungeon));
    }
}

