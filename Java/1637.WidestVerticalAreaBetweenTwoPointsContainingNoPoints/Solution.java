/* Sort: Time:O(n*lognn), Space:O(n)
 * 1. Sort by x-coordinates, and update max by comparing each x-interval
 */     

import java.util.*; // Stack

public class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        List<Integer> xs = new ArrayList<>();
        for(int[] point: points){
            xs.add(point[0]);
        }
        Collections.sort(xs);
        
        int max = 0;
        for(int i = 0; i < xs.size() - 1; i++){
            max = Math.max(max, xs.get(i + 1) - xs.get(i));
        }
        return max;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] points = {{8, 7}, {9, 9}, {7, 4}, {9, 7}};
        System.out.println("points:");
        for(int[] point: points){
            System.out.println(Arrays.toString(point));
        }
        System.out.println("max vertical area:" + sol.maxWidthOfVerticalArea(points));
    }
}
