/* Sort + Merge: O(n*logn)
 * 1. Sort the intervals with accending order
 * 2. Merge the intervals
 *
 * ex: [1,3],[8,10],[2,6],[15,18]
 * after sort =>  [1,3],[2,6],[8,10],[15,18]
 * after merge => [1,6],[8,10],[15,18]
 */

import java.util.*;


/* Definition for binary tree */
public class Solution {
    public int[][] merge(int[][] intervals) {
        Deque<int[]> mergedIntervals = new LinkedList<>();
        if(intervals.length == 0){
            return intervals;
        }
        
        Arrays.sort(intervals, (x, y) -> (x[0] - y[0]));
        mergedIntervals.add(intervals[0]);
        for(int[] interval: intervals){
            if(mergedIntervals.peekLast()[1] >= interval[0]){
                mergedIntervals.peekLast()[1] = Math.max(mergedIntervals.peekLast()[1], interval[1]);
            }else{
                mergedIntervals.add(interval);
            }
        }
        int[][] ret = new int[mergedIntervals.size()][2];
        int size = mergedIntervals.size();
        for(int i = 0; i < size; ++i){
            ret[i] = mergedIntervals.pollFirst();
        }
        return ret;
    }
  
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
        System.out.println("Before merge:");
        for(int[] interval: intervals){
            System.out.print(Arrays.toString(interval) + ", ");
        }
        System.out.println("");

        intervals = sol.merge(intervals);

        System.out.println("After merge:");
        for(int[] interval: intervals){
            System.out.print(Arrays.toString(interval) + ", ");
        }
        System.out.println("");
	}
}
