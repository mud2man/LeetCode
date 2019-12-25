/* Sort + Heap: Time:O(n*logn), Space:O(n)
 * 1. Have a list to store all the free time interval, and sort with start
 * 2. Have a minimum heap sorted with end. 
 * 3. The heap contains overlapped intervals
 * 4. Because any tow intervals for the same employee never overlap, the intervals in minHeap must belong to different employee
 * 5. Poll the top if it no overlap with the current one
 * 6. New a interval if the current interval overlap with the peek interval in minHeap
 *
 * ex: [[[1,2], [5,6]],[[1,3]],[[4,10]]]
 * freeTimes: [-inf, 1], [-inf, 1], [-inf, 4], [2, 5] ,[3, inf], [6, inf], [10, inf]
 * 
 * time 0: minHeap[[-inf, 1]], commonTimes:[]
 * time 1: minHeap[[-inf, 1], [-inf, 1]], commonTimes:[]
 * time 2: minHeap[[-inf, 1], [-inf, 4]], commonTimes:[]
 * time 3: minHeap[[-inf, 4], [2, 5], ], commonTimes:[]
 * time 4: minHeap[[-inf, 4], [2, 5], [3, inf]], commonTimes:[[3, 4]]
 * time 5: minHeap[[6, inf]], commonTimes:[[3, 4]]
 * time 6: minHeap[[10, inf]], commonTimes:[[3, 4]]
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

    private class StartSort implements Comparator<Interval>{
        public int compare(Interval x, Interval y){
            return (x.start < y.start)? -1: 1;
        }
    }
    
    private class EndSort implements Comparator<Interval>{
        public int compare(Interval x, Interval y){
            return (x.end < y.end)? -1: 1;
        }
    }
    
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> freeTimes = new ArrayList<Interval>();
        int employeeCount = schedule.size();
        
        for(List<Interval> employee: schedule){
            int start = Integer.MIN_VALUE;
            for(Interval time: employee){
                int end = time.start;
                freeTimes.add(new Interval(start, end));
                start = time.end;
            }
            freeTimes.add(new Interval(start, Integer.MAX_VALUE));
        }
        
        Collections.sort(freeTimes, new StartSort());
        
        PriorityQueue<Interval> minHeap = new PriorityQueue<Interval>(new EndSort());
        List<Interval> commonTimes = new ArrayList<Interval>();
        for(Interval freeTime: freeTimes){
            if(minHeap.isEmpty()){
                minHeap.add(freeTime);
            }
            else{
                if(minHeap.size() == (employeeCount - 1) && minHeap.peek().end > freeTime.start){
                    int start = freeTime.start;
                    int end = Math.min(minHeap.peek().end, freeTime.end);
                    if(start > Integer.MIN_VALUE && end < Integer.MAX_VALUE){
                        commonTimes.add(new Interval(start, end));
                    }
                    minHeap.add(freeTime);
                    minHeap.poll();
                }else{
                    while(!minHeap.isEmpty() && minHeap.peek().end < freeTime.start){
                        minHeap.poll();
                    }
                    minHeap.add(freeTime);
                    if(minHeap.size() == employeeCount){
                        minHeap.poll();
                    }
                }
            }
        }
        return commonTimes;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        List<List<Interval>> schedule  = new ArrayList<List<Interval>>();
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(sol.new Interval(1, 2));
        intervals.add(sol.new Interval(5, 6));
        schedule.add(intervals);
        intervals = new ArrayList<Interval>();
        intervals.add(sol.new Interval(1, 3));
        schedule.add(intervals);
        intervals = new ArrayList<Interval>();
        intervals.add(sol.new Interval(4, 10));
        schedule.add(intervals);
        
        System.out.print("schedule: ");
        for(List<Interval> i: schedule){
            for(Interval interval: i){
                System.out.print("[" + interval.start + "," + interval.end + "], ");
            }
        }
        System.out.println("");

        System.out.print("common freee time: ");
        for(Interval interval: sol.employeeFreeTime(schedule)){
            System.out.print("[" + interval.start + "," + interval.end + "], ");
        }
        System.out.println("");
	}
}
