/* O(n), This solution has round error flaw
 * 1. Accumulate all the angles
 * 2. If the sum of angles = (n - 2)PI, it is a convex polygon
 */

import java.util.*; // Stack

public class Solution {
    public double angle(int[] vectorA, int[] vectorB){
        double inerProduct, lenA, lenB, angle;
        
        inerProduct = (vectorA[0] * vectorB[0]) + (vectorA[1] * vectorB[1]);
        lenA = Math.sqrt(vectorA[0]*vectorA[0] + vectorA[1]*vectorA[1]);
        lenB = Math.sqrt(vectorB[0]*vectorB[0] + vectorB[1]*vectorB[1]);
        angle = Math.acos(inerProduct/(lenA*lenB));
        
        return (angle != Math.PI)? (angle / Math.PI) : 0;
    }
    
    public boolean isConvex(List<List<Integer>> points) {
        int size, idx, n;
        double refPointy, currPointy;
        List<Integer> currPoint, prevPoint, nextPoint;
        int[] vectorA, vectorB;
        double angleSum, angle;
        
        size = points.size();
        vectorA = new int[2];
        vectorB = new int[2];
        angleSum = 0;
        n = 0;
        
        for(idx = 0; idx < size; idx++){
            prevPoint = points.get(idx);
            currPoint = points.get((idx + 1) % size);
            nextPoint = points.get((idx + 2) % size);
            
            vectorA[0] = currPoint.get(0) - prevPoint.get(0);
            vectorA[1] = currPoint.get(1) - prevPoint.get(1);
            vectorB[0] = currPoint.get(0) - nextPoint.get(0);
            vectorB[1] = currPoint.get(1) - nextPoint.get(1);
            angle = angle(vectorA, vectorB);
            angleSum += angle;
            n = (angle != 0)? ++n: n;
        }
        
        if(Math.abs(n - 2 - angleSum) < 0.000001){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> points;
        
        sol = new Solution();
        points = new ArrayList<List<Integer>>();

        points.add(new ArrayList<Integer>(Arrays.asList(0, 0)));
        points.add(new ArrayList<Integer>(Arrays.asList(0, 10)));
        points.add(new ArrayList<Integer>(Arrays.asList(10, 10)));
        points.add(new ArrayList<Integer>(Arrays.asList(10, 0)));
        points.add(new ArrayList<Integer>(Arrays.asList(5, 5)));
        
        for(List<Integer> point: points){
            System.out.print("(" + point.get(0) + ", " + point.get(1) + "), ");
        }
        System.out.println("");

        System.out.println("isConvex: " + sol.isConvex(points));
    }
}
