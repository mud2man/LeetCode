/* DFS: Time:O(n*m), Space:O(1)
 * 1. Use "isSurrounded" to determine if the O-region open or closed
 * 2. If open, fill them with 'F'. Otherwise fill them with 'X'
 * 3. Visit the board, and fill the F-region with 'O'
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private boolean isSurrounded(char[][] board, int y, int x){
        if(y < 0 || y >= board.length || x < 0 || x >= board[0].length){
            return false;
        }
        if(board[y][x] == 'X' || board[y][x] == 'V'){
            return true;
        }
        
        board[y][x] = 'V';
        int[][] shifts = {{0, 1}, {0, -1}, {1, 0}, {-1 ,0}};
        for(int[] shift: shifts){
            if(!isSurrounded(board, y + shift[0], x + shift[1])){
                return false;
            }
        }
        return true;
    }
    
    private void fill(char[][] board, int y, int x, char p){
        if(y < 0 || y >= board.length || x < 0 || x >= board[0].length){
            return;
        }
        if(board[y][x] == 'X' || board[y][x] == p){
            return;
        }
        
        board[y][x] = p;
        int[][] shifts = {{0, 1}, {0, -1}, {1, 0}, {-1 ,0}};
        for(int[] shift: shifts){
            fill(board, y + shift[0], x + shift[1], p);
        }
    }
    
    public void solve(char[][] board) {
        int depth = board.length;
        int width = (depth == 0)? 0: board[0].length;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(board[y][x] == 'O'){
                    if(isSurrounded(board, y, x)){
                        fill(board, y, x, 'X');
                    }
                    else{
                        fill(board, y, x, 'F');
                    }
                }
            }
        }
        
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(board[y][x] == 'F'){
                    fill(board, y, x, 'O');
                }
            }
        }
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] board = {{'X', 'X', 'X', 'X'},
                          {'X', 'O', 'O', 'X'},
                          {'X', 'X', 'O', 'X'},
                          {'X', 'O', 'X', 'X'}};
        
        System.out.println("before: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
        sol.solve(board);
        System.out.println("after:");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
    }
}
