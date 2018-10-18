/* Dynamic Programming: Time:O(n), Space:O(n)
 * 1. dp[i] = the number of 1s of i
 * 2. dp[i] = 1 + dp[i % base], whew base is MSB
 */

import java.util.*;

public class Solution {
    public int[] countBits(int num) {
        if(num == 0){
            return new int[]{0};
        }
        else if(num == 1){
            return new int[]{0, 1};
        }
        else{
            int[] dp = new int[num + 1];
            dp[1] = 1;
            for(int i = 2, base = 2; i <= num; ++i){
                if(i == base * 2){
                    dp[i] = 1;
                    base *= 2;
                }
                else{
                    dp[i] = 1 + dp[i % base];
                }
            }
            return dp;
        }
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int num = 5;
        System.out.println("num: " + num);
        System.out.println("bits array:" + Arrays.toString(sol.countBits(num)));
    }
}
