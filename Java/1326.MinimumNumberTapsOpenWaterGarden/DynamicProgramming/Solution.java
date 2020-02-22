/* Dynamic programming: Time:O(n * c), Space:O(n), where c is 100
 * 1. dp[i] = the minimun taps covered with ending at index-i
 * 2. dp[i] = min(dp[j - ranges[j]] + 1), where j - ranges[j] <= i <= j + ranges[j]
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 2);
        for(int i = 0; i < ranges.length; ++i){
            if(ranges[i] == 0){
                continue;
            }
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            int base =(left == 0)? 0: dp[left];
            for(int j = left; j <= right; ++j){
                dp[j] = Math.min(dp[j], base + 1);
            }
        }
        return (dp[n] < n + 2)? dp[n]: -1;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 5;
        int[] ranges = {3, 4, 1, 1, 0, 0};
        System.out.println("ranges: " + Arrays.toString(ranges));
        System.out.println("minimun taps#: " + sol.minTaps(n, ranges));
    }
}
