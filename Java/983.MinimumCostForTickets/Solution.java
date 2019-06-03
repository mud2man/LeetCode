/* Divide and Conquer: Time:O(n), Space:O(n)
 * 1. dp[day] = the minimum cost to cover from day-1 to day-day
 * 2. The optimal solution must contains ones where all tickets bought on days in the input "days"
 * 3. And dp[day1] <= dp[day2], where day1 < day2. 
 * 4. dp[day] = min{dp[day0] + cost[0], dp[day1] + cost[1], dp[day2] + cost[2]}, where day0 = largest day <= day - 1, day1 = largest day <= day - 7,and day2 = largest day <= day - 30
 * 5. All dp[day] is visited at most 3 times, so time complexity is O(n)
 */

import java.util.*; 
import java.math.*;

public class Solution {
    private int helper(Integer[] dp, int[] days, int endIdx, int[] costs){
        int day = days[endIdx];
        if(dp[day] != null){
            return dp[day];
        }
        
        int idx = endIdx;
        int cost = helper(dp, days, idx - 1, costs) + costs[0];
        
        while(idx > 0 && days[idx] + 7 > day){
            idx--;
        }
        cost = Math.min(cost, helper(dp, days, idx, costs) + costs[1]);
        
        while(idx > 0 && days[idx] + 30 > day){
            idx--;
        }
        cost = Math.min(cost, helper(dp, days, idx, costs) + costs[2]);
        dp[day] = cost;
        return cost;
    }
    
    public int mincostTickets(int[] days, int[] costs) {
        Integer[] dp = new Integer[366];
        dp[0] = new Integer(0);
        int[] cloneDays = new int[days.length + 1];
        for(int i = 0; i < days.length; ++i){
            cloneDays[i + 1] = days[i];
        }
        return helper(dp, cloneDays, cloneDays.length - 1, costs);
    }
 
    public static void main(String[] args){
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {2, 7, 15};
        Solution sol = new Solution();
        System.out.println("days:" + Arrays.toString(days));
        System.out.println("costs:" + Arrays.toString(costs));
        System.out.println("mincost: " + sol.mincostTickets(days, costs));
    }
}
