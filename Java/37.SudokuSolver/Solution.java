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
    private boolean backtrack(List<int[]> emptyCells, int idx, List<List<Set<Integer>>> candidates, char[][] board){
        if(idx == emptyCells.size()){
            return true;
        }
        
        int y = emptyCells.get(idx)[0];
        int x = emptyCells.get(idx)[1];
        int boxIdx = (y / 3) * 3 + (x / 3);
        for(int i = 1; i < 10; ++i){
            if(candidates.get(0).get(y).contains(i) && 
               candidates.get(1).get(x).contains(i) && 
               candidates.get(2).get(boxIdx).contains(i)){
                candidates.get(0).get(y).remove(i);
                candidates.get(1).get(x).remove(i);
                candidates.get(2).get(boxIdx).remove(i);
                board[y][x] = (char)(i + '0');
                if(backtrack(emptyCells, idx + 1, candidates, board)){
                    return true;
                }
                candidates.get(0).get(y).add(i);
                candidates.get(1).get(x).add(i);
                candidates.get(2).get(boxIdx).add(i);
            }
        }
        return false;
    }
    
    public void solveSudoku(char[][] board) {
        List<List<Set<Integer>>> candidates = new ArrayList<>(); //row, column, box
        Set<Integer> one2Nine = new HashSet<>();
        for(int i = 1; i < 10; ++i){
            one2Nine.add(i);
        }
        
        for(int i = 0; i < 3; ++i){
            candidates.add(new ArrayList<>());
            for(int j = 0; j < 9; ++j){
                candidates.get(i).add(new HashSet<>(one2Nine));
            }  
        }
        
        List<int[]> emptyCells = new ArrayList<>();
        for(int y = 0; y < 9; ++y){
            for(int x = 0; x < 9; ++x){
                if(board[y][x] == '.'){
                    emptyCells.add(new int[]{y, x});
                }else{
                    candidates.get(0).get(y).remove(board[y][x] - '0');
                    candidates.get(1).get(x).remove(board[y][x] - '0');
                    candidates.get(2).get((y / 3) * 3 + (x / 3)).remove(board[y][x] - '0');
                }
            }
        }
        backtrack(emptyCells, 0, candidates, board);
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
 
        System.out.println("before: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
        sol.solveSudoku(board);
        System.out.println("after: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
    }
}
