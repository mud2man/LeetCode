/* Dynamic programming: Time:O(N*P*G), Space:O(N*P*G), where N is group#
 * 1. dp[z][y][x] = the schemes of making x profit using y people with considering group[0, 1, ... z] and profit[0, 1, ... z]
 * 2. dp[z][y][P] is especially the schemes for P profit or larger of using y people
 * 3. dp[z][y][x] = dp[z - 1][y][x](not include profit[z]) + dp[z - 1][y - group[z - 1]][x - profit[z - 1]](include profit[z])
 * 4. dp[z][y][P] = dp[z - 1][y][P] + SumOf(dp[z - 1][prevY][x]), where Math.max(0, P - profit[z - 1]) <= x <= P 
 */

import java.util.*;

public class Solution{
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int[][][] dp = new int[group.length + 1][G + 1][P + 1];
        int base = 1_000_000_007;
        dp[0][0][0] = 1;
        for(int z = 1; z < group.length + 1; ++z){
            for(int y = 0; y < G + 1; ++y){
                //caculate the schemes with profit < P
                for(int x = 0; x < P; ++x){
                    dp[z][y][x] = dp[z - 1][y][x];
                    int prevX = x - profit[z - 1];
                    int prevY = y - group[z - 1];
                    dp[z][y][x] += (prevX >= 0 && prevY >= 0)? dp[z - 1][prevY][prevX]: 0;
                    dp[z][y][x] = dp[z][y][x] % base;
                }
                
                //caculate the schemes with profit >= P
                dp[z][y][P] = dp[z - 1][y][P];
                int prevY = y - group[z - 1];
                if(prevY >= 0){
                    int prevX = Math.max(0, P - profit[z - 1]);
                    for(int x = prevX; x < P + 1; ++x){
                        dp[z][y][P] += dp[z - 1][prevY][x];
                        dp[z][y][P] = dp[z][y][P] % base;
                    }
                }
            }
        }
        
        int schemes = 0;
        for(int y = 0; y < G + 1; ++y){
            schemes += dp[group.length][y][P];
            schemes = schemes % base;
        }
        return schemes;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int G = 5;
        int P = 3;
        int[] group = {2, 2};
        int[] profit = {2, 3};
        
        System.out.println("G: " + G);
        System.out.println("P: " + P);
        System.out.println("group: " + Arrays.toString(group));
        System.out.println("profit: " + Arrays.toString(profit));
        System.out.println("schemes: " + sol.profitableSchemes(G, P, group, profit));
    }
}
