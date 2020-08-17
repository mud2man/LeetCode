/* Dynamic programming: Time:O(n * m), Space:O(n * m)
 * 1. Construct rightBottomSums
 * 2. Construct blockSums, where[y][x] = rightBottomSum - rightTopSum - leftBottomSum + leftTopSum;
 */     

import java.util.*; // Stack

public class Solution {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int[][] rightBottomSums = new int[mat.length][mat[0].length];
        for(int y = 0; y < mat.length; ++y){
            for(int x = 0; x < mat[0].length; ++x){
                int topSum =(y > 0)? rightBottomSums[y - 1][x]: 0;
                int leftSum =(x > 0)? rightBottomSums[y][x - 1]: 0;
                int topLeftSum =(y > 0 && x > 0)? rightBottomSums[y - 1][x - 1]: 0;
                rightBottomSums[y][x] = topSum + leftSum + mat[y][x] - topLeftSum;
            }
        }
        
        int[][] blockSums = new int[mat.length][mat[0].length];
        for(int y = 0; y < mat.length; ++y){
            for(int x = 0; x < mat[0].length; ++x){
                int rightBottomY =(y + K < mat.length)? y + K: mat.length - 1;
                int rightBottomX =(x + K < mat[0].length)? x + K: mat[0].length - 1;
                int rightBottomSum = rightBottomSums[rightBottomY][rightBottomX];
                int rightTopY = y - K  - 1;
                int rightTopX = rightBottomX;
                int rightTopSum =(rightTopY >= 0)? rightBottomSums[rightTopY][rightTopX]: 0;
                int leftBottomY = rightBottomY;
                int leftBottomX = x - K - 1;
                int leftBottomSum =(leftBottomX >= 0)? rightBottomSums[leftBottomY][leftBottomX]: 0;
                int leftTopY = y - K  - 1;
                int leftTopX = x - K - 1;
                int leftTopSum =(leftTopY >= 0 && leftTopX >= 0)? rightBottomSums[leftTopY][leftTopX]: 0;
                blockSums[y][x] = rightBottomSum - rightTopSum - leftBottomSum + leftTopSum;
            }
        }
        return blockSums;
    } 
    
    public static void main(String[] args){
        Solution sol = new Solution();
        int K = 1;
        System.out.println("K:" + K);
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("mat:");
        for(int[] row: mat){
            System.out.println(Arrays.toString(row));
        }

        int[][] blockSums = sol.matrixBlockSum(mat, K);
        System.out.println("blockSums:");
        for(int[] row: blockSums){
            System.out.println(Arrays.toString(row));
        }
    }
}
