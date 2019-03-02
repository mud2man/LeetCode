/* Math: Time:O(n^2*k), Space:O(n)
 * 1. dp[y][x] = the maximum vacation days on y-th week staying city x
 * 2. dp[y][x] = max(dp[y - 1][from] + days[x][y], ...), where 0 <= from < n
 * 3. Memorize the maximum vacation days "max" during update of dp
 */

import java.util.*;

public class Solution{
    public int maxVacationDays(int[][] flights, int[][] days) {
        int k = days[0].length;
        int n = days.length;
        int[][] dp = new int[2][n];
        int max = 0;
        
        for(int x = 1; x < n; ++x){
            dp[0][x] = -1;
        }
        
        for(int y = 1; y <= k; ++y){
            for(int x = 0; x < n; ++x){
                dp[y % 2][x] = -1;
                for(int z = 0; z < n; ++z){
                    int from = (y == 1)? 0: z;
                    if((flights[from][x] == 1 || from == x) && dp[(y - 1) % 2][from] != -1){
                        dp[y % 2][x] = Math.max(dp[y % 2][x], dp[(y - 1) % 2][from] + days[x][y - 1]);
                    }
                }
                max = Math.max(max, dp[y % 2][x]);
            }
        }

        return max;
    }

    public static void main(String[] args){
        int[][] flights = {{0, 1, 1}, {1, 0, 1},{1, 1, 0}};
        int[][] days = {{1, 3, 1}, {6, 0, 3},{3, 3, 3}};
        Solution sol = new Solution();
        
        System.out.println("flights:");
        for(int[] row: flights){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("days:");
        for(int[] row: days){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("maximum vacation days: " + sol.maxVacationDays(flights, days));
    }
}
