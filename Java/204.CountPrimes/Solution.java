/* Math: O(n)
 * 1. Have notPrime array to denote if the current number is a prime
 * 2. If yes, accumulate count and times the number with 2, 3,... to make notPrime[number*2] = true, notPrime[number*3] = true..
 * 3. Otherwise, continue
 * 3. Since every number is visited at mots twice, ithe complexity is O(n)
 */          

import java.util.*; // Stack

public class Solution {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        
        return count;
    }
 
    public static void main(String[] args){
        Solution sol;
        int n = 10;
        sol = new Solution();

        System.out.println("n:" + n);
        int primesCount = sol.countPrimes(n);
        System.out.println("primesCount: " + primesCount);
    }
}
