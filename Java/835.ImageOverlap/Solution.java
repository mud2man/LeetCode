/* Time:O(n^4), Space:O(n^4), LeetCode has better solution
 * 1. Have a utility method "getOverlap", which can produce overlap count given offset
 * 2. Pick the maximum overlap while trying all combinations
 */

import java.util.*;


public class Solution{
    private int getOverlap(int[][] A, int[][] B, int shiftY, int shiftX){
        int yStartA = (shiftY >= 0)? 0: -shiftY;
        int xStartA = (shiftX >= 0)? 0: -shiftX;
        int yStartB = (shiftY >= 0)? shiftY: 0;
        int xStartB = (shiftX >= 0)? shiftX: 0;
        int depth = A.length;
        int width = A[0].length;
        
        int sum = 0;
        for(int y = 0; y < depth - Math.abs(shiftY); ++y){
            for(int x = 0; x < width - Math.abs(shiftX); ++x){
                sum += A[yStartA + y][xStartA + x] * B[yStartB + y][xStartB + x];
            }
        }
        return sum;
    }
    
    public int largestOverlap(int[][] A, int[][] B) {
        int dpeth = A.length;
        int width = A[0].length;
        
        int max = getOverlap(A, B, 0, 0);
        for(int y = 0; y < dpeth; ++y){
            for(int x = 0; x < width; ++x){
                max = Math.max(max, getOverlap(A, B, y, x));
                max = Math.max(max, getOverlap(A, B, -y, x));
                max = Math.max(max, getOverlap(A, B, y, -x));
                max = Math.max(max, getOverlap(A, B, -y, -x));
            }
        }
        return max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] A = {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        int[][] B = {{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};

        System.out.println("A: ");
        for(int[] row: A){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("B: ");
        for(int[] row: B){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("maximum overlap: " + sol.largestOverlap(A, B));
    }
}
