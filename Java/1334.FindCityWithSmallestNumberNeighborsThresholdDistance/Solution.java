/* Floyd Warshall: Time:O(n^3), Space:O(n^2). 
 * 1. Use Floyd Warshall's algorithm to caculate the shortest distance between any pair of cities
 * 2. Collect "rechableNeighbors" from all cities given condition (distance < distanceThreshold)
 * 3. Find the city with minimum rechable neighbors
 */

import java.util.*;


public class Solution{
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[n][n];
        for(int[] edge: edges){
            dp[edge[0]][edge[1]] = edge[2];
            dp[edge[1]][edge[0]] = edge[2];
        }
        
        for(int mid = 0; mid < n; ++mid){ //Floyd Warshall's algorithm
            for(int start = 0; start < n; start++){
                for(int end = 0; end < n; ++end){
                    int prevDis = (dp[start][end] == 0)? Integer.MAX_VALUE: dp[start][end];
                    int newDis = (dp[start][mid] > 0 && dp[mid][end] > 0)? dp[start][mid] + dp[mid][end]: Integer.MAX_VALUE;
                    newDis = Math.min(newDis, prevDis);
                    dp[start][end] = (newDis == Integer.MAX_VALUE)? 0: newDis;
                }
            }
        }
        
        int[] rechableNeighbors = new int[n];
        for(int start = 0; start < n - 1; start++){
            for(int end = start + 1; end < n; ++end){
                if(dp[start][end] <= distanceThreshold){
                    rechableNeighbors[start]++;
                    rechableNeighbors[end]++;
                }
            }
        }
        int min = n + 1;
        int cityWithMinNeighbors = n - 1;
        for(int i = n - 1; i >= 0; --i){
            if(rechableNeighbors[i] < min){
                cityWithMinNeighbors = i;
                min = rechableNeighbors[i];
            }
        }
        return cityWithMinNeighbors;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 4;
        int[][] edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;
        System.out.println("n:" + n);
        System.out.println("distanceThreshold:" + distanceThreshold);
        for(int[] edge: edges){
            System.out.println(Arrays.toString(edge));
        }
        System.out.println("city with minimum number of neighbors:" + sol.findTheCity(n, edges, distanceThreshold));
    }
}
