/* Hash: Time:O(n), space:O(n)
 * 1. Construct a map of points "pointsMap", and find the minimum x and maximum x
 * 2. Traverse point p in points to check if the mirrow point p' exist
 */

import java.util.*;

public class Solution{
    public boolean isReflected(int[][] points) {
        if(points.length == 0){
            return true;
        }
        
        HashMap<Integer, Set<Integer>> pointsMap = new HashMap<Integer, Set<Integer>>();
        int minX = points[0][0];
        int maxX = minX;
        for(int[] point: points){
            minX = Math.min(minX, point[0]);
            maxX = Math.max(maxX, point[0]);
            pointsMap.putIfAbsent(point[0], new HashSet<Integer>());
            pointsMap.get(point[0]).add(point[1]);
        }
        
        int sumX = minX + maxX;

        for(int[] point: points){
            int x = sumX - point[0];
            int y = point[1];
            if(!pointsMap.containsKey(x) || !pointsMap.get(x).contains(y)){
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] points = {{1, 1}, {-1, 1}};

        System.out.println("points: ");
        for(int[] point: points){
            System.out.println(Arrays.toString(point));
        }
        
        sol = new Solution();    
        System.out.println("is reflection: " + sol.isReflected(points));
    }
}
