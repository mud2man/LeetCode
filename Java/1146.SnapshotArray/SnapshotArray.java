/* Math: Time:O(1), Space:O(1).
 * 1. We can determine min, max, mean and mode in the first round
 * 2. We do branch to get median given if the total number is even or odd 
 */

import java.util.*;

public class SnapshotArray {
    int snapId;
    Map<Integer, TreeMap<Integer, Integer>> index2SnapdValues;

    public SnapshotArray(int length) {
        snapId = 0;
        index2SnapdValues = new HashMap<>();
        for(int i = 0; i < length; ++i){
            index2SnapdValues.computeIfAbsent(i, key -> new TreeMap<>()).put(0, 0);
        }
    }
    
    public void set(int index, int val) {
        index2SnapdValues.get(index).put(snapId, val);
    }
    
    public int snap() {
        snapId++;
        return (snapId - 1);
    }
    
    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> snapdValues = index2SnapdValues.get(index);
        int floorKey = snapdValues.floorKey(snap_id);
        return snapdValues.get(floorKey);
    }
  
    public static void main(String[] args){
        int length = 3;
        SnapshotArray sol = new SnapshotArray(length);
        System.out.println("length: " + length);
        System.out.println("sol.set(0, 5)");
        sol.set(0, 5);
        sol.snap();
        System.out.println("sol.set(0, 6)");
        sol.set(0, 6);
        System.out.println("sol.get(0, 0): " + sol.get(0, 0));
    }
}
