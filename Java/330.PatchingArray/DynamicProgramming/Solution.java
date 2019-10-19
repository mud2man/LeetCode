/* Dynamic programming: Time:O(n^2), Space:O(n)
 * 1. dp[i] = if number i be filled
 * 2. First, we construct the initial dp[] given "nums", by traverse nums from left, and update dp[i] from right
 * 3. Then we traverse dp[]. If dp[i] is false, we can say choosing num = i to be filled is the best solution. There are 2 reasons.
 *    3.1 Any num > i is impossible to fill dp[i]
 *    3.2 Any num < i cover less number than num = i
 * 4. After deciding fill num = i, we update the coverage from right of dp[], and accumulate "count"
 */

import java.util.*;

public class Solution{
    public static int minPatches(int[] nums, int n) {
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int num: nums){
            for(int i = n; i >= 0; --i){
                if(i - num >= 0 && dp[i - num] == true){
                    dp[i] = true;
                }
            }
        }
        
        int count = 0;
        for(int i = 0; i <= n; ++i){
            if(dp[i] == true){
                continue;
            }else{
                int newNum = i;
                for(int j = n; j  - newNum >= 0; --j){
                    dp[j] = dp[j - newNum]? true: dp[j];
                }
                count++;
            }
        }
        return count;
    }
   
    public static void main(String[] args){
        int[] nums = {1, 3};
        int n = 6;
        System.out.println("nums: " + Arrays.toString(nums));;
        System.out.println("n: " + n);
        System.out.println("patch#: " + minPatches(nums, n));
    }
}
