/* Binary Search: Time:O(n), Space:O(1), Don't have to use binary search
 * 1. Find the first interval overlapped with "newInterval" or the insert position
 * 2. Append all the intervals before insert position "index"
 * 3. Update newInterval as long as there is overlapping 
 * 4. Append all the intervals which is no overlap with newInterval
 */

import java.util.*;
public class Solution{
    static private class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    private boolean isOverlap(Interval x, Interval y){
        return !(x.end < y.start || y.end < x.start);
    }
    
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int lb = 0;
        int hb = intervals.size() - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            Interval interval = intervals.get(mid);
            if(interval.end < newInterval.start){
                lb = mid + 1;
            }
            else{
                hb = mid - 1;
            }
        }
        
        int index = lb;
        List<Interval> merged = new ArrayList<>();
        for(int i = 0; i < index; ++i){
            merged.add(intervals.get(i));
        }
        
        while(index >= 0 && index < intervals.size() && isOverlap(intervals.get(index), newInterval)){
            newInterval.start = Math.min(newInterval.start, intervals.get(index).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(index).end);
            index++;
        }
        merged.add(newInterval);
        
        for(int i = index; i < intervals.size(); ++i){
            merged.add(intervals.get(i));   
        }
        return merged;
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
