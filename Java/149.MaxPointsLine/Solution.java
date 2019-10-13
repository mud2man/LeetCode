/* Hash: Time:O(n^2) Space:O(n^2)
 * 1. Fix the first point by selecting from points[0] to points[length - 1]
 * 2. Since one point is fixed, we can determine the line by slope
 * 3. Slope can be determined by (right.x - left.x) and (right.y - left.y)
 * 4. In every loop, we have a 2-layer hashmap countMap, which store (right.x - left.x) on 1st layer, (right.y - left.y) on 2nd layer
 * 5. The slope need be devide by gcd((right.x - left.x), (right.y - left.y))
 */

import java.util.*;

public class Solution{
    private int gcd(int x, int y){
        int small = Math.min(x, y);
        int big = Math.max(x, y);
        return (big % small == 0)? small: gcd(small, big % small);
    }
    
    public int maxPoints(int[][] points) {
        int globalMaxCount = 0;
        for(int i = 0; i < points.length; ++i){
            HashMap<Integer, HashMap<Integer,Integer>> countMap = new HashMap<>();
            int[] source = points[i];
            int duplicateCount = 1;
            int localMaxCount = 1;
            for(int j = i + 1; j < points.length; ++j){
                int[] target = points[j];
                if(target[1] == source[1] && target[0] == source[0]){
                    duplicateCount++;
                    localMaxCount++;
                    continue;
                }
                int[] left = (source[1] <= target[1])? source: target;
                int[] right = (source[1] <= target[1])? target: source;
                int denominator = right[1] - left[1];
                int numerator = right[0] - left[0];
                if(denominator == 0){
                    numerator = 1;
                }else if(numerator == 0){
                    denominator = 1;
                }else{
                    int maxfactor = gcd(Math.abs(denominator), Math.abs(numerator));
                    denominator = denominator / maxfactor;
                    numerator = numerator / maxfactor;
                }
                countMap.computeIfAbsent(denominator, key -> new HashMap<>()).putIfAbsent(numerator, 0);
                countMap.get(denominator).put(numerator, countMap.get(denominator).get(numerator) + 1);
                localMaxCount = Math.max(localMaxCount, duplicateCount + countMap.get(denominator).get(numerator));
            }
            globalMaxCount = Math.max(globalMaxCount, localMaxCount);
        }
        return globalMaxCount;
    }
 
    public static void main(String[] args){
        int[][] points = {{1, 1},{2, 2}, {3, 3}};
        Solution sol = new Solution();
        System.out.println("points");
        for(int[] point: points){
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }
        System.out.println("maximum number of points on a line: " + sol.maxPoints(points));
    }
}
