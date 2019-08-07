/* O(n*m*k), where k is the length of word
 * 1. Traverse every element in the given board first
 * 2. If the the element is equal to the fisrt element in the word
 * 3. Use depth-first traversal to check if any pattern matched
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    public boolean dfs(boolean[][] used, char[][] board, int y, int x, String word, int i){
        if(y < 0 || y >= board.length || x < 0 || x >= board[0].length || used[y][x] == true || board[y][x] != word.charAt(i)){
            return false;
        }
        
        if(i + 1 == word.length()){
            return true;
        }
        
        used[y][x] = true;
        int[][] shifts = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] shift: shifts){
            if(dfs(used, board, y + shift[0], x + shift[1], word, i + 1)){
                return true;
            }  
        }
        used[y][x] = false; 
        return false;
    }
    
    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        boolean isExist;
       
        for(int y = 0; y < board.length; ++y){
            for(int x = 0; x < board[0].length; ++x){
                if(dfs(used, board, y, x, word, 0)){
                    return true;
                }
            }
        }
        return false;
    }
 
    public static void main(String[] args){
        String word = "ABCCED"; 
        char[][] board = {{'A', 'B', 'C', 'E'},
                          {'S', 'F', 'C', 'S'},
                          {'A', 'D', 'E', 'E'}};
        
        Solution sol = new Solution();

        System.out.println("word: " + word);
        System.out.println("board: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("result: " + sol.exist(board, word));
    }
}
