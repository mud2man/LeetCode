/* O(m*n)
 * 1. Classsify the edge cases, and walk accordingly
 * 
 *     0  1 
 *   -----------
 *   | 1  2  3 | 2
 * 7 | 4  5  6 | 3
 * 6 | 7  8  9 | 
 *   -----------
 *        5  4
 */          

import java.util.*; // Stack

public class Solution {
        public int onWhichEdge(int[][] matrix, int y, int x){
        int xSize, ySize;
        
        xSize = matrix[0].length;
        ySize = matrix.length;
        
        if(x == 0 && y == 0){
            return 0;
        }
        else if (x < xSize - 1 && y == 0){
            return 1;
        }
        else if (x == xSize - 1 && y == 0){
            return 2;
        }
        else if(x == xSize - 1 && y == ySize - 1){
            return 4;
        }
        else if(x == xSize - 1 && y > 0 ){
            return 3;
        }
        else if(x == 0 && y == ySize - 1){
            return 6;
        }
        else if(x > 0 && y == ySize - 1){
            return 5;
        }
        else if(x == 0 && y < ySize - 1){
            return 7;
        }
        else{
            return -1;
        }
    }

    public void digonalFill(boolean isIncrease,int[][] matrix, int y, int x, int len, int idx, int[] digonal){
        if(isIncrease == true){
            for(int count = 0; count < len; count++){
                digonal[idx + count] = matrix[y][x];
                y++;
                x--;
            }
        }
        else{
            for(int count = 0; count < len; count++){
                digonal[idx + count] = matrix[y][x];
                x++;
                y--;
            }
        }
    }
    
    public int[] findDiagonalOrder(int[][] matrix) {
        int[] digonal;
        int width, depth, x, y, idx, edgeId, len;
        
        if(matrix.length == 0){
            return new int[0];
        }
        
        width = matrix[0].length;
        depth = matrix.length;
        digonal = new int[width*depth];
        digonal[0] = matrix[0][0];
        x = 1;
        y = 0;
        idx = 1;
        
        if(matrix.length == 1){
            for(;idx < width; idx++){
                digonal[idx] =  matrix[0][idx];
            }
            return digonal;
        }
        
        if(matrix[0].length == 1){
            for(;idx < depth; idx++){
                digonal[idx] =  matrix[idx][0];
            }
            return digonal;
        }
        
        while((edgeId = onWhichEdge(matrix, y, x)) != 4){
            
            switch (edgeId){
                case 1:
                    len = Math.min(x + 1, depth);
                    digonalFill(true, matrix, y, x, len, idx, digonal);
                    y = y + len - 1;
                    x = x - len + 1;
                    if(y < depth - 1){
                        y++;
                    }
                    else{
                        x++;
                    }
                    break;
                case 2:
                    len = Math.min(depth, width);
                    digonalFill(true, matrix, y, x, len, idx, digonal);
                    y = y + len - 1;
                    x = x - len + 1;
                    if(y < depth - 1){
                        y++;
                    }
                    else{
                        x++;
                    }
                    break;
                case 3:
                    len = Math.min(depth - y, width);
                    digonalFill(true, matrix, y, x, len, idx, digonal);
                    y = y + len - 1;
                    x = x - len + 1;
                    if(y < depth - 1){
                        y++;
                    }
                    else{
                        x++;
                    }
                    break;
                case 5:
                    len = Math.min(width - x, depth);
                    digonalFill(false, matrix, y, x, len, idx, digonal);
                    y = y - len + 1;
                    x = x + len - 1;
                    if(x < width - 1){
                        x++;
                    }
                    else{
                        y++;
                    }
                    break;
                case 6:
                    len = Math.min(depth, width);
                    digonalFill(false, matrix, y, x, len, idx, digonal);
                    y = y - len + 1;
                    x = x + len - 1;
                    if(x < width - 1){
                        x++;
                    }
                    else{
                        y++;
                    }
                    break;
                case 7:
                    len = Math.min(y + 1, width);
                    digonalFill(false, matrix, y, x, len, idx, digonal);
                    y = y - len + 1;
                    x = x + len - 1;
                    if(x < width - 1){
                        x++;
                    }
                    else{
                        y++;
                    }
                    break;
                default:
                    len = 0;
                    break;
            }
            idx = idx + len;
        }
        
        if(x < width && y < depth){
            digonal[idx] = matrix[depth - 1][width - 1];
        }
        
        return digonal;
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
