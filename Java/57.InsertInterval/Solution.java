/* Binary Search: Time:O(n), Space:O(1)
 * 1. Find the first interval overlapped with "newInterval"
 * 2. Delete the overlapped intervals, and add "newInterval"
 */

import java.util.*;
public class Solution{
    static private class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    private boolean overlap(Interval interval1, Interval interval2){
        Interval left = (interval1.start < interval2.start)? interval1: interval2;
        Interval right = (interval1.start < interval2.start)? interval2: interval1;
        return !(left.end < right.start);
    }
    
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        int i = 0;
        int j = intervals.size() - 1;
        int size = intervals.size();
        
        while(i <= j){
            int mid = (i + j) / 2;
            if(intervals.get(mid).end < newStart){
                i = mid + 1;
            }
            else{
                j = mid - 1;
            }
        }
        
        int startIndex = i;
        newInterval.start = (startIndex < size)? Math.min(intervals.get(startIndex).start, newStart): newInterval.start;
        for(i = startIndex; i < size; ++i){
            if(overlap(newInterval, intervals.get(startIndex))){
                newInterval.end = Math.max(intervals.get(startIndex).end, newEnd);
                intervals.remove(startIndex);
            }
            else{
                break;
            }
        }
        intervals.add(startIndex, newInterval);
        
        return intervals;
    }

    public static void main(String[] args){
        Solution sol;
        List<Interval> intervals = new ArrayList<Interval>();
        Interval newInterval = new Interval(2, 5);
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(6, 9));

        sol = new Solution();
        System.out.println("new interval:" + " (" + newInterval.start + ", " + newInterval.end + ")");
        System.out.println("before merged intervals: ");
        for(Interval interval: intervals){
            System.out.println("(" + interval.start + ", " + interval.end + ")");
        }

        System.out.println("after merged intervals: ");
        for(Interval interval: sol.insert(intervals, newInterval)){
            System.out.println("(" + interval.start + ", " + interval.end + ")");
        }
    }
}
