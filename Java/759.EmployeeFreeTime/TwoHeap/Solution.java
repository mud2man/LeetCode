/* MinHeap + MaxHeap: Time:O(n*logn), Space:O(n)
 * 1. Have a minHeap to store tuples, and sorted by interval.end
 * 2. Have a maxHeap to store the starts
 * 3. If maxHeap.peek() < minHeap.peek().interval.end, there is a common free time among the intervals
 * 4. After checking, take the successor of the top of minHeap and remove the top
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
    private class Tuple{
        int y;
        int x;
        Interval interval;
        Tuple(int y, int x, Interval iv){this.y = y; this.x = x; interval = iv;}
    }
    
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<List<Interval>> freeTimes = new ArrayList<>();
        int MAX = 1_000_000_000;
        for(int i = 0; i < schedule.size(); ++i){
            List<Interval> intervals = schedule.get(i);
            freeTimes.add(new ArrayList<>());
            Interval prevInterval = new Interval(-1, -1);
            for(int j = 0; j < intervals.size(); ++j){
                freeTimes.get(i).add(new Interval(prevInterval.end, intervals.get(j).start));
                prevInterval = intervals.get(j);
            }
            freeTimes.get(i).add(new Interval(prevInterval.end, MAX));
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> (y - x));
        PriorityQueue<Tuple> minHeap = new PriorityQueue<>((x, y) -> (x.interval.end - y.interval.end));
        for(int i = 0; i < freeTimes.size(); ++i){
            if(freeTimes.get(i).isEmpty()){
                return new ArrayList<>();
            }
            maxHeap.add(freeTimes.get(i).get(0).start);
            minHeap.add(new Tuple(i, 0, freeTimes.get(i).get(0)));
        }
        
        List<Interval> commonFreeTimes = new ArrayList<>();
        while(minHeap.size() == schedule.size()){
            if(maxHeap.peek() < minHeap.peek().interval.end && maxHeap.peek() > -1 && minHeap.peek().interval.end < MAX){
                commonFreeTimes.add(new Interval(maxHeap.peek(), minHeap.peek().interval.end));
            }
            Tuple tuple = minHeap.poll();
            maxHeap.remove(tuple.interval.start);
            if(tuple.x + 1 < freeTimes.get(tuple.y).size()){
                minHeap.add(new Tuple(tuple.y, tuple.x + 1, freeTimes.get(tuple.y).get(tuple.x + 1)));
                maxHeap.add(freeTimes.get(tuple.y).get(tuple.x + 1).start);
            }
        }
        return commonFreeTimes;
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
