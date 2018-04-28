/* Math + Dynamic Programming: O(n ^ 2)
 * 1. Let dp[i] = the maximum number used by i key strokes
 * 2. dp[i] must be made by with the final sequence = ctlA + ctlC + ctlV + ctlV + ....
 * 3. Because if there is any A in the final sequence of dp[i], we can move A to the beginning of the final sequence.
 * 4. Then we can produce a bigger solution, which contradict that dp[i] is the biggest number
 * 5. dp[i] = (count + 1) * dp[j], where j decrease from (i - 3), and count increase from 1
 * 6. In mathmatics, the curve y = (x)*(constant - x) has only one local maximum, and it is global maximun too.
 * 7. So we can decrease j until (count + 1) * dp[j] starts to decrease
 */

import java.util.*;


public class Solution{
    public int maxA(int N) {
        int[] dp = new int[N + 6];
        for(int i = 1; i < 6; ++i){
            dp[i] = i;
        }
        
        for(int i = 6; i <= N; ++i){
            int j = i - 3;
            int count = 1;
            while(dp[i] <= (count * dp[j] + dp[j])){
                dp[i] = count * dp[j] + dp[j];
                j--;
                count++;
            }
        }
        
        return dp[N];
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 7;
        
        System.out.println("n: " + n);
        System.out.println("maximum number of A: " + sol.maxA(n));
    }
}
