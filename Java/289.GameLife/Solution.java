/* Bit manupulation: O(n*m)
 * 1. Put the next state to the 2nd bit
 * 2. Traverse all elements in the matrix to update next state, and update the left-top neighbor
 * 3. Update the top neighbor if x == (width - 1), and the left neighbor if y == (depth - 1)
 * 4. Update the last one, where y == (depth - 1) and x == (width - 1)
 */

import java.util.*;


public class Solution{
    private boolean willLive(int y, int x, int[][] board){
        int[][] shifts = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int count = 0;
        for(int i = 0; i < shifts.length; ++i){
            int shiftY = y + shifts[i][0];
            int shiftX = x + shifts[i][1];
            if(shiftY >= 0 && shiftY < board.length && shiftX >= 0 && shiftX < board[0].length){
                count += ((board[shiftY][shiftX] & 0x1) != 0)? 1: 0;
            }
        }
        return (count == 3)? true: (count == 2)? ((board[y][x] & 0x1) != 0): false;
    }
    
    private void update(int y, int x, int[][] board){
        if(y >= 0 && y < board.length && x >= 0 && x < board[0].length){
            board[y][x] = board[y][x] >> 1;
        }
    }
    
    public void gameOfLife(int[][] board) {
        int depth = board.length;
        int width = board[0].length;
        
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(willLive(y, x, board)){
                    board[y][x] |= 0x2;
                }
                
                update(y - 1, x - 1, board);
                if(x == (width - 1)){
                    update(y - 1, x, board);
                }
                if(y == (depth - 1)){
                    update(y, x - 1, board);
                }
            }
        }
        update(depth - 1, width - 1, board);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] board = {{1, 1, 0},
                         {1, 1, 0},
                         {0, 0, 1}};

        
        System.out.println("current board: ");
        for(int[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");

        sol.gameOfLife(board);

        System.out.println("next board: ");
        for(int[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");
    }
}
