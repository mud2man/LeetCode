/* Hash: Time:O(n^2 * logn) Space:O(n^2), LeetCode has a better solution
 * 1. A line can be form by two points (x1, y1), (x2, y2) => y = (y2 - y1)/(x2 - x1)*x + (x2*y1 - x1*y2)/(x2 - x1)
 * 2. So a line can be identified by identified by tuple = {(y2 - y1), (x2 - x1), (x2*y1 - x1*y2), x1, y1} 
 * 3. Have a map countMap to store line-count pair, and elemet is Pair{sum, indexs}
 * 4. indexex is a hashSet to prevent accumulate the duplicated points
 * 5. Traverse from i = 0 to i = (length - 2), and j = i +_1 to (length - 1)
 * 5. Update maxCount in every iteration, and return maxCount in the end
 */

import java.util.*;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

public class Solution{
    private Long[] getLineIdentity(Point startPoint, Point endPoint){
        long x1 = (long)startPoint.x;
        long y1 = (long)startPoint.y;
        long x2 = (long)endPoint.x;
        long y2 = (long)endPoint.y;
        Long[] identity = new Long[5];
        identity[0] = (y2 - y1);
        identity[1] = (x2 - x1);
        identity[2] = (x2*y1 - x1*y2);
        identity[3] = x1;
        identity[4] = y1;
        return identity;
    }
    
    private class Pair{
        int sum;
        HashSet<Integer> indexs;
        Pair(){sum = 0; indexs = new HashSet<Integer>();}
    }
    
    private class PointComparator implements Comparator<Point>{
        @Override
        public int compare(Point x, Point y){
            if(x.x != y.x){
                return x.x - y.x;
            }
            else{
                return x.y - y.y;
            }
        }
    }
    
    private class SlopComparator implements Comparator<Long[]>{
        @Override
        public int compare(Long[] x, Long[] y){
            Long a = x[0]*y[1];
            Long b = x[1]*y[0];
            return a.compareTo(b);
        }
    }
    
    private class ShiftComparator implements Comparator<Long[]>{
        @Override
        public int compare(Long[] x, Long[] y){
            if(x[1] == 0 && y[1] == 0){
                Long c = x[3];
                Long d = y[3];
                return c.compareTo(d);
            }
            Long a = x[1]*y[2];
            Long b = x[2]*y[1];
            return a.compareTo(b);
        }
    }
    
    public int maxPoints(Point[] points) {
        Arrays.sort(points, new PointComparator());
        Map<Integer, Integer> weightMap = new HashMap<Integer, Integer>();
        List<Point> pointList = new ArrayList<Point>();
        for(int i = 0; i < points.length; ++i){
            int listIndex = pointList.size() - 1;
            if(pointList.isEmpty()){
                pointList.add(points[i]);
                weightMap.put(0, 1);
            }
            else if(pointList.get(listIndex).x == points[i].x && pointList.get(listIndex).y == points[i].y){
                weightMap.put(listIndex, weightMap.get(listIndex) + 1);
            }
            else{
                pointList.add(points[i]);
                weightMap.put(listIndex + 1, 1);
            }
        }
        
        if(pointList.size() < 3){
            int count = 0;
            for(Map.Entry<Integer, Integer> entry: weightMap.entrySet()){
                count += entry.getValue();
            }
            return count;
        }
        
        int maxCount = 0;
        Map<Long[], Map<Long[], Pair>> countMap = new TreeMap<Long[], Map<Long[], Pair>>(new SlopComparator());
        for(int i = 0; i < (pointList.size() - 1); ++i){
            for(int j = i + 1; j < pointList.size(); ++j){
                Long[] identity = getLineIdentity(pointList.get(i), pointList.get(j));
                if(!countMap.containsKey(identity)){
                    countMap.put(identity, new TreeMap<Long[], Pair>(new ShiftComparator()));
                }
                
                Map<Long[], Pair> innerMap = countMap.get(identity);
                if(!innerMap.containsKey(identity)){
                    innerMap.put(identity, new Pair());
                }
                
                Pair pair = innerMap.get(identity);
                if(!pair.indexs.contains(i)){
                    pair.sum += weightMap.get(i);
                    pair.indexs.add(i);
                }
                if(!pair.indexs.contains(j)){
                    pair.sum += weightMap.get(j);
                    pair.indexs.add(j);
                }
                maxCount = Math.max(maxCount, pair.sum);
            }
        }
        return maxCount;
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
