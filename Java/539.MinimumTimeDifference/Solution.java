/* Bucket sort: Time:O(n), Space:O(1)
 * 1. Have a bucket array "minuteBucket"
 * 2. Traverse the adjacent neighbors, and update the minimum difference
 * 3. Need to check the start and end time, then update minimum difference again
 */         

import java.util.*;

public class Solution {
    private int getMinute(String timeString){
        String[] timeArray = timeString.split(":");
        return Integer.parseInt(timeArray[0]) * 60 + Integer.parseInt(timeArray[1]);
    }
    
    public int findMinDifference(List<String> timePoints) {
        boolean[] minuteBucket = new boolean[1440];
        for(String timePoint: timePoints){
            int minute = getMinute(timePoint);
            if(minuteBucket[minute] == true){
                return 0;
            }
            minuteBucket[minute] = true;
        }
        
        int start = 0;
        while(minuteBucket[start] != true){start++;}
        int ptr0 = start;
        int ptr1 = start + 1;
        int minDiff = Integer.MAX_VALUE;
        while(ptr1 < 1440){
            while(ptr1 < 1440 && minuteBucket[ptr1] != true){ptr1++;}
            if(ptr1 == 1440){
                break;
            }
            minDiff = Math.min(minDiff, ptr1 - ptr0);
            ptr0 = ptr1;
            ptr1 = ptr1 + 1;
        }
        int end = ptr0;
        minDiff = Math.min(minDiff, 1440 - (end - start));
        return minDiff;
    }

    public static void main(String[] args){
        Solution sol;
        List<String> timePoints = new ArrayList<String>();
        timePoints.add("23:59");
        timePoints.add("00:00");
        sol = new Solution();
        System.out.println("timePoints:" + timePoints);
        System.out.println("minimum difference:" + sol.findMinDifference(timePoints));
    }
}
