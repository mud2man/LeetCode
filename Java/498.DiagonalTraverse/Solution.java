/* O(m*n)
 * 1. Traverse until index reach to the end 
 * 2. Travserse direction depends on the direction
 */          

import java.util.*; // Stack

public class Solution {
    private void traverse(int[][] matrix, int[] offset, int[] ptr, int[] order, int[] index){
        while(ptr[0] >= 0 && ptr[0] < matrix.length && ptr[1] >= 0 && ptr[1] < matrix[0].length){
            order[index[0]++] = matrix[ptr[0]][ptr[1]];
            ptr[0] = ptr[0] + offset[0];
            ptr[1] = ptr[1] + offset[1];
        }
        
        if(ptr[0] == -1 && ptr[1] == matrix[0].length){
            ptr[0] = 1;
            ptr[1] = matrix[0].length - 1;
        }
        else if(ptr[0] == matrix.length && ptr[1] == -1){
            ptr[0] = matrix.length - 1;
            ptr[1] = 1;
        }
        else if(ptr[0] < 0){
            ptr[0]++;
        }
        else if(ptr[0] == matrix.length){
            ptr[0]--;
            ptr[1] += 2;
        }
        else if(ptr[1] < 0){
            ptr[1]++;
        }
        else{
            ptr[1]--;
            ptr[0] += 2;
        }
    }
    
    public int[] findDiagonalOrder(int[][] matrix) {
        int depth = matrix.length;
        int width = (depth > 0) ? matrix[0].length: 0;
        int[][] offsets = {{-1, 1}, {1, -1}};
        int direction = 0;
        int[] ptr ={0, 0};
        int[] order = new int[depth * width];
        int[] index = {0};
        
        while(index[0] < depth * width){
            traverse(matrix, offsets[direction % 2], ptr, order, index);
            direction++;
        }
        
        return order;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] diagnol;

        sol = new Solution();
        
        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        diagnol = sol.findDiagonalOrder(matrix);
        System.out.println("diagnol: " + Arrays.toString(diagnol));
    }
}
