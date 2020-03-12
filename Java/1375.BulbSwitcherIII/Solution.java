/* Time:O(n), Space:O(n). However, leetcode has Space-O(1) solution
 * 1. Have an array "bulbs" to record the status of each bulb with the status 0:off, 1:yellow, 2:blue 
 * 2. In each round, we turn the bulb to blue if isLeftBlue is true, and keep change status as long as bulbs[idx] == 1
 * 3. We accumulate allBlueCount as yellowCount == 0
 */

import java.util.*;

public class Solution {
    public int numTimesAllBlue(int[] light) {
        int[] bulbs = new int[light.length]; //0:off, 1:yellow, 2:blue;
        int yellowCount = 0;
        int allBlueCount = 0;
        for(int onIndex: light){
            onIndex--;
            boolean isLeftBlue =(onIndex == 0)? true: (bulbs[onIndex - 1] == 2);
            bulbs[onIndex] = 1;
            yellowCount++;
            int idx = onIndex;
            while(isLeftBlue && idx < bulbs.length && bulbs[idx] == 1){
                yellowCount--;
                bulbs[idx] = 2;
                idx++;
                allBlueCount +=(yellowCount == 0)? 1: 0;
            }
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
