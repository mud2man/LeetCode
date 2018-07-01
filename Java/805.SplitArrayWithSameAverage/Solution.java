/* Backtrack, Math: Time:O(2^n/2), Space:O(2^n/2).
 * 1. If return true, "left" and "right" has the same avearge "a", leftSum = a*l, rightSum = a*r => totalSum = a*(l + r)
 * 2. Therefore, leftSum = 0, and rightSum = 0, after all number are subtracted with average "a"
 * 3. The problem can be reformulated to find a subset which the sum is 0 give all number is subtraceted with average 'a'
 * 4. To reduce time complexity, we can divide A as left and right. Then use helper to find all their combination of sum
 * 5. If we can find a sum 'x' from left, and '-x' from right. And both set are not empty. Then return true
 */         

import java.util.*;
import java.awt.Point;

public class Solution {
    private void getSubSum(Point[] A, int idx, Point sum, int count, Map<Point, Set<Integer>> map){
        if(idx == A.length){
            map.putIfAbsent(sum, new HashSet<Integer>());
            map.get(sum).add(count);
            return;
        }
        getSubSum(A, idx + 1, op(sum, A[idx], true), count + 1, map);
        getSubSum(A, idx + 1, sum, count, map);
    }
    
    private Point op(Point a, Point b, boolean add){
        int numerator = (add)? a.x*b.y + a.y*b.x : a.x*b.y - a.y*b.x;
        int denominator = a.y*b.y;
        if(numerator == 0){
            return new Point(0, 1);
        }
        else{
            return new Point(numerator / gcd(numerator, denominator), denominator / gcd(numerator, denominator));
        }
    }
    
    private int gcd(int x, int y){
        x = Math.abs(x);
        y = Math.abs(y);
        int big = (x >= y)? x: y;
        int small = (x >= y)? y: x;
        if(big % small == 0){
            return small;
        }
        return gcd(big % small, small);
    }
    
    public boolean splitArraySameAverage(int[] A) {
        if(A.length == 1){
            return false;
        }
        
        int sum = 0;
        for(int a: A){
            sum += a;
        }
        
        Point avg = (sum == 0)? new Point(0, 1) : new Point(sum / gcd(sum, A.length), A.length / gcd(sum, A.length));
        Point[] left = new Point[A.length / 2];
        Point[] right = new Point[A.length - left.length];
        for(int i = 0; i < left.length; ++i){
            left[i] = op(avg, new Point(A[i], 1), false);
        }
        for(int i = 0; i < right.length; ++i){
            right[i] = op(avg, new Point(A[i + left.length], 1), false);
        }
        
        Map<Point, Set<Integer>> leftMap = new HashMap<Point, Set<Integer>>();
        getSubSum(left, 0, new Point(0, 1), 0, leftMap);
        Map<Point, Set<Integer>> rightMap = new HashMap<Point, Set<Integer>>();
        getSubSum(right, 0, new Point(0, 1), 0, rightMap);
        
        for(Map.Entry<Point, Set<Integer>> leftEntry: leftMap.entrySet()){
            Point leftSum = leftEntry.getKey();
            Point rightSum = op(new Point(0, 1), leftSum, false);
            if(rightMap.containsKey(rightSum)){
                Set<Integer> leftSet = leftEntry.getValue();
                Set<Integer> rightSet = rightMap.get(rightSum);
                if(leftSet.size() == 1 && leftSet.contains(0) && 
                   rightSet.size() == 1 && rightSet.contains(0)){
                    continue;
                }
                else if(leftSet.size() == 1 && leftSet.contains(left.length) && 
                        rightSet.size() == 1 && rightSet.contains(right.length)){
                    continue;
                }
                else{
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Solution sol= new Solution();
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8}; 
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("can split : " + sol.splitArraySameAverage(A));
    }
}
