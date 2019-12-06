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

public class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> {return x[0] - y[0];});
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((x, y) ->{return x[1] - y[1];});
        int roomsNumber = 0;
        for(int[] interval: intervals){
            while(!minHeap.isEmpty() && minHeap.peek()[1] <= interval[0]){
                minHeap.poll();
            }
            minHeap.add(interval);
            roomsNumber = Math.max(roomsNumber, minHeap.size());
        }
        return roomsNumber;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
		
        System.out.println("intervals[]: ");	
        for(int[] i: intervals){
        	System.out.print(Arrays.toString(i) + ", ");	
        }
        System.out.println("\nrooms#: " + sol.minMeetingRooms(intervals));	
	}
}
