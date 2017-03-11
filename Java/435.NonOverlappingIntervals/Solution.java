/* Sort and Greedy: O(nlogn)
 * 1. Sort intervals by end time
 * 2. Select the next index of interval without overlap
 * 3. Or, if the next interval with overlaping, but the start time is latter than the current one
 * 4. Replace the current idx with this one, and call "greedySelectNext" to select the next valid interval
 */

import java.util.*; // Stack

class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

public class Solution {
    private class EndPointComparator implements Comparator<Interval>{
        @Override
        public int compare(Interval o1, Interval o2) {
            return o2.end - o1.end;
        }
    }
    
    public int greedySelectNext(Interval[] intervals, int idx){
        int nextIdx;

        nextIdx = idx + 1;
        while(nextIdx < intervals.length && intervals[nextIdx].end > intervals[idx].start){
            if(intervals[nextIdx].start > intervals[idx].start){
                nextIdx = greedySelectNext(intervals, nextIdx);
                break;
            }
            nextIdx++;
        }
        return nextIdx;
    }
    
    public int eraseOverlapIntervals(Interval[] intervals) {
        int count, size, idx;
        
        idx = 0;
        count = 1;
        size = intervals.length;
        Arrays.sort(intervals, new EndPointComparator());
        
        if(size == 0){
            return 0;
        }

        while(idx < intervals.length ){
            idx = greedySelectNext(intervals, idx);
            count = (idx < intervals.length )? count + 1: count;
        }
        return (size - count); 
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
