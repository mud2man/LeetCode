/* TreeSet: Time:O(n*m^2*logn), Space:O(n*m^2)
 * 1. Redefine matrix, s.t., martrix[y][x] = sum(matrix[y][0] + matrix[y][1] + ... + matrix[y][x])
 * 2. Fix xStart and xEnd, and put all the upper rectangle "upRectangle" to the tree set upRectangles
 * 3. Find the leastGreater value of (upRectangle - k), and update max by max(max, upRectangle - leastGreater)
 * 
 * ex: matrix = {{1, 0, 1},   => {{1, 1, 2}, 
 *               {0 ,2, 3}}       {0 ,2, 5}}
 */

import java.util.*;

public class Solution{
    public int maxSumSubmatrix(int[][] matrix, int k) {
        for(int y = 0; y < matrix.length; ++y){
            for(int x = 1; x < matrix[0].length; ++x){
                matrix[y][x] = matrix[y][x] + matrix[y][x - 1];
            }
        }
    
        int max = Integer.MIN_VALUE;
        for(int xStart = 0; xStart < matrix[0].length; ++xStart){
            for(int xEnd = xStart; xEnd < matrix[0].length; ++xEnd){
                TreeSet<Integer> upRectangles = new TreeSet<>();
                int upRectangle = 0;
                for(int y = 0; y < matrix.length; ++y){
                    int leftRowSum = (xStart > 0)? matrix[y][xStart - 1]: 0;
                    int rowSum = matrix[y][xEnd];
                    upRectangle += (rowSum - leftRowSum);
                    if(upRectangle <= k){
                       max = Math.max(max, upRectangle); 
                    }
                    int target = upRectangle - k;
                    Integer leastGreater = upRectangles.ceiling(target);
                    if(leastGreater != null){
                        max = Math.max(max, upRectangle - leastGreater);
                    }
                    upRectangles.add(upRectangle);
                }
            }
        }
        return max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] matrix = {{1, 0, 1},{0, -2, 3}};
        int k = 2;
        System.out.println("k: " + k);
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("max sum: " + sol.maxSumSubmatrix(matrix, k));
    }
}
