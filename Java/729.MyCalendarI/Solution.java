/* Interval tree: Time:O(logn), Space:O(n), We can use TreeMap instead. It's simpler
 * 1. Construct the interval tree, and return true if insert successfully
 */

import java.util.*;

public class Solution{
    TreeMap<Integer, Integer> calendar;
    public Solution() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer floorKey = calendar.floorKey(start);
        if (floorKey != null && calendar.get(floorKey) > start) return false;
        Integer ceilingKey = calendar.ceilingKey(start);
        if (ceilingKey != null && ceilingKey < end) return false;

        calendar.put(start, end);
        return true;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] intervals = {{10, 20}, {15, 25}, {20, 30}};
        
        for(int[] interval: intervals){
            System.out.println(Arrays.toString(interval));
        	System.out.println("insert success: " + sol.book(interval[0], interval[1]));
        }
    }
}
