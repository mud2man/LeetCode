/* Binary search: O(nlogn)
 * 1. Binary search the number m between matrix[0][0] and matrix[depth - 1][width - 1], s.t. (the number of integer which is less than or equal to m) = k
 * 2. Get maximun from the elements which is smaller or equal to m, and it is Kth element
 */

import java.util.*;

public class Solution{
    private int getLessEqualCount(int[][] matrix, int num){
        int count = 0;
        for(int x = 0, y = matrix.length - 1; x < matrix[0].length; ++x){
            while(y > 0 && matrix[y][x] > num){
                --y;
            }
            count +=(matrix[y][x] <= num)? (y + 1): 0;
        }
        return count;
    }
    
    private int getMaxFromLessEqual(int[][] matrix, int num){
        int boundry = num;
        int max = Integer.MIN_VALUE;
        for(int x = 0, y = matrix.length - 1; x < matrix[0].length; ++x){
            while(y > 0 && matrix[y][x] > boundry){
                --y;
            }
            max = (matrix[y][x] <= boundry)? Math.max(max, matrix[y][x]): max;
        }
        return max;
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        int depth = matrix.length;
        int width = matrix[0].length;
        int lb = matrix[0][0];
        int hb = matrix[depth - 1][width - 1];
        while(lb <= hb){
            int mid = (int)(((long)lb + (long)hb) / 2l);
            int lessEqualCount = getLessEqualCount(matrix, mid);
            if(lessEqualCount == k){
                lb = mid;
                break;
            }else if(lessEqualCount < k){
                lb = mid + 1;
            }else{
                hb = mid - 1;
            }
        }
        return getMaxFromLessEqual(matrix, lb);
    }
  
    public static void main(String[] args){
        int k = 8;
        int[][] matrix = {{1, 5, 9}, 
                          {10, 11, 13}, 
                          {12, 13, 15}};
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        Solution sol = new Solution();
        int num = sol.kthSmallest(matrix, k);
        System.out.println("");
        System.out.println(k+  "th num: " + num);
    }
}
