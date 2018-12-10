/* TreeSet: Time:O(n*m^2*logn), Space:O(n*m^2)
 * 1. 
 */

import java.util.*;

public class Solution{
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int depth = matrix.length;
        int width = matrix[0].length;
        Integer golbalMax = null;
        TreeSet<Integer>[][] sums = new TreeSet[width][width];
        for(int x = 0; x < width; ++x){
            for(int y = 0; y < depth; ++y){
                int upSum = (y > 0)? matrix[y - 1][x]: 0;
                int leftSum = (x > 0)? matrix[y][x - 1]: 0;
                int upLeftSum = (y > 0 && x > 0)? matrix[y - 1][x - 1]: 0;
                matrix[y][x] = matrix[y][x] + upSum + leftSum - upLeftSum;
                int end = x;
                for(int start = 0; start <= end; ++start){
                    if(sums[start][end] == null){
                        sums[start][end] = new TreeSet<>();
                    }
                    int leftArea = (start > 0)? matrix[y][start - 1]: 0;
                    int rightArea = matrix[y][x] - leftArea;
                    //consider the case of retangle [[0, start], [y, end]]
                    if(rightArea <= k){
                        golbalMax = (golbalMax == null)? rightArea: Math.max(golbalMax, rightArea);
                    }
                    int target = rightArea - k;
                    Integer leastGrater = sums[start][end].ceiling(target);
                    if(leastGrater != null){
                        golbalMax = (golbalMax == null)? rightArea - leastGrater: Math.max(golbalMax, rightArea - leastGrater);
                    }
                    sums[start][end].add(rightArea);
                }
            }
        }
        return (golbalMax == null)? 0: golbalMax;
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
