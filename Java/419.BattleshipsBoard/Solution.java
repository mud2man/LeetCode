/* O(m*n)t
 * 1. Only the X has no X on its top and left can be count as a new battleship
 * 2. Traverse every element, and return count
 */

import java.util.*; // Stack

public class Solution {
    public int countBattleships(char[][] board) {
        int count = 0;
        for(int y = 0; y < board.length; ++y){
            for(int x = 0; x < board[0].length; ++x){
                if(board[y][x] == 'X'){
                    boolean isXLeft = (x == 0)? false: board[y][x - 1] == 'X';
                    boolean isXUp = (y == 0)? false: board[y - 1][x] == 'X';
                    count += (!isXLeft && !isXUp)? 1: 0;
                }
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        char board[][] = {{'X', '.', '.', 'X'},
                          {'.', '.', '.', 'X'},
                          {'.', '.', '.', 'X'}};
        Solution sol = new Solution();

        System.out.println("board: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("battleship#: " + sol.countBattleships(board));
	}
}
