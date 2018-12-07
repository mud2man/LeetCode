/* DFS + BFS: Time:O(n * m), Space:O(n * m)
 * 1. Find the first island, and use dfs to recolor the first island from 1 to 2, and push all positions to queue
 * 2. Use BFS to grow the first island until it touch the first island '1', then return distance
 */

import java.util.*;

public class Solution{
    private void dfs(int[] start, int[][] A, Deque<int[]> queue){
        int y = start[0];
        int x = start[1];
        if(y < 0 || y >= A.length || x < 0 || x >= A[0].length || A[y][x] == 0 || A[y][x] == 2){
            return;
        }
        A[y][x] = 2;
        queue.add(new int[]{y, x});
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] dir: dirs){
            int[] nextStart = new int[]{y + dir[0], x + dir[1]};
            dfs(nextStart, A, queue);
        }
    }
    
    public int shortestBridge(int[][] A) {
        int[] start = new int[2];
        Deque<int[]> queue = new LinkedList<>();
        boolean hit = false;
        for(int y = 0; y < A.length; ++y){
            for(int x = 0; x < A[0].length; ++x){
                if(A[y][x] == 1 && !hit){
                    start = new int[]{y, x};
                    dfs(start, A, queue);
                    hit = true;
                }
            }
        }
        
        int dis = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int[] pos = queue.pollFirst();
                for(int[] dir: dirs){
                    int y = pos[0] + dir[0];
                    int x = pos[1] + dir[1];
                    if(y < 0 || y >= A.length || x < 0 || x >= A[0].length || A[y][x] == 2){
                        continue;
                    }
                    else if(A[y][x] == 1){
                        return dis;
                    }
                    else{
                        A[y][x] = 2;
                        queue.add(new int[]{y, x});
                    }
                }
            }
            dis++;
        }
        return 0;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] A = {{0, 1}, {1, 0}};
        for(int[] row: A){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("shortest bridge: " + sol.shortestBridge(A));
    }
}
