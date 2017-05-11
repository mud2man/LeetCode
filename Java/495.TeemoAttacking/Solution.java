/* Merger sort: O(n)
 * 1. Scan from the leftest element of timeSeries
 * 2. Check if the previous time overlapped with the current time
 * 3. If overlapping, accumulate time span with (currTime + duration - endTime)
 * 4. If not, accumulate time span with duration
 */

import java.util.*; // Stack

public class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int idx, size, endTime, timeSpan, currTime;
        
        size = timeSeries.length;
        
        if(size == 0){
            return 0;
        }
        
        currTime = timeSeries[0];
        timeSpan = duration;
        endTime = timeSeries[0] + duration;
        for(idx = 1; idx < size; idx++){
            currTime = timeSeries[idx];
            if(endTime >= currTime){
                timeSpan = timeSpan + (currTime + duration - endTime);
            }
            else{
                timeSpan = timeSpan + duration;
            }
            endTime = currTime + duration;
        }
        
        return timeSpan;
    }

    public static void main(String[] args){
        Solution sol;
        int[] timeSeries = {1, 2};
        int duration = 2;

        sol = new Solution();
        
        System.out.println("timeSeries: " + Arrays.toString(timeSeries));
        System.out.println("duration: " + duration);
        System.out.println("poisoned duration: " + sol.findPoisonedDuration(timeSeries, duration));
    }
}
