/* Greedy + Dynamic programming: Time:O(n), Space:O(n)
 * 1. dp[i][0][] = {top value, longest wiggle length ending at top considering {nums[0], nums[1], ... nums[i]} 
 * 2. dp[i][1][] = {bottom value, longest wiggle length ending at bottom considering {nums[0], nums[1], ... nums[i]} 
 * 3. We update dp[i] if we can make the length longer or make the top/bottom higher/lower which can meet the greedy property 
 */

import java.util.*;

public class Solution{
    public int wiggleMaxLength(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[][][] dp = new int[nums.length][2][2]; //{{top, maxLength}, {bottom, maxLength}}
        dp[0][0] = new int[]{nums[0], 1};
        dp[0][1] = new int[]{nums[0], 1};
        for(int i = 1; i < nums.length; ++i){
            int num = nums[i];
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i - 1][1];
            if(num > dp[i - 1][1][0]){
                if(dp[i - 1][1][1] + 1 > dp[i][0][1]){
                    dp[i][0] = new int[]{num, dp[i - 1][1][1] + 1};
                }else if(dp[i - 1][1][1] + 1 == dp[i][0][1]){
                    dp[i][0] = (num > dp[i][0][0])? new int[]{num, dp[i - 1][1][1] + 1}: dp[i][0];
                }
            }else if(num < dp[i - 1][1][0] && dp[i][1][1] <= dp[i - 1][1][1]){
                dp[i][1] = new int[]{num, dp[i - 1][1][1]};
            }

            if(num < dp[i - 1][0][0]){
                if(dp[i - 1][0][1] + 1 > dp[i][1][1]){
                    dp[i][1] = new int[]{num, dp[i - 1][0][1] + 1};
                }else if(dp[i - 1][0][1] + 1 == dp[i][1][1]){
                    dp[i][1] = (num < dp[i][1][0])? new int[]{num, dp[i - 1][0][1] + 1}: dp[i][1];
                }
            }else if(num > dp[i - 1][0][0] && dp[i][0][1] <= dp[i - 1][0][1]){
                dp[i][0] = new int[]{num, dp[i - 1][0][1]};
            }
        }
        return Math.max(dp[nums.length - 1][0][1], dp[nums.length - 1][1][1]);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 7, 4, 9, 2, 5};
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("maximun wiggle length:" + sol.wiggleMaxLength(nums));
    }
}
