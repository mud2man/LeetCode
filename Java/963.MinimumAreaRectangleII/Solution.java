/* Math:O(n^2*logn), Space:O(n^2)
 * 1. Classify all pairs of points by center and their distance
 * 2. In the same category, any two pairs can form a retangle
 * 3. So we can get the area from the two pairs, and update minArea
 */

import java.util.*;

public class Solution{
    public double minAreaFreeRect(int[][] points) {
        Map<Double, Map<Double, Map<Double, List<int[]>>>> map = new HashMap<>();
        for(int i = 0; i < points.length - 1; ++i){
            for(int j = i + 1; j < points.length; ++j){
                double[] center = new double[]{(double)(points[i][0] + points[j][0]) / 2.0, 
                                               (double)(points[i][1] + points[j][1]) / 2.0};
                double radius = Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2);
                map.computeIfAbsent(center[0], key -> new HashMap<>())
                    .computeIfAbsent(center[1], key -> new HashMap<>())
                    .computeIfAbsent(radius, key -> new ArrayList<>())
                    .add(new int[]{points[i][0], points[i][1], points[j][0], points[j][1]});
            }
        }
        
        double minArea = 40000.0 * 40000.0 + 10.0;
        for(Map.Entry<Double, Map<Double, Map<Double, List<int[]>>>> entry0: map.entrySet()){
            for(Map.Entry<Double, Map<Double, List<int[]>>> entry1: entry0.getValue().entrySet()){
                for(Map.Entry<Double, List<int[]>> entry2: entry1.getValue().entrySet()){
                    List<int[]> pairs = entry2.getValue();
                    for(int i = 0; i < pairs.size() - 1; ++i){
                        for(int j = i + 1; j < pairs.size(); ++j){
                            int[] pair0 = pairs.get(i);
                            int[] pair1 = pairs.get(j);
                            double length = Math.pow(pair0[0] - pair1[0], 2) + Math.pow(pair0[1] - pair1[1], 2);
                            length = Math.sqrt(length);
                            double width = Math.pow(pair0[0] - pair1[2], 2) + Math.pow(pair0[1] - pair1[3], 2);
                            width = Math.sqrt(width);
                            double area = length * width;
                            minArea = Math.min(minArea, area);
                        }
                    }
                }
            }
        }
        return (minArea == 40000.0 * 40000.0 + 10.0)? 0: minArea;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] points = {{1, 2}, {2, 1}, {1, 0}, {0, 1}};
        System.out.println("points:");
        for(int[] point: points){
            System.out.print(Arrays.toString(point) + ",");
        }
        System.out.println("");
        System.out.println("minimum retangle area:" + sol.minAreaFreeRect(points));
    }
}
