/* Selection algorithm: Time:O(n), Space:O(1)
 * 1. Use selection algorithm to partition the smallest K points to left
 */

import java.util.*;

public class Solution{
    public int[][] kClosest(int[][] points, int K) {
        int lb = 0;
        int hb = points.length - 1;
        while(lb <= hb){
            int[] pivot = points[hb];
            int lessEqualIdx = lb - 1;
            for(int i = lb; i < hb; ++i){
                int[] point = points[i];
                if(point[0]*point[0] + point[1]*point[1] <= pivot[0]*pivot[0] + pivot[1]*pivot[1]){
                    int[] temp = points[lessEqualIdx + 1];
                    points[++lessEqualIdx] = point;
                    points[i] = temp;
                }
            }
            int[] temp = points[lessEqualIdx + 1];
            points[++lessEqualIdx] = pivot;
            points[hb] = temp;
            
            if(lessEqualIdx == K - 1){
                break;
            }else if(lessEqualIdx < K - 1){
                lb = lessEqualIdx + 1;
            }else{
                hb = lessEqualIdx - 1;
            }
        }
        
        int[][] kClosest = new int[K][2];
        for(int i = 0; i < K; ++i){
            kClosest[i][0] = points[i][0];
            kClosest[i][1] = points[i][1];
        }
        return kClosest;
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
