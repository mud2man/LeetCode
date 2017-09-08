/* Combination Theorey: complexity = O(n)
 * 1. Caculate the number with fixed number of digits from 0 to n. 
 * 2. Sum all the specific count with the specific number of digits
 * 3. If number of digits > 10, there is no unique digit number
 */

import java.util.*; // Stack

public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        int[] dp = new int[11];
        dp[0] = 1;
        dp[1] = 9;
        
        for(int i = 2; i <= Math.min(n, 10); ++i){
            dp[i] = dp[i - 1] * (11 - i);
        }
        
        int count = 0;
        for(int i = 0; i <= Math.min(n, 10); ++i){
            count = count + dp[i];
        }
        
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int n = 2;

        sol = new Solution();
        System.out.println("n: " + n);
        System.out.println("count: " + sol.countNumbersWithUniqueDigits(n));
    }
}
