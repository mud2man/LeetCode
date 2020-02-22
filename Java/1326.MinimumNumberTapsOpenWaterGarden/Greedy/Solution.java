/* Greedy: Time:O(nlogn), Space:O(n)
 * 1. Sorting intervals by left points and then by right points
 * 2. Have variable "left" to remember the covered leftest point of garden
 * 3. Keep remember the last interval in prevIntervals[0], and update prevIntervals[1] as long as the current interval connected with "left"
 * 4. Only increase count when the current interval disconnect "left"
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public int minTaps(int n, int[] ranges) {
        List<int[]> intervals = new ArrayList<>();
        for(int i = 0; i < ranges.length; ++i){
            if(ranges[i] > 0){
               intervals.add(new int[]{Math.max(0, i - ranges[i]), i + ranges[i]});  
            }
        }
        Collections.sort(intervals, (x, y) -> ((x[0] != y[0])? (x[0] - y[0]): (x[1] - y[1])));
        int count = 0;
        int left = 0;
        int[][] prevIntervals = {{-1, -1}, {-1, -1}};
        for(int i = 0; i < intervals.size(); ++i){
            int[] interval = intervals.get(i);
            if(left >= n){
                return count;
            }else if(left >= interval[0]){
                //update the rightest interval connected with left
                if(interval[1] > prevIntervals[1][1]){
                    prevIntervals[1] = interval;
                }
            }else{
                if(left >= prevIntervals[1][0]){
                    left = prevIntervals[1][1];
                    prevIntervals[0] = prevIntervals[1];
                    prevIntervals[1] = new int[]{-1, -1};
                    count++;
                    i--;
                }else{
                    return -1;
                }
            }
        }
        return (prevIntervals[1][1] >= n)? count + 1: -1;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 5;
        int[] ranges = {3, 4, 1, 1, 0, 0};
        System.out.println("ranges: " + Arrays.toString(ranges));
        System.out.println("minimun taps#: " + sol.minTaps(n, ranges));
    }
}
