/* Time:O(m*n), Space:O:(1)
 * 1. Traverse until index reach to the end 
 * 2. Travserse direction depends on the direction
 */          

import java.util.*; // Stack

public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int depth = matrix.length;
        int width = (depth > 0)? matrix[0].length: 0;
        int[] path = new int[depth * width];
        int idx = 0;
        int[][] offset = {{-1, 1}, {1, -1}};
        int[] position = {0, 0};
        while(idx < path.length){
            while(position[0] >= 0 && position[1] < width){
                path[idx++] = matrix[position[0]][position[1]];
                position[0] += offset[0][0];
                position[1] += offset[0][1];
            }
            if(position[1] == width){
                position[0] += 2;
                position[1] = width - 1;
            }else{
                position[0] = 0;
            }

            while(position[0] < depth && position[1] >= 0){
                path[idx++] = matrix[position[0]][position[1]];
                position[0] += offset[1][0];
                position[1] += offset[1][1];
            }
            if(position[0] == depth){
                position[0] = depth - 1;
                position[1] += 2;
            }else{
                position[1] = 0;
            }
        }
        return path;
    }
 
    public static void main(String[] args){
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution sol = new Solution();
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        int[] diagnol = sol.findDiagonalOrder(matrix);
        System.out.println("diagnol: " + Arrays.toString(diagnol));
    }
}
