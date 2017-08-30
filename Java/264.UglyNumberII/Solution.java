/* Use Dynamic Programing: O(n)
 * 1. positions[0]: pointer for 2, positions[1]: pointer for 3, positions[2]: pointer for 5
 * 2. dp[i] = i-th ugly number
 * 3. dp[i] = min(dp[positions[0]] * 2, dp[positions[1]] * 3, dp[positions[2]] * 5)
 */

import java.util.*; // Stack

public class Solution{
    public int nthUglyNumber(int n) {
        int dp[] = new int[n];
        // positions[0]: pointer for 2, positions[1]: pointer for 3, positions[2]: pointer for 5 
        int positions[] = new int[3];
        
        dp[0] = 1;
        for(int i = 1; i < n; ++i){
            int min = 2 * dp[positions[0]];
            min = Math.min(min, 3 * dp[positions[1]]);
            min = Math.min(min, 5 * dp[positions[2]]);
            dp[i] = min;
            
            if(min == 2 * dp[positions[0]]){
                positions[0]++;
            }
            
            if(min == 3 * dp[positions[1]]){
                positions[1]++;
            }
            
            if(min == 5 * dp[positions[2]]){
                positions[2]++;
            }
        }
        
        return dp[n - 1];
    }

    public static void main(String[] args){
        int ugly;
        int n = 10;
        Solution sol;
        
        sol = new Solution();
        ugly = sol.nthUglyNumber(n);
        
        System.out.println(n + "-th ugly: " +  ugly);
    }
}
