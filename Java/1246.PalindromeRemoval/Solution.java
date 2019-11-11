/* Dynamic programming:O(n^3), Space:O(n^2)
 * 1. dp[end][start] = the minimum removal #
 * 2. dp[end][start] = min{dp[mid - 1][start] + dp[end - 1][mid + 1]) or (dp[mid - 1][start] + dp[end - 1][mid + 1] given arr[mid] == arr[end], ...), where start <= mid <= end
 */

import java.util.*; // Stack

public class Solution {
    public int minimumMoves(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        for(int end = 0; end < arr.length; ++end){
            dp[end][end] = 1;
            for(int start = end - 1; start >= 0; --start){
                dp[end][start] = end - start + 1;
                for(int mid = start; mid <= end; mid ++){
                    int leftCount =(mid > start)? dp[mid - 1][start]: 0;
                    if(arr[mid] == arr[end]){
                        if(end - mid < 2){
                            dp[end][start] = Math.min(dp[end][start], leftCount + 1);
                        }else{
                            dp[end][start] = Math.min(dp[end][start], leftCount + dp[end - 1][mid + 1]);
                        }
                    }else{
                        dp[end][start] = Math.min(dp[end][start], leftCount + dp[end][mid] );
                    }
                }
            }
        }
        return dp[arr.length - 1][0];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] arr = {1, 3, 4, 1, 5};
        System.out.println("arr:" + Arrays.toString(arr));
        System.out.println("minimum moves:" + sol.minimumMoves(arr));
    }
}
