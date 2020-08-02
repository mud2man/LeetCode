/* Math: Time:O(1), Space:O(1)
 * 1. Get the angular position of the two hands, and calculate the difference
 */     

import java.util.*; // Stack

public class Solution {
    public double angleClock(int hour, int minutes) {
        double degreeInAnHourPerMinute = 0.5;
        double degreeInAnHour = 30.0;
        double degreeInAMinute = 6.0;
        double hourDegree = degreeInAnHour * (double)(hour % 12) + degreeInAnHourPerMinute * (double)minutes;
        double minuteDegree = minutes * degreeInAMinute;
        double angle = Math.abs(hourDegree - minuteDegree);
        return Math.min(360.0 - angle, angle);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int hour = 12;
        int minutes = 30;   
        System.out.println("hour:" + hour);
        System.out.println("minutes:" + minutes);
        System.out.println("angle:" + sol.angleClock(hour, minutes));
    }
}
