/* Dynamic programming: Time:O(N*P*G), Space:O(N*P*G), where N is group#
 * 1. dp[z][y][x] = the schemes of making x profit using y people with considering group[0, 1, ... z] and profit[0, 1, ... z]
 * 2. dp[z][y][P] is especially the schemes for P profit or larger of using y people
 * 3. If not select (group[z], profit[z]) = (g, p), we accumulate dp[z][y][x] to dp[z + 1][y][x]
 * 4. If select (group[z], profit[z]) = (g, p), we accumulate dp[z][y][x] to dp[z + 1][y + g][x + p]
 * 5. For each traversal, we consider both cases of selecting and no-slecting
 */

import java.util.*;

public class Solution{
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int[][][] dp = new int[group.length + 1][G + 1][P + 1];
        int base = 1_000_000_007;
        dp[0][0][0] = 1;
        for(int z = 0; z < group.length; ++z){
            int g = group[z];
            int p = profit[z];
            for(int y = 0; y < G + 1; ++y){
                for(int x = 0; x < P + 1; ++x){
                    //not select (g, p)
                    dp[z + 1][y][x] = (dp[z + 1][y][x] + dp[z][y][x]) % base;
                    //select (g, p)
                    if(y + g <= G){
                        if(x + p >= P){
                            dp[z + 1][y + g][P] = (dp[z][y][x] + dp[z + 1][y + g][P]) % base;
                        }else{
                            dp[z + 1][y + g][x + p] = (dp[z][y][x] + dp[z + 1][y + g][x + p]) % base;
                        }
                        
                    }
                }
            }
        }
        
        int schemes = 0;
        for(int y = 0; y < G + 1; ++y){
            schemes = (schemes + dp[group.length][y][P]) % base;
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
