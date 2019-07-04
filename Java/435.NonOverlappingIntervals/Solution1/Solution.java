/* Sort and Greedy: Time:O(nlogn), Space:O(1). Solution2 is better
 * 1. The same as the problem: find the maximum number of intervals that are non-overlapping
 * 2. Sort intervals by start time / end time, and have "previousInterval" as null
 * 3. Accumulate count when previousInterval is not overlap with current interval
 * 4. Update previousInterval when (previousInterval[1] <= interval[0]) or (previousInterval[1] > interval[1])
 */

import java.util.*; // Stack

public class Solution {
    private class StartComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            return (x[0] == y[0])? (x[1] - y[1]): (x[0] - y[0]);
        }    
    }
    
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new StartComparator());
        int count = 0;
        int[] previousInterval = null;
        for(int[] interval: intervals){
            if(previousInterval != null && previousInterval[1] <= interval[0]){
                count++;
                previousInterval = interval;
            }else if(previousInterval == null || previousInterval[1] > interval[1]){
                previousInterval = interval;
            }
        }
        count += (intervals.length > 0)? 1: 0;
        return intervals.length - count;
    }

    public static void main(String[] args){
        int removeCount;
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        Solution sol = new Solution();

        System.out.print("intervals: ");
        for(int[] interval: intervals){
            System.out.print(Arrays.toString(interval) + ",");
        }
        System.out.println("");
        System.out.println("remove#: " + sol.eraseOverlapIntervals(intervals));
    }
}
