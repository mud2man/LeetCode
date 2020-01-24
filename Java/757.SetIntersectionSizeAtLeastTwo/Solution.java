/* Greedy: Time:O(nlogn), Space:O(1).
 * 1. Have a pair of range of left and right points 
 * 2. Sort intervals by start points
 * 3. We update leftRange and rightRange by 3 cases:1) leftRange intersects interval, 2)rightRange intersects interval, 3) no intersection
 * 4. Since each necessary point should maximize its coverage and every intervals have 2 points, we start from leftest interval and maximize their coverage
 * 5. Asume S is the minimum set, we can pick the leftest node which covers most intervals to have better of equal solution than S
 */

import java.util.*;


public class Solution{
    private boolean isIntersect(int[] x, int[] y){
        return !(x[1] < y[0] || y[1] < x[0]);
    }
    
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> (x[0] - y[0]));
        int[] leftRange = new int[]{intervals[0][0], intervals[0][1] - 1};
        int[] rightRange = new int[]{intervals[0][0] + 1, intervals[0][1]};
        int count = 2;
        for(int[] interval: intervals){
            if(isIntersect(leftRange, interval)){
                leftRange[0] = Math.max(leftRange[0], interval[0]);
                leftRange[1] = Math.min(leftRange[1], interval[1] - 1);
                rightRange[0] = leftRange[0] + 1;
                rightRange[1] = Math.min(rightRange[1], interval[1]);
            }else if(isIntersect(interval, rightRange)){
                leftRange[0] = Math.max(rightRange[0], interval[0]);
                leftRange[1] = Math.min(rightRange[1], interval[1] - 1);
                rightRange[0] = leftRange[0] + 1;
                rightRange[1] = interval[1];
                count++;
            }else{
                leftRange = new int[]{interval[0], interval[1] - 1};
                rightRange = new int[]{interval[0] + 1, interval[1]};
                count+=2;
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] intervals = {{1, 3}, {1, 4}, {2, 5}, {3, 5}};
        System.out.println("intervals:");
        for(int[] interval: intervals){
            System.out.println(Arrays.toString(interval));
        }
        System.out.println("minimum size of intersection:" + sol.intersectionSizeTwo(intervals));
    }
}
