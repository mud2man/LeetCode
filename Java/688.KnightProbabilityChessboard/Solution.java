/* Dynamic Programming Time:O(N^2*K), Space:O(N^2). LeetCode has O(N^6*logK) solution
 * 1. We have two state "curr", and "prev" to record the number of stops after k-th moves and (k-1)-th moves
 * 2. Every move, we consider 8 directions for every position
 * 3. After K state transitions, add all the stops on "curr". Then return (count / base)
 */

import java.util.*;

public class Solution{
    public double knightProbability(int N, int K, int r, int c) {
        double[][] curr = new double[N][N];
        curr[r][c] = 1.0;
        int[][] shifts = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        double base = 1.0;
        
        for(int k = 0; k < K; ++k){
            base = 8.0 * base;
            double[][] prev = curr;
            curr = new double[N][N];
            for(int y = 0; y < N; ++y){
                for(int x = 0; x < N; ++x){
                    if(prev[y][x] == 0.0){
                        continue;
                    }
                    for(int[] shift: shifts){
                        int nextY = y + shift[0];
                        int nextX = x + shift[1];
                        if(nextY >= 0 && nextY < N && nextX >= 0 && nextX < N){
                            curr[nextY][nextX] += prev[y][x];
                        }
                    }
                }
            }
        }
        
        double count = 0.0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                count += curr[y][x];
            }
        }
        
        return count / base;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 3;
        int K = 2;
        int r = 0;
        int c = 0;
        System.out.println("N:" + N + ", K:" + K + ", r:" + r + ", c:" + c);
        System.out.println("probability in board: " + sol.knightProbability(N, K, r, c));
    }
}
