/* Heap: Time:O(nlogn), Space:O(n)
 * 1. Sort trip with start point, and do traverse
 * 2. Have a heap sorted by end point
 * 3. Pop the top of heap if the current position >= top[2], and confirm if count > capacity
 */

import java.util.*;

public class Solution{
    private class startComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            return x[1] - y[1];
        }
    }
    
    private class endComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            return x[2] - y[2];
        }
    }
    
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, new startComparator());
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new endComparator());
        int count = 0;
        for(int[] trip: trips){
            int position = trip[1];
            while(!minHeap.isEmpty() && minHeap.peek()[2] <= position){
                int[] top = minHeap.poll();
                count -= top[0];
            }
            minHeap.add(trip);
            count += trip[0];
            if(count > capacity){
                return false;
            }
        }
        return true;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] trips = {{2, 1, 5}, {3, 3, 7}};
        int capacity = 4;
        
        System.out.println("capacity: " + capacity);
        System.out.print("trips: ");
        for(int[] trip: trips){
            System.out.print(Arrays.toString(trip) + ", ");
        }   
        System.out.println("\npick up and drop off all passengers: " + sol.carPooling(trips, capacity));
    }
}
