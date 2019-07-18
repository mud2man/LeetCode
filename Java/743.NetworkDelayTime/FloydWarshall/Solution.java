/* Floyd-Warshall: Time:O((n^3), Space:O(n^2)
 * 1. Have a map "edge" to record all edges
 * 2. Use dynamic programming to caculate all shortest paths among all pairs
 * 3. Get the longest path among (K, i), where 1 <= i <= N
 */

import java.util.*;

public class Solution{
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> edge = new HashMap<>();
        for(int[] time: times){
            edge.putIfAbsent(time[0], new HashMap<>());
            edge.get(time[0]).put(time[1], time[2]);
        }
        
        int[][] dp = new int[N + 1][N + 1];
        for(int[] row: dp){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        for(int i = 1; i <= N; ++i){
            for(int y = 1; y <= N; ++y){
                for(int x = 1; x <= N; ++x){
                    if(y == x){
                        dp[y][x] = 0;
                    }else{
                        int yi = (y == i)? 0: dp[y][i];
                        yi = (edge.containsKey(y) && edge.get(y).containsKey(i))? Math.min(yi, edge.get(y).get(i)): yi;
                        int ix = (i == x)? 0: dp[i][x];
                        ix = (edge.containsKey(i) && edge.get(i).containsKey(x))? Math.min(ix, edge.get(i).get(x)): ix;
                        if(yi != Integer.MAX_VALUE && ix != Integer.MAX_VALUE){
                            dp[y][x] = Math.min(dp[y][x], yi + ix);
                        }
                    }
                }
            }
        }

        int time = -1;
        for(int i = 1; i <= N; ++i){
            time = Math.max(time, dp[K][i]);
        }
        return (time == Integer.MAX_VALUE)? -1: time;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;
        
        System.out.println("N: " + N);
        System.out.println("K: " + K);
        System.out.println("times: ");
        for(int[] time: times){
            System.out.println(Arrays.toString(time));
        }
        System.out.println("delay time: " + sol.networkDelayTime(times, N, K));
    }
}
