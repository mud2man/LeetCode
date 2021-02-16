/* Dynamic programming: Time:O(n * n), Space:O(n * m)
 * 1. sum[y][x] = rectangle area sum with bottom-right corner (y, x)
 * 2. Accumulate maxLength with 1 if the square sum with bottom-right corner (y, x) and (maxLength + 1) side is <= thread
 * 3. maxLength is the max side length as all the squares before (y, x)
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int depth = mat.length;
        int width = mat[0].length;
        int[][] sum = new int[depth][width];
        int maxLength = 0;
        for(int y = 0; y < depth; y++){
            for(int x = 0; x < width; x++){
                int upSum =(y > 0)? sum[y - 1][x]: 0;
                int leftSum =(x > 0)? sum[y][x - 1]: 0;
                int upLeftSum =(y > 0 && x > 0)? sum[y - 1][x - 1]: 0;
                sum[y][x] = upSum + leftSum - upLeftSum + mat[y][x];
                int nextLength = maxLength + 1;
                if(y >= nextLength - 1 && x >= nextLength - 1){
                    upSum =(y >= nextLength)? sum[y - nextLength][x]: 0;
                    leftSum =(x >= nextLength)? sum[y][x - nextLength]: 0;
                    upLeftSum =(y >= nextLength && x >= nextLength)? sum[y - nextLength][x - nextLength]: 0;
                    int squareSum = sum[y][x] - upSum - leftSum + upLeftSum;
                    maxLength =(squareSum <= threshold)? nextLength: maxLength;
                }
            }
        }
        return maxLength;
    }
 
    public static void main(String[] args){
        int[][] nums = {{1, 1, 3, 2, 4, 3, 2}, {1, 1, 3, 2, 4, 3, 2}, {1, 1, 3, 2, 4, 3, 2}};
        int threshold = 4;
        Solution sol = new Solution();
        System.out.println("threshold:"  + threshold);
        System.out.println("nums:" );
        for(int[] row: nums){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("max side length:" + sol.maxSideLength(nums, threshold));
    }
}
