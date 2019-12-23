/* Math: Time:O(square(n)), Space:O(1)
 * 1. Calculate the limit of number of consecutive numbers by getting the answer form equation: (1 + 2 + ... + limit) = N
 * 2. For odd consecutive numbers, N = (x - k) + ... (x) + (x + k). So N can be divide by the number of consecutive numbers 
 * 3. For even consecutive numbers, N = (x - k) + ... + x + (x + 1) + ... (x + k + 1). So N + (k + 1) can be divide by the number of consecutive numbers  
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public int consecutiveNumbersSum(int N) {
        long n = (long)N;
        int limit = ((int)Math.sqrt(1 + 8 * n) - 1) / 2;
        int count = 0;
        int offset = 1;
        for(int i = 1; i <= limit; ++i){
            if(i % 2 == 1){
                count += (N % i == 0)? 1: 0;
            }else{
                count += ((N + offset) % i == 0)? 1: 0;
                offset++;
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 9;
        System.out.println("N: " + N);
        System.out.println("number of consecutive numbers: " + sol.consecutiveNumbersSum(N));
    }
}
