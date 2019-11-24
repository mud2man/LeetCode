/* Backtrack: Time:O(9^n), where n is the number of empty cells
 * 1. Have candidate sets for every row, column, and box
 * 2. Have a list of empty cells, and link the associated candidate sets to the node
 * 3. Use "backtrack" method to visit the list
 * 4. During backtrack, select the intersection of the three candidates set, and traverse the intersection
 * 5. Remove the intersect candidate from the three candidates set, and put it back when the recursive backtrack return
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> columns = new HashMap<>();
        Map<Integer, Set<Character>> blocks = new HashMap<>();
        for(int y = 0; y < board.length; ++y){
            for(int x = 0; x < board[0].length; ++x){
                char c = board[y][x];
                if(Character.isDigit(c)){
                    int rowIndex = y;
                    if(rows.containsKey(rowIndex) && rows.get(rowIndex).contains(c)){
                        return false;
                    }
                    rows.computeIfAbsent(rowIndex, key -> new HashSet<>()).add(c);
                    int columnIndex = x;
                    if(columns.containsKey(columnIndex) && columns.get(columnIndex).contains(c)){
                        return false;
                    }
                    columns.computeIfAbsent(columnIndex, key -> new HashSet<>()).add(c);
                    int blockIndex = (y / 3) * 3 + x / 3;
                    if(blocks.containsKey(blockIndex) && blocks.get(blockIndex).contains(c)){
                        return false;
                    }
                    blocks.computeIfAbsent(blockIndex, key -> new HashSet<>()).add(c);
                }
            }
        }
        return true;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                          {'6','.','.','1','9','5','.','.','.'},
                          {'.','9','8','.','.','.','.','6','.'},
                          {'8','.','.','.','6','.','.','.','3'},
                          {'4','.','.','8','.','3','.','.','1'},
                          {'7','.','.','.','2','.','.','.','6'},
                          {'.','6','.','.','.','.','2','8','.'},
                          {'.','.','.','4','1','9','.','.','5'},
                          {'.','.','.','.','8','.','.','7','9'}};
        System.out.println("board: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("is valid: " + sol.isValidSudoku(board));
    }
}
