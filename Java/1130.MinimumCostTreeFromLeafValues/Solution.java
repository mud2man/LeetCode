/* Dynamic Programming: Time:O(n^3), Space:O(n^2). Leetcode has O(n) stack solution
 * 1. dp[right][left] = min(max[i]][left] * max[right][i + 1] + dp[i][left] + dp[right][i + 1]), where 0 <= i <= right - 1 
 * 2. answer is dp[arr.length - 1][0]  
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int mctFromLeafValues(int[] arr) {
        int[] maxFromLeft = new int[arr.length];
        int[][] dp = new int[arr.length][arr.length];
        int[][] max = new int[arr.length][arr.length];
        for(int right = 0; right < arr.length; right++){
            maxFromLeft[right] =(right == 0)? arr[right] : Math.max(maxFromLeft[right - 1], arr[right]);
            int maxFromRight = arr[right];
            max[right][right] = arr[right];
            for(int left = right - 1; left >= 0; left--){
                max[right][left] = Math.max(arr[left], max[right][left + 1]);
                dp[right][left] = Integer.MAX_VALUE;
                for(int leftBoundry = left; leftBoundry < right; leftBoundry++){
                    int curr =  max[leftBoundry][left] * max[right][leftBoundry + 1] + dp[leftBoundry][left] + dp[right][leftBoundry + 1];
                    dp[right][left] = Math.min(dp[right][left], curr);
                }
            }
        }
        return dp[arr.length - 1][0];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] arr = {6, 2, 4};
        System.out.println("arr:" + Arrays.toString(arr));
        System.out.println("minimum cost:" + sol.mctFromLeafValues(arr));
    }
}
