/* Dynamic programming: Time:O(n), Space:O(n). However, LeetCode has one-shot O(1) space solution
 * 1. Get the longest left slope and store in dp[i][0], and right slope in dp[i][1]
 * 2. Traverse dp and get the longest mountain
 */

import java.util.*;

public class Solution{
    public int longestMountain(int[] A) {
        int length = A.length;
        int[][] dp = new int[length][2];
        for(int i = 1; i < length; ++i){
            dp[i][0] = (A[i] > A[i - 1])? Math.max(2, dp[i - 1][0] + 1): 0;
        }
        
        for(int i = length - 2; i > -1; --i){
            dp[i][1] = (A[i] > A[i + 1])? Math.max(2, dp[i + 1][1] + 1): 0;
        }
        
        int ret = 0;
        for(int i = 1; i < length - 1; ++i){
            ret = (dp[i][0] > 0 && dp[i][1] > 0)? Math.max(ret, dp[i][0] + dp[i][1] - 1): ret;
        }
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {2, 1, 4, 7, 3, 2, 5};
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("longest mountain: " + sol.longestMountain(A));
    }
}
