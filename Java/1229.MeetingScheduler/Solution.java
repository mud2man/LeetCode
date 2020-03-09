/* Merge sort: Time:O(nlogn), Space:O(n). We can also use min heap, although the worst time complexity remains the same
 * 1. Sort slots1 and slots2 by starting point
 * 2. Use merge-sort like to traverse both slots as long as index1 < slots1.length && index2 < slots2.length
 * 3. Move the index to next which has righter end point
 */

import java.util.*;

public class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (x, y) -> (x[0] - y[0]));
        Arrays.sort(slots2, (x, y) -> (x[0] - y[0]));
        int index1 = 0;
        int index2 = 0;
        while(index1 < slots1.length && index2 < slots2.length){
            int[] slot1 = slots1[index1];
            int[] slot2 = slots2[index2];
            if(slot1[1] < slot2[0]){
                index1++;
            }else if(slot2[1] < slot1[0]){
                index2++;
            }else{
                int start = Math.max(slot1[0], slot2[0]);
                int end = Math.min(slot1[1], slot2[1]);
                if(end - start >= duration){
                    List<Integer> earliestTimeSlot = new ArrayList<>();
                    earliestTimeSlot.add(start);
                    earliestTimeSlot.add(start + duration);
                    return earliestTimeSlot;
                }else if(slot1[1] >= slot2[1]){
                    index2++;
                }else{
                    index1++;
                }
            }
        }
        return new ArrayList<>();
    }
  
    public static void main(String[] args){
        int[][] slots1 = {{10, 50},{60, 120}, {140, 210}};
        int[][] slots2 = {{0, 15}, {60, 70}};
        int duration = 8;
        Solution sol = new Solution();
        System.out.println("duration:" + duration);
        System.out.print("slots1:");
        for(int[] slot: slots1){
            System.out.print(Arrays.toString(slot) + ", ");   
        }
        System.out.print("\nslots2:");
        for(int[] slot: slots2){
            System.out.print(Arrays.toString(slot) + ", ");   
        }
        System.out.println("\nearliest time slot: " + sol.minAvailableDuration(slots1, slots2, duration));
    }
}
