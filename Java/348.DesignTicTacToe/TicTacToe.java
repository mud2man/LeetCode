/* Array: Time:O(1), Space:O(n)
 * 1. Have columns, rows to record the points of every column and row given for player#1 and player#2
 * 2. Have diagnal and anitDiagnal to record the diagnal direction
 * 3. Return (player + 1) if (rows[player][row] == n || columns[player][col] == n || dignal[player] == n || antiDiagnal[player] == n)
 * 4. Otherwise, return 0
 */          

import java.util.*; // Stack

public class TicTacToe{
    int[][] rows;
    int[][] columns;
    int[] dignal;
    int[] antiDiagnal;
    int n;
    
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.rows = new int[2][n];
        this.columns = new int[2][n];
        this.dignal = new int[2];
        this.antiDiagnal = new int[2];
        this.n = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        player--;
        rows[player][row]++;
        columns[player][col]++;
        dignal[player] += (row == col)? 1: 0;
        antiDiagnal[player] += ((row + col) == (n - 1))? 1: 0;
        if(rows[player][row] == n || columns[player][col] == n || dignal[player] == n || antiDiagnal[player] == n){
            return player + 1;
        }
        return 0;
    }

    public static void main(String[] args){
        TicTacToe tic = new TicTacToe(3);
        int row = 0; 
        int col = 0;
        int player = 1;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 0; 
        col = 2;
        player = 2;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 2; 
        col = 2;
        player = 1;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 1; 
        col = 1;
        player = 2;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 2; 
        col = 0;
        player = 1;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 1; 
        col = 0;
        player = 2;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 2; 
        col = 1;
        player = 1;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
    }
}
