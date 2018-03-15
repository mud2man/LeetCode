/* Maximum heap + Greedy: Time:O(nlogn), Space:O(n). 
 * LeetCode has O(mlogn) binary search solution, where m = stations[n - 1] - stations[0]
 * 1. If there is a new station we can add, we must select the region which has the maximum distance.
 * 2. Because if we don't, we can decrease the maximum distance
 * 3. First, we assign the stations with the propotion (nodes[i].range * K / totalRange))
 * 4. And we assign the left over stations to the range with the maximum distance
 */
import java.util.*;

public class Solution {
    private class HeapNode{
        int range;
        int stations;
        double distance;
        HeapNode(int r, int s, double d){range = r; stations = s; distance = d;}
    }
    
    private class DistanceComparator implements Comparator<HeapNode>{
        public int compare(HeapNode x, HeapNode y){
            return (y.distance < x.distance)? -1: 1; 
        }
    }
    
    public double minmaxGasDist(int[] stations, int K) {
        HeapNode[] nodes = new HeapNode[stations.length - 1];
        int totalRange = 0;
        for(int i = 0; i < (stations.length - 1); ++i){
            int range = stations[i + 1] - stations[i];
            nodes[i] = new HeapNode(range, 0, 0.0);
            totalRange += range;
        }
        
        // assign the statins according its weight nodes[i].range
        int remain = K;
        for(int i = 0; i < nodes.length; ++i){
            nodes[i].stations = (int)((long)nodes[i].range * (long)K / (long)totalRange);
            nodes[i].distance = (double)(nodes[i].range) / (double)(nodes[i].stations + 1); 
            remain -= nodes[i].stations;
        }
        
        PriorityQueue<HeapNode> maxHeap = new PriorityQueue<HeapNode>(new DistanceComparator());
        for(HeapNode node: nodes){
            maxHeap.add(node);
        }
        
        // assign the left over stations to the range with the maximum distance
        while(remain > 0){
            HeapNode top = maxHeap.poll();
            top.stations++;
            top.distance = (double)(top.range) / (double)(top.stations + 1);
            maxHeap.add(top);
            remain--;
        }
        
        return maxHeap.peek().distance;
    }
 
    public static void main(String[] args){
        int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int K = 9;
        Solution sol;
        
        sol = new Solution();
        System.out.println("stations: " + Arrays.toString(stations));
        System.out.println("K: " + K);
        System.out.println("maximum distance: " + sol.minmaxGasDist(stations, K));
    }

}
