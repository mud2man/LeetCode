/* BucketSort: Time:O(n), Space:O(1)
 * 1. Have onMap and offMap to record people# getting on and getting off on specific position
 * 2. Caculate the people# on every locatiton
 */

import java.util.*;

public class Solution{
    public boolean carPooling(int[][] trips, int capacity) {
        int[] onMap = new int[1001];
        int[] offMap = new int[1001];
        for(int[] trip: trips){
            int onPosition = trip[1];
            int offPosition = trip[2];
            int count = trip[0];
            onMap[onPosition] += count;
            offMap[offPosition] += count;
        }
        
        int count = 0;
        for(int i = 0; i <= 1000; ++i){
            count -= offMap[i];
            count += onMap[i];
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
