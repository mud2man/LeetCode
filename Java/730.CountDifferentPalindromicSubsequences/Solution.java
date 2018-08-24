/* Dynamic Programming: Time:O(n^3), Space:O(n^2). LeetCode has a O(n^2) solution
 * 1. dp[y][x] = the count of different palindromic subsequences between S.substring(x, y)
 * 2. dp[y][x] = dp[y - 1][x](without S.charAt(y)) + dp[y - 1][z](with S.charAt(y)) - dp[w][z](intersection), 
      where S.charAt(z - 1) == S.charAt(y), S.charAt(w + 1) == S.charAt(y)
 */

import java.util.*;

public class Solution{
    private int helper(String S, int[][] dp, int y, int x){
        if(y == x){
            return 1;
        }   
        
        char target = S.charAt(y);
        int countWithout = dp[y - 1][x];
        int headIdx = x;
        int tailIdx = y - 1;
        
        while(S.charAt(headIdx) != target && headIdx <= tailIdx){headIdx++;}
        headIdx++;
        int countWith = (headIdx <= tailIdx)? dp[tailIdx][headIdx] + 1: 1;
        
        if(headIdx > tailIdx){
            return (countWithout + countWith) % 1000000007;
        }
        
        while(S.charAt(tailIdx) != target && headIdx <= tailIdx){tailIdx--;}
        tailIdx--;
        
        int countIntersect;
        if(headIdx <= tailIdx){
            // don't take any chars between headIdx and tailIdx is also an option, so add 1
            countIntersect = dp[tailIdx][headIdx] + 1;
        }
        else if(S.charAt(headIdx) == target){
            // S.charAt(headIdx) is the only insection
            countIntersect = 1;
        }
        else{
            countIntersect = 0;
        }
        
        int count = (countWithout + countWith - countIntersect) % 1000000007;
        count = (count < 0)? count += 1000000007: count;
        return count;
    }
    
    public int countPalindromicSubsequences(String S) {
        int length = S.length();
        int[][] dp = new int[length][length];
        
        for(int y = 0; y < length; ++y){
            for(int x = y; x >= 0; --x){
                dp[y][x] = helper(S, dp, y, x);
            }
        }
        
        return dp[length - 1][0];
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "bccb";
        
        System.out.println("S: " + S);
        System.out.println("count:" + sol.countPalindromicSubsequences(S));
    }
}
