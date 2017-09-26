/* Greedy Schedule: O(n)
 * 1. Sort with the start position, if they have the same start position sort with the end position
 * 2. Find the maximum set which can shot the first ballon by checking currEnd >= points[j][0], and update currEnd
 * 
 * Proof: Consider the position of every arrow
 *  Assum there is a minimum set of arrows, and there are two situation 
 *  1. The first arrow is on the right side of what we found: There must be some ballons cannot be shot. It is impossible 
 *  2. The first arrow is on the left side of what we found: We can move the arrow to our position. This way, we can shot more 
 *     ballons, and the rest of the ballons can still be shot by the rest of arrows
 */          

import java.util.*; // Stack

public class Solution {
    private class startPositionComparator implements Comparator<int[]>{
        @Override
        public int compare(int[] ballon0, int[] ballon1){
            if(ballon0[0] != ballon1[0]){
                return ballon0[0] - ballon1[0];
            }
            else{
                return ballon0[1] - ballon1[1];
            }
        }
    }
    
    public int findMinArrowShots(int[][] points) {
        int count = 0;
        Arrays.sort(points, new startPositionComparator());
         
        int i = 0;
        while(i < points.length){
            int[] currBallon = points[i];
            int currEnd = currBallon[1];
            count++;
            int j = i + 1;
            while(j < points.length && currEnd >= points[j][0]){
                currEnd = Math.min(currEnd, points[j][1]);
                ++j;
            }
            i = j;
        }
                    
        return count;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[][] ballons = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        sol = new Solution();
        
        System.out.println("ballons:");
        for(int[] ballon: ballons){
            System.out.println(Arrays.toString(ballon));
        }

        System.out.println("minimum arrows:" + sol.findMinArrowShots(ballons));
    }
}
