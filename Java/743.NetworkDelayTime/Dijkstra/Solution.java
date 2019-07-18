/* Djikstra: Time:O((n+e)logn), Space:O(n)
 * 1. Have a map "timeToNodes" to record time to nodes
 * 2. Have a map "adjList" as adjacent list
 * 3. Extarct a node from timeToNodes, and minimum head store times
 * 4. Update minimum heap and timeToNodes, when (minTime + neighbor[1]) < nodeTimes[neighbor[0]]
 */

import java.util.*;

public class Solution{
    public int networkDelayTime(int[][] times, int N, int K) {
        HashMap<Integer, Set<Integer>> timeToNodes = new HashMap<Integer, Set<Integer>>();
        timeToNodes.put(Integer.MAX_VALUE, new HashSet<Integer>());
        int[] nodeTimes = new int[N + 1];
        for(int i = 1; i <= N; ++i){
            nodeTimes[i] = Integer.MAX_VALUE;
            timeToNodes.get(Integer.MAX_VALUE).add(i);
        }
        nodeTimes[K] = 0;
        timeToNodes.get(Integer.MAX_VALUE).remove(K);
        timeToNodes.put(0, new HashSet<Integer>());
        timeToNodes.get(0).add(K);
        
        HashMap<Integer, List<int[]>> adjList = new HashMap<Integer, List<int[]>>();
        for(int[] time: times){
            adjList.putIfAbsent(time[0], new ArrayList<int[]>());
            adjList.get(time[0]).add(new int[]{time[1], time[2]});
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        minHeap.add(0);
        while(!minHeap.isEmpty()){
            int minTime = minHeap.poll();
            Iterator<Integer> iterator = timeToNodes.get(minTime).iterator();
            int node = iterator.next();
            nodeTimes[node] = minTime;
            iterator.remove();
            List<int[]> neighbors = adjList.get(node);
            if(neighbors == null){
                continue;
            }
            for(int[] neighbor: neighbors){
                if((minTime + neighbor[1]) < nodeTimes[neighbor[0]]){
                    timeToNodes.get(nodeTimes[neighbor[0]]).remove(neighbor[0]);
                    minHeap.remove(nodeTimes[neighbor[0]]);
                    nodeTimes[neighbor[0]] = minTime + neighbor[1];
                    timeToNodes.putIfAbsent(nodeTimes[neighbor[0]], new HashSet<Integer>());
                    timeToNodes.get(nodeTimes[neighbor[0]]).add(neighbor[0]);
                    minHeap.add(nodeTimes[neighbor[0]]);
                }
            }
        }
        
        int max = 0;
        for(int i = 1; i <= N; ++i){
            max = Math.max(max, nodeTimes[i]);
        }
        
        return (max == Integer.MAX_VALUE)? -1: max;
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
