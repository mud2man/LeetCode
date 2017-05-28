/* Sort + Merge: O(n*logn)
 * 1. Sort the intervals with accending order
 * 2. Merge the intervals
 *
 * ex: [1,3],[8,10],[2,6],[15,18]
 * after sort =>  [1,3],[2,6],[8,10],[15,18]
 * after merge => [1,6],[8,10],[15,18]
 */

import java.util.*;


/* Definition for binary tree */
public class Solution {
    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> merge(List<Interval> intervals) {
        ArrayList<Interval> mergedIntervals;
        
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval leftIntveral, Interval rightInterval) {
                return Integer.compare(leftIntveral.start, rightInterval.start);
            }
        });
        
        mergedIntervals = new ArrayList<Interval>();
        
        for(Interval interval: intervals){
            if(mergedIntervals.isEmpty()){
                mergedIntervals.add(interval);
            }
            else if(interval.start <= mergedIntervals.get(mergedIntervals.size() - 1).end){
                if(interval.end > mergedIntervals.get(mergedIntervals.size() - 1).end){
                    mergedIntervals.get(mergedIntervals.size() - 1).end = interval.end;
                }
            }
            else{
                mergedIntervals.add(interval);
            }
        }
        
        return (List<Interval>) mergedIntervals;
    }

    public static void main(String[] args)
    {
        List<Interval> intervals; 
        Solution sol;
        Interval intv;

        sol = new Solution();
        intervals = new ArrayList<Interval>();
        intervals.add(sol.new Interval(1, 3));
        intervals.add(sol.new Interval(8, 10));
        intervals.add(sol.new Interval(2, 6));
        intervals.add(sol.new Interval(15, 18));
        
        System.out.println("Before merge:");
        for(Interval interval: intervals){
            System.out.print("[" + interval.start + "," + interval.end + "], ");
        }
        System.out.println("");

        intervals = sol.merge(intervals);

        System.out.println("After merge:");
        for(Interval interval: intervals){
            System.out.print("[" + interval.start + "," + interval.end + "], ");
        }
        System.out.println("");
	}
}
