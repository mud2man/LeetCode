/* Hash: Time:O(n^2) Space:O(n^2)
 * 1. First, have a map "point2Count" to reduce the duplicate nodes, and assign count as the number of node
 * 1. Fix the first point by selecting from points[0] to points[length - 1]
 * 2. Since one point is fixed, we can determine the line by slope
 * 3. Slope can be determined by (right.x - left.x) and (right.y - left.y)
 * 5. Then update localMax, then update golbalMax
 */

import java.util.*;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

public class Solution{
    private int getGcd(int x, int y){
        if(x == 0 || y == 0){
            return 1;
        }
        int small = Math.min(x, y);
        int big = Math.max(x, y);
        return (big % small == 0)? small: getGcd(big % small, small);
    }
    
    private String getSlope(String x, String y){
        String[] pointX = x.split("#");
        String[] pointY = y.split("#");
        int deltaX = Integer.valueOf(pointX[0]) - Integer.valueOf(pointY[0]);
        int deltaY = Integer.valueOf(pointX[1]) - Integer.valueOf(pointY[1]);
        if(deltaX == 0){
            return "inf";
        }
        else if(deltaY == 0){
            return "0";
        }
        else{
            int gcd = getGcd(Math.abs(deltaX), Math.abs(deltaY));
            String slope = Integer.toString(deltaY / gcd) + "." + Integer.toString(deltaX / gcd);
            return slope;
        }
    }
    
    public int maxPoints(Point[] points) {
        Map<String, Integer> point2Count = new HashMap<>();
        for(Point p: points){
            String code = Integer.toString(p.x) + "#" + Integer.toString(p.y);
            point2Count.putIfAbsent(code, 0);
            point2Count.put(code, point2Count.get(code) + 1);
        }
        
        int globlaMax = 0;
        for(Map.Entry<String, Integer> source: point2Count.entrySet()){
            Map<String, Integer> slope2Count = new HashMap<>();
            int localMax = 0;
            for(Map.Entry<String, Integer> point: point2Count.entrySet()){
                if(source.getKey().equals(point.getKey())){
                    continue;
                }
                String slope = getSlope(source.getKey(), point.getKey());
                slope2Count.putIfAbsent(slope, 0);
                slope2Count.put(slope, slope2Count.get(slope) + point.getValue());
                localMax = Math.max(localMax, slope2Count.get(slope));
            }
            globlaMax = Math.max(globlaMax, localMax + source.getValue());
        }
        return (points.length > 0)? globlaMax: 0;
    }
 
    public static void main(String[] args){
        Point[] points = new Point[4];
        Solution sol = new Solution();
        points[0] = new Point(0, 0); 
        points[1] = new Point(1, 2); 
        points[2] = new Point(2, 4); 
        points[3] = new Point(1, 5); 

        System.out.println("points");
        for(Point point: points){
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
        System.out.println("maximum number of points on a line: " + sol.maxPoints(points));
    }
}
