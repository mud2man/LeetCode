/* Set, Time:O(n^2), Space:O(n)
 * 1. Have pointsMap to store all the map for O(1) query
 * 2. Iterate all points pair, and check if the missing two points exist from pointsMap
 * 3. If so, update minArea
 */

import java.util.*;

public class Solution{
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> pointsMap = new HashMap<>();
        for(int[] point: points){
            pointsMap.putIfAbsent(point[0], new HashSet<>());
            pointsMap.get(point[0]).add(point[1]);
        }
        
        int minArea = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; ++i){
            for(int j = i + 1; j < points.length; ++j){
                int[] x = points[i];
                int[] y = points[j];
                int[] z = new int[]{x[0], y[1]};
                int[] w = new int[]{y[0], x[1]};
                if(x[0] != y[0] && x[1] != y[1] &&
                    pointsMap.containsKey(z[0]) && pointsMap.get(z[0]).contains(z[1]) && 
                    pointsMap.containsKey(w[0]) && pointsMap.get(w[0]).contains(w[1])){
                    int area = Math.abs(x[0] - y[0]) * Math.abs(x[1] - y[1]);
                    minArea = Math.min(area, minArea);
                }
            }
        }
        return (minArea == Integer.MAX_VALUE)? 0: minArea;
    }
   
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] points = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}};
        System.out.println("points:");
        for(int[] point: points){
            System.out.println(Arrays.toString(point));
        }
        System.out.println("minimum area:" + sol.minAreaRect(points));
    }
}
