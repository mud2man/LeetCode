/* Disjoint Intervals: Time: add/remove=O(A + R), query=O(log(A + R)), Space:O(A + R), because we have at most (A + R) intervals
 * 1. Use treeMap to store the disjoint intervals
 * 2. In "addRange", remove the range [left, right) first, and add interval [left, right), then do merge around [left, right)
 * 3. In "queryRange", get the closest interval from left
 * 4. In "removeRange", update the leftest interval with key(floorKey), and remove the intervals falling in intervals.subMap(left, right)
 */         

import java.util.*;

public class RangeModule {
   TreeMap<Integer, Integer> intervals;
    
    public RangeModule() {
        intervals = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        removeRange(left, right);
        intervals.put(left, right);
        
        //merge
        Integer ceilingKey = intervals.ceilingKey(right);
        if(ceilingKey != null && ceilingKey == right){
            intervals.put(left, intervals.get(ceilingKey));
            intervals.remove(right);
            right = intervals.get(left);
        }
        Integer lowerKey = intervals.lowerKey(left);
        if(lowerKey != null && intervals.get(lowerKey) == left){
            intervals.remove(left);
            intervals.put(lowerKey, right);
        }  
    }
    
    public boolean queryRange(int left, int right) {
        Integer floorKey = intervals.floorKey(left);
        return (floorKey == null)? false: (intervals.get(floorKey) >= right);
    }
    
    public void removeRange(int left, int right) {
        Integer floorKey = intervals.floorKey(left);
        if(floorKey != null){
            if(intervals.get(floorKey) > right){
                intervals.put(right, intervals.get(floorKey));
                intervals.put(floorKey, left);
            }
            else if(intervals.get(floorKey) > left){
                intervals.put(floorKey, left);
            }
        }
        
        Map<Integer, Integer> overlaps = new HashMap(intervals.subMap(left, true, right, false));
        for(Map.Entry<Integer, Integer> entry: overlaps.entrySet()){
            int val = entry.getValue();
            int key = entry.getKey();
            intervals.remove(key);
            if(val >= right){
                intervals.put(right, val);
            }
        }
    }
 
    public static void main(String[] args){
        RangeModule sol = new RangeModule();
        
        int[] interval = {10, 20};
        System.out.println("addRange(" + interval[0] + ", " + interval[1] + ")");
        sol.addRange(interval[0], interval[1]); 

        interval = new int[]{14, 16};
        System.out.println("addRange(" + interval[0] + ", " + interval[1] + ")");
        sol.removeRange(interval[0], interval[1]); 
        
        interval = new int[]{10, 14};
        System.out.println("queryRange(" + interval[0] + ", " + interval[1] + "): " + sol.queryRange(interval[0], interval[1]));
        
        interval = new int[]{13, 15};
        System.out.println("queryRange(" + interval[0] + ", " + interval[1] + "): " + sol.queryRange(interval[0], interval[1]));
        
        interval = new int[]{16, 17};
        System.out.println("queryRange(" + interval[0] + ", " + interval[1] + "): " + sol.queryRange(interval[0], interval[1]));
    }
}
