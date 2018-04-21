/* Two pointers: O(n*m*k), where k is merge# 
 * 1. Scan vertically and horizontally to set the crushable element to negative, and return the "crused"
 * 2. If crushed is true, do merge by tow pointers
 */

import java.util.*;


public class Solution{
        private boolean crush(int[][] board){
        boolean crushed = false;
        int depth = board.length;
        int width = board[0].length;
        
        //horizon
        for(int y = 0; y < depth; ++y){
            int count = 1;
            int previousValue = Math.abs(board[y][0]);
            for(int x = 1; x < width; ++x){
                if(previousValue == Math.abs(board[y][x]) && x < (width - 1)){
                    count++;
                }
                else{
                    int length = (previousValue == Math.abs(board[y][x]))? count + 1: count;
                    int start = (previousValue == Math.abs(board[y][x]))? x: x - 1;
                    if(length > 2 && board[y][start] != 0){
                        for(int i = 0; i < length; ++i){
                            board[y][start - i] = -Math.abs(board[y][start - i]);
                        }
                        crushed = true;
                    }
                    count = 1;
                    previousValue = Math.abs(board[y][x]);
                }
            }
        }
        
        //vertical
        for(int x = 0; x < width; ++x){
            int count = 1;
            int previousValue = Math.abs(board[0][x]);;
            for(int y = 1; y < depth; ++y){
                if(previousValue == Math.abs(board[y][x]) && y < (depth - 1)){
                    count++;
                }
                else{
                    int length = (previousValue == Math.abs(board[y][x]))? count + 1: count;
                    int start = (previousValue == Math.abs(board[y][x]))? y: y - 1;
                    if(length > 2 && board[start][x] != 0){
                        for(int i = 0; i < length; ++i){
                            board[start - i][x] = -Math.abs(board[start - i][x]);
                        }
                        crushed = true;
                    }
                    count = 1;
                    previousValue = Math.abs(board[y][x]);
                }
            }
        }
        return crushed;
    }
    
    private void merge(int[][] board){
        int depth = board.length;
        int width = board[0].length;
        for(int x = 0; x < width; ++x){
            int base = depth;
            for(int y = depth - 1; y >= 0; --y){
                if(board[y][x] > 0){
                    board[--base][x] = board[y][x];
                }
            }
            for(int y = base - 1; y >= 0; --y){
                board[y][x] = 0;
            }
        }
    }
    
    public int[][] candyCrush(int[][] board) {
        while(crush(board)){
            merge(board);
        }
        return board;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] board = {{110,5,112,113,114},
                         {210,211,5,213,214},
                         {310,311,3,313,314},
                         {410,411,412,5,414},
                         {5,1,512,3,3},
                         {610,4,1,613,614},
                         {710,1,2,713,714},
                         {810,1,2,1,1},
                         {1,1,2,2,2},
                         {4,1,4,4,1014}};

        
        System.out.println("board: ");
        for(int[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");

        sol.candyCrush(board);

        System.out.println("crushed: ");
        for(int[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");
    }
}
