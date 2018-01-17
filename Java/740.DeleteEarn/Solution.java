/* Dynamic Programming: Time:O(n), Space:O(n), where n is max(nums#, maxNum)
 * 1. Construct count map and get the maximum number
 * 2. dp[i] = the maximum result when consider taking number i
 */         

import java.util.*;

public class Solution {
    public int deleteAndEarn(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        
        HashMap<Integer, Integer> countsMap = new HashMap<Integer, Integer>();
        int maxNum = 0;
        for(int num: nums){
            countsMap.putIfAbsent(num, 0);
            countsMap.put(num,  countsMap.get(num) + 1);
            maxNum = Math.max(maxNum, num);
        }

        int[] dp = new int[maxNum + 1];
        dp[1] = countsMap.containsKey(1)? 1 * countsMap.get(1): 0;        
        for(int i = 2; i <= maxNum; ++i){
            dp[i] = countsMap.containsKey(i)? Math.max(dp[i - 1], (dp[i - 2] + i * countsMap.get(i))): dp[i - 1];
        }
        
        return dp[maxNum];
    }

    public static void main(String[] args){
        Solution sol; 
        int[] nums = {3, 4, 2};
        sol = new Solution();
        
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maximum earning: " + sol.deleteAndEarn(nums));
    }
}
