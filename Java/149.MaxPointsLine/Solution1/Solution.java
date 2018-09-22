/* Hash: Time:O(n^2) Space:O(n^2)
 * 1. Fix the first point by selecting from points[0] to points[length - 1]
 * 2. Since one point is fixed, we can determine the line by slope
 * 3. Slope can be determined by (right.x - left.x) and (right.y - left.y)
 * 4. In every loop, we have a 2-layer hashmap countMap, which store (right.x - left.x) on 1st layer, (right.y - left.y) on 2nd layer
 * 5. The slope need be devide by gcd((right.x - left.x), (right.y - left.y))
 */

import java.util.*;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

public class Solution{
    private int gcd(int x, int y){
        int small = Math.min(x, y);
        int big = Math.max(x, y);
        return (big % small == 0)? small: gcd(small, big % small);
    }
    
    public int maxPoints(Point[] points) {
        int globalMaxCount = 0;
        for(int i = 0; i < points.length; ++i){
            HashMap<Integer, HashMap<Integer,Integer>> countMap = new HashMap<Integer, HashMap<Integer,Integer>>();
            Point source = points[i];
            int duplicateCount = 0;
            int localMaxCount = 1;
            for(int j = i + 1; j < points.length; ++j){
                Point target = points[j];
                if(target.x == source.x && target.y == source.y){
                    duplicateCount++;
                    localMaxCount++;
                    continue;
                }
                
                Point right = (target.x > source.x)? target: source;
                Point left = (target.x > source.x)? source: target;
                int denominator = right.x - left.x;
                int numerator = right.y - left.y;
                if(denominator == 0){
                    numerator = 1;
                }
                else if(numerator == 0){
                    denominator = 1;
                }
                else{
                    int maxfactor = gcd(Math.abs(denominator), Math.abs(numerator));
                    denominator = denominator / maxfactor;
                    numerator = numerator / maxfactor;
                }
                
                countMap.putIfAbsent(denominator, new HashMap<>());
                HashMap<Integer,Integer> innerMap = countMap.get(denominator);
                innerMap.putIfAbsent(numerator, 0);
                innerMap.put(numerator, innerMap.get(numerator) + 1);
                localMaxCount = Math.max(localMaxCount, duplicateCount + innerMap.get(numerator) + 1);
            }
            globalMaxCount = Math.max(globalMaxCount, localMaxCount);
        }
        return globalMaxCount;
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
