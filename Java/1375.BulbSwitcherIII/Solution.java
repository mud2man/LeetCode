/* Time:O(n), Space:O(1).
 * 1. Have a variable "rightestSeenBulb" to remember the seen bulb with the biggest index
 * 2. Accumulate "allBlueCount" if all bulbs at left of rightestSeenBulb were turned on
 */

import java.util.*;

public class Solution {
    public int numTimesAllBlue(int[] light) {
        int allBlueCount = 0;
        int rightestSeenBulb = 0;
        for(int idx = 0; idx < light.length; ++idx){
            rightestSeenBulb = Math.max(rightestSeenBulb, light[idx]);
            allBlueCount +=(rightestSeenBulb == idx + 1)? 1: 0;
        }
        return allBlueCount;
    }
  
    public static void main(String[] args){
        int light = {2, 1, 3, 5, 4}
        Solution sol = new Solution();
        System.out.println("light:" + Arrays.toString(light));
        System.out.println("number of moments in which all turned on bulbs are blue:" + sol.numTimesAllBlue(light));
    }
}
