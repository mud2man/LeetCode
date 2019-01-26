/* BFS: Time:O(n^2), Space:O(n^2)
 * 1. Use BFS to reach the reachable nodes, and use set "visited" to avoid repeated visited nodes
 */

import java.util.*; // Stack

public class Solution {
    private int[] pos2yx(int pos, int[][] board){
        int len = board.length;
        int y = (len - 1) - (pos - 1) / len;
        int x = ((len - 1 - y) % 2 == 0) ? (pos - 1) % len: (len - 1) - (pos - 1) % len;
        return new int[]{y, x};
    }
    
    public int snakesAndLadders(int[][] board) {
        int len = board.length;
        Set<Integer> queue = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(1);
        int move = 0;
        while(!queue.isEmpty()){
            Set<Integer> nextQueue = new HashSet<>();
            for(int curr: queue){
                if(curr == len * len){
                    return move;
                }
                visited.add(curr);
                
                // jump to next
                for(int j = 1; j <= 6; ++j){
                    int next = curr + j;
                    if(next > board.length * board.length){
                        break;
                    }
                    int[] nextYx = pos2yx(next, board);
                    
                    if(board[nextYx[0]][nextYx[1]] == -1){
                        if(!visited.contains(next)){
                            nextQueue.add(next);
                        }
                    }
                    else{
                        next = board[nextYx[0]][nextYx[1]];
                        if(!visited.contains(next)){
                            nextQueue.add(next);
                        }
                    }
                }
            }
            move++;
            queue = nextQueue;
        }
        return -1;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] board = {{-1, -1, -1, -1, -1, -1},
                         {-1, -1, -1, -1, -1, -1},
                         {-1, -1, -1, -1, -1,- 1},
                         {-1, 35, -1, -1, 13, -1},
                         {-1, -1, -1, -1, -1, -1},
                         {-1, 15, -1, -1, -1, -1}};
        
        System.out.println("board: ");
        for(int[] row: board){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("least moves: " + sol.snakesAndLadders(board));
    }
}
