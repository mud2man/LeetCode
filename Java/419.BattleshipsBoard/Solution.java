/* Hashset
 * 1. Record the positions of battle ships in the previous row
 * 2. If there is a new position "x" not contained in the previous, it's a new battleship
 */

import java.util.*; // Stack

public class Solution {
    public int countBattleships(char[][] board) {
        int x, y, count;
        HashSet<Integer> preRow, nextRow;
        
        preRow = new HashSet<Integer>();
        count = 0;
        
        for(y = 0; y < board.length; ++y){
            nextRow = new HashSet<Integer>();
            for(x = 0; x < board[0].length; ++x){
                if(board[y][x] == 'X'){
                    if((x == 0 || board[y][x - 1] == '.') && (!preRow.contains(x)) ){
                        count++;
                    }
                    nextRow.add(x);
                }
            }
            preRow = nextRow;
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
