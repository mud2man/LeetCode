/* O(m*n)t
 * 1. Only the X has no X on its top and left can be count as a new battleship
 * 2. Traverse every element, and return count
 */

import java.util.*; // Stack

public class Solution {
        public int countBattleships(char[][] board) {
        int count = 0;
        
        if(board.length == 0 || board[0].length == 0){
            return 0;
        } 
        
        for(int y = 0; y < board.length; ++y){
            if(y == 0 && board[0][0] == 'X'){
                count++;
            }
            else if(y > 0 && board[y][0] == 'X' && board[y - 1][0] == '.'){
                count++;
            }
            
            for(int x = 1; x < board[0].length; ++x){
                if(board[y][x] == 'X'){
                    if(y == 0 && board[0][x - 1] == '.'){
                        count++;
                    }
                    else if(y > 0 && board[y][x - 1] == '.' && board[y - 1][x] == '.'){
                        count++;
                    }
                }
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol;
        char board[][] = {{'X', '.', '.', 'X'},
                          {'.', '.', '.', 'X'},
                          {'.', '.', '.', 'X'}};

        sol = new Solution();

        System.out.println("board: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("battleship#: " + sol.countBattleships(board));
	}
}
