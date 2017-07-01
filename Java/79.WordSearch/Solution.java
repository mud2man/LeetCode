/* O(n*m*k), where k is the length of word
 * 1. Traverse every element in the given board first
 * 2. If the the element is equal to the fisrt element in the word
 * 3. Use depth-first traversal to check if any pattern matched
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    public boolean dfs(boolean[][] used, char[][] board, int y, int x, String word, int idx){
        
        if(idx == word.length() - 1 && word.charAt(idx) == board[y][x]){
            return true;
        }
        
        if(board[y][x] != word.charAt(idx)){
            return false;
        }
        
        //push
        used[y][x] = true;
        
        // walk up
        if(y > 0 && used[y - 1][x] == false && dfs(used, board, y - 1, x, word, idx + 1)){
            return true;
        }
        
        // walk down
        if(y < board.length - 1 && used[y + 1][x] == false && dfs(used, board, y + 1, x, word, idx + 1)){
            return true;
        }
        
        // walk left
        if(x > 0 && used[y][x - 1] == false && dfs(used, board, y, x - 1, word, idx + 1)){
            return true;
        }
        
        // walk right
        if(x <  board[0].length - 1 && used[y][x + 1] == false && dfs(used, board, y, x + 1, word, idx + 1)){
            return true;
        }
        
        //pop
        used[y][x] = false;;
        
        return false;
    }
    
    public boolean exist(char[][] board, String word) {
        int y, x;
        boolean[][] used = new boolean[board.length][board[0].length];
        boolean isExist;
       
        for(y = 0; y < board.length; ++y){
            for(x = 0; x < board[0].length; ++x){
                if(dfs(used, board, y, x, word, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Solution sol;
        String word = "ABCCED"; 
        char[][] board = {{'A', 'B', 'C', 'E'},
                          {'S', 'F', 'C', 'S'},
                          {'A', 'D', 'E', 'E'}};
        
        sol = new Solution();

        System.out.println("word: " + word);
        System.out.println("board: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("result: " + sol.exist(board, word));
    }
}
