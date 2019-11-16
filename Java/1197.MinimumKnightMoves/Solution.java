/* BFS: Time:O(n), Spaceo(n), where n = x * y. LeetCode has O(1) mathmetic solution
 * 1. The move is symmetric, so the minimum move to (-4, -2) is equal to (4, 2). So we can change y to |y|, and x to |x|
 * 2. We can limit the positions in the rectangle of  (-2, -2) and (y + 2, x + 2)
 * 3. It's because  we can always find a symmetric position in the rectangle to reach the destination (y, x) using the symetric moves
 */

import java.util.*; // Stack

public class Solution {
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Set<String> visited = new HashSet<>();
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int[][] shifts = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        int move = 0;
        visited.add("0.0");
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int[] pos = queue.pollFirst();
                if(pos[0] == y && pos[1] == x){
                    return move;
                }
                for(int[] shift: shifts){
                    int[] next = new int[]{pos[0] + shift[0], pos[1] + shift[1]};
                    String hash = Integer.toString(next[0]) + "." + Integer.toString(next[1]);
                    if(!visited.contains(hash) && next[0] >= -2 && next[1] >= -2 && next[0] <= y + 2 && next[1] <= x + 2){
                        queue.add(next);
                        visited.add(hash);
                    }
                }
            }
            move++;
        }
        return -1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int y = 5;
        int x = 5;
        System.out.println("y:" + y + ", x:" + x);
        System.out.println("minimum moves:" + sol.minKnightMoves(x, y));
    }
}
