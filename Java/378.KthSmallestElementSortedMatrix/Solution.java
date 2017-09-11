/* Binary search: O(nlogn)
 * 1. Binary search the number m between matrix[0][0] and matrix[depth - 1][width - 1], s.t. (the number of integer which is less than or equal to m) = k
 * 2. Get maximun from the elements which is smaller or equal to m, and it is Kth element
 */

import java.util.*;

public class Solution{
    private int getLessEquelCount(int[][] matrix, int target){
        int y = matrix.length - 1;
        int count = 0;
        
        for(int x = 0; x < matrix[0].length; ++x){
            while(y >= 0 && matrix[y][x] > target){
                --y;
            }
            
            if(y == -1){
                break;
            }
            count += (y + 1);
        }
        return count;
    }
    
    private int getMaxFromLessEquelNums(int[][] matrix, int target){
        int y = matrix.length - 1;
        int max = Integer.MIN_VALUE;
        
        for(int x = 0; x < matrix[0].length; ++x){
            while(y >= 0 && matrix[y][x] > target){
                --y;
            }
            
            if(y == -1){
                break;
            }
            max = Math.max(max, matrix[y][x]);
        }
        return max;
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        int depth = matrix.length;
        int width = matrix[0].length;
        int lb = matrix[0][0];
        int ub = matrix[depth - 1][width - 1];
        
        while(lb <= ub){
            int mid = (int)(((long)lb + (long)ub) / 2);
            int count = getLessEquelCount(matrix, mid);
            if(count == k){
                lb = mid;
                break;
            }
            else if(count > k){
                ub = mid - 1;
            }
            else{
                lb = mid + 1;
            }
        }
        
        int Kth = getMaxFromLessEquelNums(matrix, lb);
        return Kth;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k;
        int num;

        k = 8;

        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        sol = new Solution();
        num = sol.kthSmallest(matrix, k);

        System.out.println("");
        System.out.println(k+  "th num: " + num);
    }
}
