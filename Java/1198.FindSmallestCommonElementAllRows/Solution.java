/* Binary Search: Time:O(n*m*logn), Space:O(1)
 * 1. Pick up candidate from first row, and binary serach the rest of row given the candidate
 */

import java.util.*;

public class Solution{
    public int smallestCommonElement(int[][] mat) {
        int[] firstRow = mat[0];
        for(int candidate: firstRow){
            boolean fail = false;
            for(int y = 1; y < mat.length; ++y){
                int idx = Arrays.binarySearch(mat[y], candidate);
                if(idx < 0){
                    fail = true;
                    break;
                }
            }
            
            if(!fail){
                return candidate;
            }
        }
        return -1;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] mat = {{1, 2, 3, 4, 5}, {2, 4, 5, 8, 10}, {3, 5, 7, 9, 11}, {1, 3, 5, 7, 9}};
        for(int[] row: mat) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("smallest common element:" + sol.smallestCommonElement(mat));
    }
}
