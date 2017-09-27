/* Bit manupulation: O(n*m)
 * 1. Shift the current state to 2nd bit, and put the next state to the 1st bit
 * 2. Traverse all elements in the matrix to update next state, and reset the left-top neighbor's 2nd bit
 * 3. Reset the last 2 columns's 2nd bit and the ones at the last row as well
 */

import java.util.*;


public class Solution{
    private boolean isValid(int[] offset, int y, int x, int[][] board){
        y = y + offset[0];
        x = x + offset[1];
        int depth = board.length;
        int width = board[0].length;
        
        if(y >= 0 && y < depth && x >= 0 && x < width){
            return true;
        }
        
        return false;
    }
    
    public void gameOfLife(int[][] board) {
        int depth = board.length;
        int width = (depth == 0)? 0: board[0].length;
        int[][] offset0 = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}};
        int[][] offset1 = {{0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int mask0 = 2;
        int mask1 = 1;
        
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                int liveNeighborCount = 0;
                // count left-top neighbors
                for(int[] offset: offset0){
                    if(isValid(offset, y , x, board)){
                        if((board[y + offset[0]][x + offset[1]] & mask0) > 0){
                            liveNeighborCount++;
                        }
                    }
                }
                
                // count right-bottom neighbors
                for(int[] offset: offset1){
                    if(isValid(offset, y , x, board)){
                        if((board[y + offset[0]][x + offset[1]] & mask1) > 0){
                            liveNeighborCount++;
                        }
                    }
                }
                
                boolean isLive = (board[y][x] == 1)? true: false;
                board[y][x] = board[y][x] << 1;  
                if(isLive){
                    if(liveNeighborCount >= 2 && liveNeighborCount <= 3){
                        board[y][x] = board[y][x] | mask1;   
                    }
                    else{
                        board[y][x] = board[y][x] & ~mask1;
                    }
                }
                else{
                    if(liveNeighborCount == 3){
                        board[y][x] = board[y][x] | mask1; 
                    }
                }
                
                if(y >= 1 && x >= 1){
                    board[y - 1][x - 1] = board[y - 1][x - 1] & ~mask0;
                }
            }
        }
        
        //correction
        int lastX = width - 1;
        if(lastX >= 0){
            for(int y = 0; y < depth; ++y){
                board[y][lastX] = board[y][lastX] & ~mask0;
            }
        }
        
        int last2X = width - 2;
        if(last2X >= 0){
            for(int y = 0; y < depth; ++y){
                board[y][last2X] = board[y][last2X] & ~mask0;
            }
        }
                                           
        int lastY = depth - 1;
        if(lastY >= 0){
            for(int x = 0; x < width - 2; ++x){
                board[lastY][x] = board[lastY][x] & ~mask0;
            }
        }
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
