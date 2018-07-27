/* Math: Time:O(n^2), Space:O(n)
 * 1. The moves of rows is independent of the moves of columns
 * 2. From here, we only considers rows becuase all the operations are the same to columns. And they are independent
 * 3. Reduces rows to sets, and call getMinMoves to get the minimum moves
 * 4. Swap is only valid if rows.size() = 2, and the second row must be 1's complement of the first row
 * 5. Discuss cases based on if the length of row is 2's multiple, and the count of ones must be row.length()/2, row.length()/2 + 1
 * 6. Get the minimum swap bwtween "010101.." and "101010..."
 */

import java.util.*;

public class Solution{
    private int getMinMoves(Set<String> rows){
        if(rows.size() != 2){
            return -1;
        }
        
        int oneCount = 0;
        List<String> list = new ArrayList<>(rows);
        for(int i = 0; i < list.get(0).length(); ++i){
            char c = list.get(0).charAt(i);
            if(c == '1'){
                if(list.get(1).charAt(i) == '1'){
                    return -1;
                }
                oneCount++; 
            }
            else{
                if(list.get(1).charAt(i) == '0'){
                    return -1;
                }
            }
        }
        
        String row = list.get(0);
        if(row.length() % 2 == 0){
            if(oneCount != row.length() / 2){
                return -1;
            }
            
            String zeroOne = "";
            String oneZero = "";
            for(int i = 0; i < row.length() / 2; ++i){
                zeroOne += "01";
                oneZero += "10";
            }
            
            int[] diff = {0, 0};
            for(int i = 0; i < row.length(); ++i){
                diff[0] += (row.charAt(i) != zeroOne.charAt(i))? 1: 0;
                diff[1] += (row.charAt(i) != oneZero.charAt(i))? 1: 0;
            }
            return Math.min(diff[0], diff[1]) / 2;
        }
        else{
            if(oneCount != row.length() / 2 && oneCount != (row.length() / 2 + 1)){
                return -1;
            }
            String pattern = (oneCount == row.length() / 2)? "01": "10";
            String zeroOne = "";
            for(int i = 0; i < row.length() / 2; ++i){
                zeroOne += pattern;
            }
            zeroOne += (oneCount == row.length() / 2)? "0": "1";
            int diff = 0;
            for(int i = 0; i < row.length(); ++i){
                diff += (row.charAt(i) != zeroOne.charAt(i))? 1: 0;
            }
            return diff / 2;
        }
    }
    
    public int movesToChessboard(int[][] board) {
        int depth = board.length;
        int width = board[0].length;
        int moves = 0;
        
        Set<String> rows = new HashSet<>();
        for(int i = 0; i < depth; ++i){
            StringBuilder row = new StringBuilder();
            for(int j = 0; j < width; ++j){
                row.append(board[i][j]);
            }
            rows.add(row.toString());
        }
        
        int colMoves = getMinMoves(rows);
        if(colMoves == -1){
            return -1;
        }
        else{
            moves += colMoves;
        }
        
        Set<String> cols = new HashSet<>();
        for(int i = 0; i < width; ++i){
            StringBuilder col = new StringBuilder();
            for(int j = 0; j < depth; ++j){
                col.append(board[j][i]);
            }
            cols.add(col.toString());
        }
        
        int rowMoves = getMinMoves(cols);
        if(rowMoves == -1){
            return -1;
        }
        else{
            moves += rowMoves;
        }
        
        return moves;
    }

    public static void main(String[] args){
        int[][] board = {{0, 1, 1, 0},{0, 1, 1, 0},{1, 0, 0, 1}, {1, 0, 0, 1}};
        Solution sol = new Solution();
        
        System.out.println("board:");
        for(int[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("minimum moves: " + sol.movesToChessboard(board));
    }
}
