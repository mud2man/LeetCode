/* Dijkstra: Time:O(maxTime * log(n * maxTime)), Space:O(n * maxTime).
 * 1. Have "city2HitTimeAndCost" to record the latest hit time of the city with value is the cost
 * 2. We ignore the city if the hit time is larger than the previous hit time, since the cost is restrictly increasing w.r.t. the view of city
 * 3. Use Dijkstra to find the shortest path and ignore the city if hitTime > maxTime 
 */

import java.util.*; // Stack


public class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length - 1;
        Map<Integer, List<int[]>> adjacentList = new HashMap<>();
        for(int[] edge: edges){
            adjacentList.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
            adjacentList.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(new int[]{edge[0], edge[2]});
        }
        Map<Integer, int[]> city2HitTimeAndCost = new HashMap<>();
        city2HitTimeAndCost.put(0, new int[]{0, passingFees[0]});
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((x, y) -> (x[0] - y[0]));
        minHeap.add(new int[]{passingFees[0], 0, 0}); // {cost, hitTime, curr}
        while(!minHeap.isEmpty()){
            int[] top = minHeap.poll();
            int curr = top[2];
            int hitTime = top[1];
            int cost = top[0];
            if(curr == n){
                return cost;
            }
            for(int[] nexts: adjacentList.get(curr)){
                int nextHitTime = hitTime + nexts[1];
                int next = nexts[0];
                int nextCost = cost + passingFees[next];
                if(nextHitTime > maxTime){
                    continue;
                }
                int[] hitTimeAndCost = city2HitTimeAndCost.get(next);
                if(hitTimeAndCost == null || nextHitTime < hitTimeAndCost[0]){
                    city2HitTimeAndCost.put(next, new int[]{nextHitTime, nextCost});
                    minHeap.add(new int[]{nextCost, nextHitTime, next});
                }
            }
        }
        return -1;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int maxTime = 30;
        int[][] edges = {{0, 1, 10}, {1, 2, 10}, {2, 5, 10}, {0, 3, 1}, {3, 4, 10}, {4, 5, 15}};
        int[] passingFees = {5, 1, 2, 20, 20, 3};
        System.out.println("maxTime:" + maxTime);
        System.out.println("edges:");
        for(int[] edge: edges){
            System.out.print(Arrays.toString(edge) + ",");
        }
        System.out.println("\npassingFees:" + Arrays.toString(passingFees));
        System.out.println("min cost:" + sol.minCost(maxTime, edges, passingFees));
    }
}
