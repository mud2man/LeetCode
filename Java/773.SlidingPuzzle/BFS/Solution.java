/* BFS: Time:O(6!), Space:O(6!)
 * 1. Transalte  board into integer, and use hashset to prevent duplicated visit
 * 2. Have state change helper "move", and revert helper "getBoard"
 */

import java.util.*;

public class Solution{
    private int hashCode(int[][] board){
        int base = 1;
        int code = 0;
        for(int y = 0; y < board.length; ++y){
            for(int x = 0; x < board[0].length; ++x){
                code += base * board[y][x];
                base *= 10;
            }
        } 
        return code;
    }
    
    private void move(int[][] board, int[] pos, int[] dir){
        board[pos[0]][pos[1]] = board[pos[0] + dir[0]][pos[1] + dir[1]];
        board[pos[0] + dir[0]][pos[1] + dir[1]] = 0;
    }
    
    private void getBoard(int[][] board, int code, int[] pos){
        for(int y = 0; y < 2; ++y){
            for(int x = 0; x < 3; ++x){
                board[y][x] = code % 10;
                code = code / 10;
                if(board[y][x] == 0){
                    pos[0] = y;
                    pos[1] = x;
                }
            }
        }
    }
    
    public int slidingPuzzle(int[][] board) {
        HashSet<Integer> visited = new HashSet<Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(hashCode(board));
        
        int dis = 0;
        int[] pos = new int[2];
        int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int code = queue.pollFirst();
                if(code == 54321){
                    return dis;
                }
                visited.add(code);
                getBoard(board, code, pos);
                int[] nextPos = new int[2];
                for(int[] dir: dirs){
                    nextPos[0] = pos[0] + dir[0];
                    nextPos[1] = pos[1] + dir[1];
                    if(nextPos[0] >= 0 && nextPos[0] < 2 && nextPos[1] >=0 && nextPos[1] < 3){
                        move(board, pos, dir);
                        int nextCode = hashCode(board);
                        if(!visited.contains(nextCode)){
                            queue.add(nextCode);
                        }
                        move(board, nextPos, new int[]{-dir[0], -dir[1]});
                    }
                }
            }
            dis++;
        }
        return -1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] board = {{4, 1, 2}, {5, 0, 3}};
        
        System.out.println("board: ");
        for(int[] row: board){
            System.out.println("row: " + Arrays.toString(row));
        }
        System.out.println("least step: " + sol.slidingPuzzle(board));
    }
}
