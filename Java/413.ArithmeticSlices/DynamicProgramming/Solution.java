/* Dynamic Programming: Time:O(n), Space:O(n)
 * 1. dp[i] = the length of arithmetic sequence ended with index i
 * 2. If (A[i] - A[i - 1]) == (A[i - 1] - A[i - 2]), assign dp[i] with dp[i - 1. Otherwise, assign with 2], and accumualte slices
 *
 * ex: input 1, 2, 3, 4, 1, -2, -5
 * dp[]: {1, 2, 3, 4, 2, 3, 4}, num = 3 + 3
 */

import java.util.*; // Stack

public class Solution {
    private int getSlices(int head, int tail){
        int len = tail - head - 2;
        int num = (len * (len + 1)) / 2;
        return num;
    }
    
    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3){
            return 0;
        }
        
        int[] dp = new int[A.length];
        dp[0] = 1;
        dp[1] = 2;
        int num = 0;
        for(int i = 2; i < A.length; ++i){
            if((A[i] - A[i - 1]) == (A[i - 1] - A[i - 2])){
                dp[i] = dp[i - 1] + 1;
            }
            else{
                dp[i] = 2;
                num += getSlices(i - dp[i - 1], i);
            }
        }
        num += getSlices(A.length - dp[A.length - 1], A.length);
        return num; 
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {1, 2, 3, 4}; 
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("slices: " + sol.numberOfArithmeticSlices(A));
    }
}
