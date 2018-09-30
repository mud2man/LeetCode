/* DFS: Time:O(n*m), Space:O(n*m). LeetCode has better solution
 * 1. Calculate the neighborring bombs numbers on the board
 * 2. Use DFS to update 'E' to number or 'B'
 */

import java.util.*;

public class Solution{
    private void calculate(char[][] board, int[][] numBoard){
        int depth = board.length;
        int width = (depth == 0)? 0: board[0].length;
        int[][] shifts ={{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(board[y][x] == 'M' || board[y][x] == 'X'){
                    for(int[] shift: shifts){
                        int nextY = y +  shift[0];
                        int nextX = x +  shift[1];
                        if(nextY >= 0 && nextY < depth && nextX >= 0 && nextX < width){
                            numBoard[nextY][nextX]++;
                        }
                    }
                }
            }
        }
    }
    
    private void dfs(char[][] board, int y, int x, int[][] numBoard){
        int depth = board.length;
        int width = (depth == 0)? 0: board[0].length;
        if(y < 0 || y >= depth || x < 0 || x >= width || board[y][x] != 'E'){
            return;
        }
        
        int[][] shifts ={{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        if(numBoard[y][x] == 0){
            board[y][x] = 'B';
            for(int[] shift: shifts){
                int nextY = y +  shift[0];
                int nextX = x +  shift[1];
                dfs(board, nextY, nextX, numBoard);
            }
        }
        else{
            board[y][x] = (char)(numBoard[y][x] + '0');
        }
    }
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int depth = board.length;
        int width = (depth == 0)? 0: board[0].length;
        int[][] numBoard = new int[depth][width];
        
        int y = click[0];
        int x = click[1];
        if(board[y][x] == 'M'){
            board[y][x] = 'X';
            return board;
        }
        
        calculate(board, numBoard);
        dfs(board, y, x, numBoard);
        return board;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
        int[] click = {3, 0};

        System.out.println("board: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("click: " + Arrays.toString(click));
        board = sol.updateBoard(board, click);
        System.out.println("board: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("click: " + Arrays.toString(click));
    }
}
