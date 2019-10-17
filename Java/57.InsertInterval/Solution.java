/* Time:O(n), Space:O(1), Don't have to use binary search
 * 1. Add the non-overlap intervals on the left side of newInterval
 * 3. Update newInterval as long as there is overlapping 
 * 4. Append all the intervals which is no overlap with newInterval
 */

import java.util.*;
public class Solution{
    private boolean isOverlap(int[] x, int[] y){
        return !(x[1] < y[0] || x[0] > y[1]);
    }
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        int i = 0;
        for(i = 0; i < intervals.length; ++i){
            if(intervals[i][1] < newInterval[0]){
                merged.add(intervals[i]);
            }else{
                break;
            }
        }
        
        while(i < intervals.length && isOverlap(intervals[i], newInterval)){
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            ++i;
        }
        merged.add(newInterval);
        
        for(int j = i; j < intervals.length; ++j){
            merged.add(intervals[j]);
        }
        
        int[][] ret = new int[merged.size()][2];
        for(int j = 0; j < ret.length; ++j){
            ret[j] = merged.get(j);
        }
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] intervals = {{1,3}, {6, 9}};
        int[] newInterval = {2, 5};

        System.out.println("new interval:" + " (" + newInterval[0] + ", " + newInterval[1] + ")");
        System.out.println("before merged intervals: ");
        for(int[] interval: intervals){
            System.out.println("(" + interval[0] + ", " + interval[1] + ")");
        }

        System.out.println("after merged intervals: ");
        for(int[] interval: sol.insert(intervals, newInterval)){
            System.out.println("(" + interval[0] + ", " + interval[1] + ")");
        }
    }
}
