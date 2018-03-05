/* Dynamic programming: Time:O(l*n*m*), Space:O(l*m*n)
 * 1. dp[i][j][k] = the maximum number of strings that you can form with given j 0s and k 1s in strs[0, 1, 2, ... i]
 * 2. dp[i][j][k] = max(selectedIth, notSelectedIth)
 * 3. selectedIth = dp[i - 1][ j - zeorOneCounts[i][0] ][ k - zeorOneCounts[i][1] ] + 1
 * 4. notSelectedIth = dp[i - 1][j][k]
 * 5. Answer is dp[strs.length - 1][m][n]
 */

import java.util.*;

public class Solution{
    private int[] getZeroOneCount(String str){
        int[] zeorOneCount = new int[2];
        for(int i = 0; i < str.length(); ++i){
            char c = str.charAt(i);
            if(c == '0'){
                zeorOneCount[0]++;
            }
            else{
                zeorOneCount[1]++;
            }
        }
        return zeorOneCount;
    }
    
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] zeorOneCounts = new int[strs.length][];
        
        for(int i = 0; i < strs.length; ++i){
            zeorOneCounts[i] = getZeroOneCount(strs[i]);
        }
        
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        
        for(int i = 0; i < strs.length; ++i){
            for(int j = 0; j <= m; ++j){
                for(int k = 0; k <= n; ++k){
                    int previousJ = j - zeorOneCounts[i][0];
                    int previousK = k - zeorOneCounts[i][1];
                    if(i > 0){
                        int selectedIth = (previousJ >= 0 && previousK >= 0)? dp[i - 1][previousJ][previousK] + 1: 0;
                        int notSelectedIth = dp[i - 1][j][k];
                        dp[i][j][k] = Math.max(selectedIth, notSelectedIth); 
                    }
                    else{
                        dp[i][j][k] = (previousJ >= 0 && previousK >= 0)? 1: 0;
                    }   
                }
            }
        }
        
        return dp[strs.length - 1][m][n];
    }

    public static void main(String[] args){
        Solution sol;
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        
        sol = new Solution();
        System.out.println("strs: " + Arrays.toString(strs));
        System.out.println("m: " + m);
        System.out.println("n: " + n);
        System.out.println("maximum number of strings: " + sol.findMaxForm(strs, m, n));
    }
}
