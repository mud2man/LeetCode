/* Dynamic programming: Time:O(n), Space:O(1)
 * 1. We start from left with length = L and right with length = M
 * 2. dp[i] = the maximum sum of continuous L-long subarray considering {A[0], A[1], ... A[i]}
 * 3. Update dp[i] by max(dp[i - 1], windowL), and max by max(max, dp[i - M] + windowM)
 * 4. Then do step 1, 2, and 3 with swapping L and M
 */

import java.util.*;

public class Solution{
    private int helper(int[] A, int L, int M){
        int windowL = 0;
        for(int i = 0; i < L; ++i){
            windowL += A[i];
        }
        int windowM = 0;
        for(int i = L; i < L + M; ++i){
            windowM += A[i];
        }
        int max = windowL + windowM;
        int[] dp = new int[2];
        dp[(L - 1) % 2] = windowL;
        for(int i = L + M; i < A.length; ++i){
            windowM += A[i];
            windowM -= A[i - M];
            windowL += A[i - M];
            windowL -= A[i - M - L];
            dp[(i - M) % 2] = Math.max(dp[(i - M - 1) % 2], windowL);
            max = Math.max(max, dp[(i - M) % 2] + windowM);
        }
        return max;
    }
    
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        return Math.max(helper(A, L, M), helper(A, M, L));
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {0, 6, 5, 2, 2, 5, 1, 9, 4};
        int L = 1;
        int M = 2;
        System.out.println("L:" + L);
        System.out.println("M:" + M);
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("maximun sum of 2 non-overlapping subarray:" + sol.maxSumTwoNoOverlap(nums, L, M));
    }
}
