/* Sliding window: Time:O(n*m^2), Space:O(n*m).
 * 1. Construct 2-d array "sum[y][x]" with sum of numbers in the rectangle with bottom-right corner (y, x)
 * 2. Fix the up and bottom row, and move rigtht from left to right while keeping reme,bering rangeSum2Count
 * 3. Get the needCutOffSum by rangeSum - target, and accumulate count with rangeSum2Count.get(needCutOffSum)
 */

import java.util.*; // Stack


public class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int[][] sum = new int[matrix.length][matrix[0].length];
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                int upSum =(y > 0)? sum[y - 1][x]: 0;
                int leftSum =(x > 0)? sum[y][x - 1]: 0;
                int upLeftSum =(y > 0 && x > 0)? sum[y - 1][x - 1]: 0;
                sum[y][x] = upSum + leftSum - upLeftSum + matrix[y][x];
            }
        }
        
        int count = 0;
        for(int low = 0; low < matrix.length; low++){
            for(int up = 0; up <= low; up++){
                Map<Integer, Integer> rangeSum2Count = new HashMap<>();
                rangeSum2Count.put(0, 1);
                for(int right = 0; right < matrix[0].length; right++){
                    int rangeSum =(up == 0)? sum[low][right]: sum[low][right] - sum[up - 1][right];
                    int needCutOffSum = rangeSum - target;
                    count += rangeSum2Count.getOrDefault(needCutOffSum, 0);
                    rangeSum2Count.put(rangeSum, rangeSum2Count.getOrDefault(rangeSum, 0) + 1);
                }
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int target = 0;
        int[][] matrix = {{0, 1, 0}, {1, 1, 1}};
        System.out.println("target:" + target);
        System.out.println("matrix:");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("count:" + sol.numSubmatrixSumTarget(matrix, target));
    }
}
