/* BFS: Time:O(2^9), Space:O(2^9)
 * 1. Have a hash function to translate matrix to hash value
 * 2. Use bfs to expend the next steps until front's hash is 0 or the queue is empty
 */

import java.util.*;

public class Solution{
    private int hash(int[][] mat){
        int base = 1;
        int hashValue = 0;
        for(int y = 0; y < mat.length; ++y){
            for(int x = 0; x < mat[0].length; ++x){
                hashValue += mat[y][x] * base;
                base *= 2;
            }
        }
        return hashValue;
    }
    
    private void flip(int y, int x, int[][] mat){
        int[][] shifts = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for(int[] shift: shifts){
            int nextY = y + shift[0];
            int nextX = x + shift[1];
            if(nextY >= 0 && nextY < mat.length && nextX >= 0 && nextX < mat[0].length){
                mat[nextY][nextX] =(mat[nextY][nextX] == 0)? 1: 0;
            }
        }
    }
    
    private void hash2Mat(int hash, int[][] mat){
        for(int y = 0; y < mat.length; ++y){
            for(int x = 0; x < mat[0].length; ++x){
                mat[y][x] = hash % 2;
                hash = hash / 2;
            }
        }
    }
    
    public int minFlips(int[][] mat) {
        Set<Integer> seen = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.add(hash(mat));
        int dis = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int front = queue.pollFirst();
                if(front == 0){
                    return dis;
                }
                hash2Mat(front, mat);
                for(int y = 0; y < mat.length; ++y){
                    for(int x = 0; x < mat[0].length; ++x){
                        flip(y, x, mat);
                        int hash = hash(mat);
                        if(!seen.contains(hash)){
                            queue.add(hash);
                            seen.add(hash);
                        }
                        flip(y, x, mat);
                    }
                }
            }
            dis++;
        }
        return -1;
    }
 
    public static void main(String[] args){
        int[][] mat = {{0, 0}, {0, 1}};
        Solution sol = new Solution();
        System.out.println("mat:");
        for(int[] row: mat){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("minimum flips#:" + sol.minFlips(mat));
    }
}
