/* Selection algorithm: Time:O(n), Space:O(1)
 * 1. Use selection algorithm to partition the smallest K points to left
 */

import java.util.*;

public class Solution{
    private void selectKth(int[][] points, int k){
        int start = 0;
        int end = points.length - 1;
        while(start < end){
            int[] pivot = points[end];
            int lessEqualTailIdx = start - 1;
            for(int i = start; i < end; ++i){
                int[] curr = points[i];
                if(curr[0] * curr[0] + curr[1] * curr[1] <= pivot[0] * pivot[0] + pivot[1] * pivot[1]){
                    int[] temp = points[lessEqualTailIdx + 1];
                    points[++lessEqualTailIdx] = curr;
                    points[i] = temp;
                }
            }
            int[] temp = points[lessEqualTailIdx + 1];
            points[++lessEqualTailIdx] = pivot;
            points[end] = temp;
            
            if(lessEqualTailIdx == k){
                return;
            }else if(lessEqualTailIdx < k){
                start = lessEqualTailIdx + 1;
            }else{
                end = lessEqualTailIdx - 1;
            }
        }
    }
    
    public int[][] kClosest(int[][] points, int K) {
        selectKth(points, K - 1);
        int[][] kClosestPoints = new int[K][2];
        for(int i = 0; i < K; ++i){
            kClosestPoints[i] = points[i];
        }
        return kClosestPoints;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] points = {{1, 3},{-2, 2}};
        int K = 1;
        
        System.out.println("points: ");
        for(int[] point: points){
            System.out.print(Arrays.toString(point) + ", ");
        }
        System.out.println("\nK: " + K);
        
        System.out.println("closest points: ");
        for(int[] point: sol.kClosest(points, K)){
            System.out.print(Arrays.toString(point) + ", ");
        }
        System.out.println("");
    }
}
