/* Array: TimeO(n*m), Space:O(1)
 * 1. Define the length of 4 edges "lens" of each spiral, and their moving direction "dirs" as well
 * 2. Move pointer startting from "start", and update it by dirs[i]
 * 3. Need to handle edge cases of x-direction(lens[1] == 1) and y-direction(lens[0] == 1) single line
 * 4. Repeat it until "start" is valid
 */

import java.util.*;


public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int depth = matrix.length;
        int width = (depth > 0)? matrix[0].length: 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] lens = {width, depth, width, depth};
        int[] start = {0, 0};
        List<Integer> spirals = new ArrayList<>();
        while((start[0] <= (depth - 1) / 2) && (start[1] <= (width - 1) / 2)){
            int y = start[0];
            int x = start[1];
            if(lens[1] == 1){
                for(int i = 0; i < lens[0]; ++i){
                    spirals.add(matrix[y][x + i]);
                }
            }
            else if(lens[0] == 1){
                for(int i = 0; i < lens[1]; ++i){
                    spirals.add(matrix[y + i][x]);
                }
            }
            else{
                for(int i = 0; i < 4; ++i){
                    for(int j = 0; j < lens[i] - 1; ++j){
                        spirals.add(matrix[y][x]);
                        y += dirs[i][0];
                        x += dirs[i][1];
                    }
                    lens[i] -= 2;
                }
            }
            start[0] += 1;
            start[1] += 1;
        }
        return spirals;
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
