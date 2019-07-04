/* Sort and Greedy: Time:O(nlogn), Space:O(1)
 * 1. The same as the problem: find the maximum number of intervals that are non-overlapping
 * 2. Sort intervals by end time, and set the previous end time previousEnd as Integer.MIN_VALUE
 * 3. Select the next index of interval without overlap, update previousEnd, and accumulate maxCount
 * 4. Repeat step2 and step3, and retunr intervals.length - maxCount
 */

import java.util.*; // Stack

class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

public class Solution {
    private class IntervalComparator implements Comparator<Interval>{
        @Override
        public int compare(Interval interval0, Interval interval1) {
            return interval0.end - interval1.end;
        }
    }
    
    public int eraseOverlapIntervals(Interval[] intervals) {
        int maxCount = 0;
        Arrays.sort(intervals, new IntervalComparator());
        
        int previousEnd = Integer.MIN_VALUE;
        for(Interval interval: intervals){
            if(previousEnd <= interval.start){
                maxCount++;
                previousEnd = interval.end;
            }
        }
        
        return intervals.length - maxCount;
    }

    public static void main(String[] args){
        int removeCount;
        Solution sol;
        Interval interval1 = new Interval(1, 2);
        Interval interval2 = new Interval(2, 3);
        Interval interval3 = new Interval(3, 4);
        Interval interval4 = new Interval(1, 3);
        Interval[] intervals = {interval1, interval2, interval3, interval4};

        sol = new Solution();

        System.out.print("intervals: ");
        for(Interval i: intervals){
            System.out.print("<" + i.start + ", " + i.end + ">, ");
        }
        System.out.println("");
        
        removeCount = sol.eraseOverlapIntervals(intervals);
        System.out.println("removeCount: " + removeCount);
    }
}
