/* Dynamic Programing: O(n*m). Can be reduced to O(n*logm) by using minHeap
 * 1. Declare the pointer in the ugly number list, every pointer refer to the associated prime
 * 2. dp(i) = min(dp(ptr0)*p0, dp(ptr1)*p1, ...)
 */

import java.util.*;

public class Solution{
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        int[] pointers = new int[primes.length];
        
        dp[0] = 1;
        for(int i = 1; i < n; ++i){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < primes.length; ++j){
                min = Math.min(min, primes[j] * dp[pointers[j]]);
            }
            
            dp[i] = min;
            
            for(int j = 0; j < primes.length; ++j){
                if(primes[j] * dp[pointers[j]] == min){
                    pointers[j]++;
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args){
        int i;
        Solution sol;
        int[] primes = {2, 7, 13, 19};
        
        System.out.println("Ugly numbers: ");

        for(i = 1; i < 20; i++){
            sol = new Solution();
            System.out.print(sol.nthSuperUglyNumber(i, primes) +", ");

            if(i%5 == 4){
                System.out.println();
            }
        }
    }
}
