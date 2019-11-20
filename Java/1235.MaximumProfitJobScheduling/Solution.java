/* Greedy + Dynamic programming:O(nlogn), Space:O(n)
 * 1. Have a DP map endTime2MaxProfit, where endTime2MaxProfit.get(i) is the maximum profit no later than endTime = i
 * 2. Sort intervals by enTime
 * 3. Update endTime2MaxProfit.get(i) by Math.max(endTime2MaxProfit.get(floorKey of start[i]) + profit[i], endTime2MaxProfit.get(endTime[i]))
 * 4. Update max in each loop
 */

import java.util.*;

public class Solution{
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] intervals = new int[startTime.length][3];
        for(int i = 0; i < intervals.length; ++i){
            intervals[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(intervals, (x, y) -> (x[1] - y[1]));
        TreeMap<Integer, Integer> endTime2MaxProfit = new TreeMap<>();
        int max = 0;
        for(int[] interval: intervals){
            Integer startFloorKey = endTime2MaxProfit.floorKey(interval[0]);
            int currProfit = (startFloorKey != null)? endTime2MaxProfit.get(startFloorKey) + interval[2]: interval[2];
            Integer endFloorKey = endTime2MaxProfit.floorKey(interval[1]);
            if(endFloorKey != null){
                currProfit = Math.max(currProfit, endTime2MaxProfit.get(endFloorKey));
            }
            endTime2MaxProfit.put(interval[1], currProfit);
            max = Math.max(max, currProfit);
        }
        return max;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] startTime = {1, 2, 3, 3};
        int[] endTime = {3, 4, 5, 6};
        int[] profit = {50, 10, 40, 70};
        System.out.println("startTime:" + Arrays.toString(startTime));
        System.out.println("endTime:" + Arrays.toString(endTime));
        System.out.println("profit:" + Arrays.toString(profit));
        System.out.println("max profit:" + sol.jobScheduling(startTime, endTime, profit));
    }
}
