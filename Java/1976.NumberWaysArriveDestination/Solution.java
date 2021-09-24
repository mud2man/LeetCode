/* Topological sort: Time:O(nlogn), Space:O(n)
 * 1. Topological sort city based on the minimum arrival time
 * 2. Vist city in sorted order, and accumulate count if the arrival time of nextCity == minimum arrival tim(ecity2TimeAndCount[nextCity][0])
 * 3. Return city2TimeAndCount[n - 1][1]
 */

import java.util.*; // Stack



public class Solution {
    public int countPaths(int n, int[][] roads) {
        int[][] city2TimeAndCount = new int[n][2];
        for(int i = 0; i < n; i++){
            city2TimeAndCount[i][0] =(i == 0)? 0: Integer.MAX_VALUE;
            city2TimeAndCount[i][1] =(i == 0)? 1: 0;   
        }
        
        Map<Integer, List<int[]>> city2NeighborsAndTime = new HashMap<>();
        for(int[] road: roads){
            city2NeighborsAndTime.computeIfAbsent(road[0], key -> new LinkedList<>())
                .add(new int[]{road[1], road[2]});
            city2NeighborsAndTime.computeIfAbsent(road[1], key -> new LinkedList<>())
                .add(new int[]{road[0], road[2]});
        }
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((x, y) -> (x[1] - y[1])); //{city, minTime}
        minHeap.add(new int[]{0, 0});
        List<Integer> sorted = new LinkedList<>();
        while(!minHeap.isEmpty()){
            int[] cityAndMinTime = minHeap.poll();
            int currCity = cityAndMinTime[0];
            if(city2TimeAndCount[currCity][0] == Integer.MAX_VALUE || currCity == 0){
                int minTime = cityAndMinTime[1];
                city2TimeAndCount[currCity][0] = minTime;
                sorted.add(currCity);
                List<int[]> neighborsAndTime = 
                    city2NeighborsAndTime.getOrDefault(currCity, new LinkedList<>());
                for(int[] neighborAndTime: neighborsAndTime){
                    int nextCity = neighborAndTime[0];
                    int nextTime = minTime + neighborAndTime[1];
                    if(city2TimeAndCount[nextCity][0] == Integer.MAX_VALUE){
                        minHeap.add(new int[]{nextCity, nextTime});
                    }
                }
            }
        }
        
        for(int city: sorted){
            int count = city2TimeAndCount[city][1];
            int time = city2TimeAndCount[city][0];
            List<int[]> neighborsAndTime = city2NeighborsAndTime.getOrDefault(city, new LinkedList<>());
            for(int[] neighborAndTime: neighborsAndTime){
                int nextCity = neighborAndTime[0];
                int nextTime = time + neighborAndTime[1];
                if(city2TimeAndCount[nextCity][0] == nextTime){
                    city2TimeAndCount[nextCity][1] = (city2TimeAndCount[nextCity][1] + count) % 1_000_000_007;
                }
            }
        }
        return city2TimeAndCount[n - 1][1];
    }
 
    public static void main(String[] args){
        int n = 7;
        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};
        Solution sol = new Solution();
        System.out.println("count of paths:" + sol.countPaths(n, roads));
    }
}
