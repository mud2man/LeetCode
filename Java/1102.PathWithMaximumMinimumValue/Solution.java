/* Dijkstra: Time:O(nlogn), Space:O(n)
 * 1. Reformulate this problem to Dijkstra's problem
 * 2. mins[y][x] = the max-min-value path starting from (0, 0)
 * 3. We always pick {A[y][x], y ,x} with the highiest A[y][x], and update mins[y][x] with max(A[y][x], neighbors's values)
 * 4. mins[A.length][A[0].length] is the answer
 */         

import java.util.*;

public class Solution {
    public int maximumMinimumPath(int[][] A) {
        int[][] mins = new int[A.length][A[0].length];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> Integer.compare(y[0], x[0]));
        maxHeap.add(new int[]{A[0][0], 0, 0});
        while(!maxHeap.isEmpty() && A[A.length - 1][A[0].length - 1] != -2){
            int[] top = maxHeap.poll();
            if(top[1] == 0 && top[2] == 0){
                mins[0][0] = A[0][0];
                maxHeap.add(new int[]{A[0][1], 0, 1});
                maxHeap.add(new int[]{A[1][0], 1, 0});
                A[0][0] = -2;
            }else{
                int max = -1;
                int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                for(int[] direction: directions){
                    int y = top[1] + direction[0];
                    int x = top[2] + direction[1];
                    if(y >= 0 && y < A.length && x >= 0 && x < A[0].length){
                        if(A[y][x] == -2){
                            max = Math.max(max, mins[y][x]);
                        }else if(A[y][x] >= 0){
                            maxHeap.add(new int[]{A[y][x], y, x}); 
                            A[y][x] = -1;
                        }
                    }
                }
                mins[top[1]][top[2]] = Math.min(top[0], max);
                A[top[1]][top[2]] = -2;
            }
        }
        return mins[A.length - 1][A[0].length - 1];
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        int[][] A = {{5, 4, 5}, {1, 2, 6}, {7, 4, 6}};
        System.out.println("A:");
        for(int[] row: A){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("max min value: " + sol.maximumMinimumPath(A));
    }
}
