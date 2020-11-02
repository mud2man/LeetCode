/* Sort: Time:O(n^2logn), Space:O(n), where n is max(mat.length, mat[0].length)
 * 1. Retrieve each diagnoals
 * 2. Sort them and reassign them to mat
 */     

import java.util.*; // Stack

public class Solution {
    public int[][] diagonalSort(int[][] mat) {
        for(int y = mat.length - 1; y >= 0; --y){
            int length = Math.min(mat.length - y,  mat[0].length); 
            List<Integer> diagnoal = new ArrayList<>();
            for(int x = 0; x < length; ++x){
                diagnoal.add(mat[y + x][x]);
            }
            Collections.sort(diagnoal);
            for(int x = 0; x < length; ++x){
                mat[y + x][x] = diagnoal.get(x);
            }
        }
        
        for(int x = 1; x < mat[0].length; ++x){
            int length = Math.min(mat[0].length - x, mat.length);
            List<Integer> diagnoal = new ArrayList<>();
            for(int y = 0; y < length; ++y){
                diagnoal.add(mat[y][x + y]);
            }
            Collections.sort(diagnoal);
            for(int y = 0; y < length; ++y){
                mat[y][x + y] = diagnoal.get(y);
            }
        }
        return mat;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] mat = {{3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}};
        System.out.println("before:");
        for(int[] row: mat){
            System.out.println(Arrays.toString(row));
        }
        mat = sol.diagonalSort(mat);
        System.out.println("after:");
        for(int[] row: mat){
            System.out.println(Arrays.toString(row));
        }
    }
}
