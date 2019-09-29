/* Array: TimeO(n*m), Space:O(1)
 * 1. Define the length of 4 edges "lens" of each spiral, and their moving direction "dirs" as well
 * 2. Move pointer startting from "start", and update it by dirs[i]
 * 3. Need to handle edge cases of x-direction(lens[1] == 1) and y-direction(lens[0] == 1) single line
 * 4. Repeat it until "start" is valid
 */

import java.util.*;

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return new ArrayList<>();
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] lens = {matrix[0].length - 1, matrix.length - 1, matrix[0].length - 1, matrix.length - 1};
        int[] start = {0, 0};
        List<Integer> serial = new ArrayList<>();
        while(serial.size() < matrix.length * matrix[0].length){
            int[] pos = {start[0]++, start[1]++};
            if(lens[0] == 0){ //single column
                for(int j = 0; j <= lens[1]; ++j){
                    serial.add(matrix[pos[0]++][pos[1]]);
                }
            }else if(lens[1] == 0){ //single row
                for(int j = 0; j <= lens[0]; ++j){
                    serial.add(matrix[pos[0]][pos[1]++]);
                }
            }else{
                for(int i = 0; i < 4; ++i){ //top, right, bottom, left edges
                    int[] dir = dirs[i];
                    int len = lens[i];
                    for(int j = 0; j < len; ++j){
                        serial.add(matrix[pos[0]][pos[1]]);
                        pos[0] += dir[0];
                        pos[1] += dir[1];
                    }
                    lens[i] -= 2;
                }
            }
        }
        return serial;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("spirals:" + sol.spiralOrder(matrix));
    }
}
