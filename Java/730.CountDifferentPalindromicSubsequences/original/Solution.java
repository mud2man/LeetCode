/* Dynamic Programming: Time:O(n^2), Space:O(n^2).
 * 1. dp[i][tail][head] = the count of different palindromic subsequences in S.substring(head, tail + 1), with two endpoints = 'a' + i
 * 2. Traverse on tail (0, n - 1), head = (0, tail), i = (0, 3)
 * 3. There are three cases: two endpoints = 'a' + i, S.charAt(head) != ('a' + i), and others
 * 4. In case0: differentiate with head and tail
 * 5. In case1: dp[i][tail][head] = (head < tail)? dp[i][tail][head + 1]: 0
 * 6. In case2: dp[i][tail][head] = (head < tail)? dp[i][tail - 1][head]: 0;
 */

import java.util.*;

public class Solution{
       public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int[][][] dp = new int[4][n][n];
        for(int tail = 0; tail < n; ++tail){
            for(int head = tail; head >= 0; --head){
                for(int i = 0; i < 4; ++i){
                    if(S.charAt(head) == ('a' + i) && S.charAt(tail) == ('a' + i)){
                        if(head == tail){
                            dp[i][tail][head] = 1;
                        }else if(head == tail - 1){
                            dp[i][tail][head] = 2;
                        }else{
                            for(int j = 0; j < 4; ++j){
                                dp[i][tail][head] += dp[j][tail - 1][head + 1];
                                dp[i][tail][head] = dp[i][tail][head] % 1000000007;
                            }
                            //case0: don't take any chars between tail - 1 and head + 1
                            //case1: only take S.charAt(head);
                            dp[i][tail][head]+=2;
                        }
                    }else if(S.charAt(head) != ('a' + i)){
                        dp[i][tail][head] = (head < tail)? dp[i][tail][head + 1]: 0;
                    }else{
                        dp[i][tail][head] = (head < tail)? dp[i][tail - 1][head]: 0;
                    }
                }
            }
        }
        
        int sum = 0;
        for(int i = 0; i < 4; ++i){
            sum += dp[i][n - 1][0];
            sum %= 1000000007;
        }
        return sum;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "bccb";
        
        System.out.println("S: " + S);
        System.out.println("count:" + sol.countPalindromicSubsequences(S));
    }
}
