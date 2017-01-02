/* Use Greedy algorithm + min-heap: O(nlgn)
 * 1. Sort intervals by their start-time
 * 2. Iterate intervals and check if the start-time is erilier than the earilest end-time of rooms
 * 3. If yes, add a new room with its end-time to the min-heap
 * 4. If no, update the end-time of the eariliest finished room
 * Proof:
 *   room#1 ----|   |---(conference#i)--|----|... (optimal solution#1)
 *   room#2 ------|  |---(conference#j)--|----|... (optimal solution#2)
 *   
 *   If conference#i sequence in room#2 is optimal, then conference#i sequence in room#1 is optimal, too.
 *   Therefore, we can aywalys chooce the earilest finished room 
 */

import java.util.*; // Stack

/* Definition for interval */
class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
 	Interval(int s, int e) { start = s; end = e; }
}

class IntervalComparator implements Comparator<Interval>{
 
	@Override
	public int compare(Interval o1, Interval o2) {
		return o1.start - o2.start;
	}
}

public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        int i;
        int earilestEnd;
        PriorityQueue<Integer> minHeap;
        
        if(intervals.length == 0){
            return 0;
        }
        
        minHeap = new PriorityQueue<Integer>();
        Arrays.sort(intervals, new IntervalComparator());
        minHeap.add(intervals[0].end);
        
        for(i = 1; i < intervals.length; ++i){
            earilestEnd = minHeap.peek();
            if(earilestEnd <= intervals[i].start){
                minHeap.poll();
            }
            minHeap.add(intervals[i].end);
        }
        return minHeap.size();
    }

    public static void main(String[] args){
		Solution sol;
		Interval[] intervals;
		
		intervals = new Interval[3];
		sol = new Solution();
		
		intervals[0] = new Interval(0, 30);
		intervals[1] = new Interval(5, 10);
		intervals[2] = new Interval(15, 20);
		
		System.out.println("intervals[]: ");	
		for(Interval i: intervals){
			System.out.print("[" + i.start + ", " + i.end + "]\n");	
		}
		
		System.out.println("#rooms: " + sol.minMeetingRooms(intervals));	
	}
}
