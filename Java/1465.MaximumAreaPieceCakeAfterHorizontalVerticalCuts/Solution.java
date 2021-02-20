/* Sort: Time:O(n*logn), Space:O(1)
 * 1. Sort cuts and get the max horizontal/vertical  side from every consecutive cuts for horizontalCuts/verticalCuts
 * 2. Mutiply horizontal and vertical sides and return 
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    private int getMaxSide(int length, int[] cuts){
        Arrays.sort(cuts);
        int maxSide = 0;
        for(int i = 0; i < cuts.length; i++){
            int prev =(i == 0)? 0: cuts[i - 1]; 
            maxSide = Math.max(maxSide, cuts[i] - prev);
        }
        maxSide = Math.max(maxSide, length - cuts[cuts.length - 1]);
        return maxSide;
    }
    
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int maxHorizontalSide = getMaxSide(h, horizontalCuts);
        int maxVerticalSide = getMaxSide(w, verticalCuts);
        long base = 1_000_000_007;
        return (int)(((long)maxHorizontalSide * (long)maxVerticalSide) % base);
    }
 
    public static void main(String[] args){
        int h = 5;
        int w = 4;
        int[] horizontalCuts = {1, 2, 4};
        int[] verticalCuts = {1, 3};
        Solution sol = new Solution();
        System.out.println("h:"  + h + ", w:" + w + ", horizontal:" + Arrays.toString(horizontalCuts) + ", vertical:" + Arrays.toString(verticalCuts));
        System.out.println("max area:" + sol.maxArea(h, w, horizontalCuts, verticalCuts));
    }
}
