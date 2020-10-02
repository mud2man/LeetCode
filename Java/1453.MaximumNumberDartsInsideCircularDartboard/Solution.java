/* Greedy + Math: Time:O(n^3), Space:O(1)
 * 1. We can prove there exists a circle laid on two points, which is enclosed by the optimal circle. So we can find the optimal circle given any two points
 * 2. Use point-slope-formulate to find the plumb line between two points
 * 3. Applying r to and the plumb line to locate the two centers
 * 4. Go through all points and count the number of points laid on/in the circle
 * 5. Update "max" while picking all centers and feed them to getPointNumberInCircle
 */     

import java.util.*; // Stack

public class Solution {
    private double[][] getCenters(int[] pointA, int[] pointB, double r){
        double[][] centers = new double[2][2];
        double x0 = (double)pointA[0];
        double y0 = (double)pointA[1];
        double x1 = (double)pointB[0];
        double y1 = (double)pointB[1];
        if(y0 == y1){
            centers[0][0] = (x0 + x1) / 2.0;
            centers[0][1] = y0 + Math.sqrt(Math.pow(r, 2) - Math.pow((x0 - x1) / 2.0, 2));
            centers[1][0] = (x0 + x1) / 2.0;
            centers[1][1] = y0 - Math.sqrt(Math.pow(r, 2) - Math.pow((x0 - x1) / 2.0, 2));
        }else{
            
            centers[0][0] = (x0 + x1) / 2.0 + 
                Math.sqrt((Math.pow(r, 2) - Math.pow((x0 - x1) / 2.0, 2) - Math.pow((y0 - y1) / 2.0, 2)) / 
                          (1 + Math.pow((x1 - x0) / (y0 - y1), 2)));
            centers[0][1] = (x1 - x0) / (y0 - y1) * (centers[0][0] - (x0 + x1) / 2.0) + (y0 + y1) / 2.0;
            centers[1][0] = (x0 + x1) / 2.0 - 
                Math.sqrt((Math.pow(r, 2) - Math.pow((x0 - x1) / 2.0, 2) - Math.pow((y0 - y1) / 2.0, 2)) / 
                          (1 + Math.pow((x1 - x0) / (y0 - y1), 2)));
            centers[1][1] = (x1 - x0) / (y0 - y1) * (centers[1][0] - (x0 + x1) / 2.0) + (y0 + y1) / 2.0;
        }
        return centers;
    }
    
    private int getPointNumberInCircle(double[] center, double r, int[][] points){
        int count = 0;
        for(int[] point: points){
            double distance = Math.sqrt(Math.pow((double)point[0] - center[0], 2) + Math.pow((double)point[1] - center[1], 2));
            count +=(r > distance || Math.abs(distance - r) < 1e-7)? 1: 0;
        }
        return count;
    }
    
    public int numPoints(int[][] points, int r) {
        int max = 1;
        for(int i = 0; i < points.length; ++i){
            for(int j = i + 1; j < points.length; ++j){
                if(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2) <= Math.pow(2 * r, 2)){
                    double[][] centers = getCenters(points[i], points[j], (double)r);
                    max = Math.max(max, getPointNumberInCircle(centers[0], (double)r, points));
                    max = Math.max(max, getPointNumberInCircle(centers[1], (double)r, points));
                }
            }
        }
        return max;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] points = {{-2, 0}, {2, 0}, {0, 2}, {0, -2}};
        int r = 2;
        System.out.print("points:");  
        for(int[] point: points) {
            System.out.print(Arrays.toString(point) + ", ");  
        }
        System.out.println("");  
        System.out.println("number of points:" + sol.numPoints(points, r));
    }
}
