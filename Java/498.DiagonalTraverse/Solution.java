/* O(m*n)
 * 1. Have a boolean variable directionUp to determine going up or going down
 * 2. Do traverse as long as (y + x) <= (depth + width - 2)
 * 3. In the end of going up, turn left if not reaching the top-right corner, otherwise turn down
 * 4. In the end of going down, turn down if not reaching the bottom-left corner, otherwise turn right
 */          

import java.util.*; // Stack

public class Solution {
    private boolean isValid(int y, int x, int depth, int width){
        if(y < 0 || y >= depth){
            return false;
        }
        
        if(x < 0 || x >= width){
            return false;
        }
        
        return true;
    }
    
    public int[] findDiagonalOrder(int[][] matrix) {
        int depth = matrix.length;
        int width = (depth > 0)? matrix[0].length: 0;
        int[] sequence = new int[depth * width];
        int y = 0;
        int x = 0;
        
        boolean directionUp = true;
        int idx = 0;
        while((y + x) <= (depth + width - 2)){
            if(directionUp == true){
                while(isValid(y, x, depth, width)){
                    sequence[idx++] = matrix[y][x];
                    y--;
                    x++;
                }
                ++y;
                --x;
                if(y == 0 && x < (width - 1)){
                    ++x;
                }
                else{
                    ++y;
                }
            }
            else{
                while(isValid(y, x, depth, width)){
                    sequence[idx++] = matrix[y][x];
                    y++;
                    x--;
                }
                --y;
                ++x;
                if(x == 0 && y < (depth - 1)){
                    ++y;
                }
                else{
                    ++x;
                }
            }
            directionUp = !directionUp;
        }
        return sequence;
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
