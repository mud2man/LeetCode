/* Dijkstra's Algorithm: Time:O(e^2*logn), Space:O(e)
 * 1. Apply Dijkstra's algorithm, but need an 2D array stops to store the visited stops 
 * 2. In conventional Dijkstra, we only put the stop with lower cost than current on into min heap
 * 3. However, we need to consider the one has fewer intermidia stops
 * 4. Therefore, we don't consider the one has higher cost, and higher intermidia stops
 */

import java.util.*;

public class Solution{
    private class Stop{
        int position;
        int price;
        int count;
        Stop(int pst, int prc, int c){position = pst; price = prc; count = c;}
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] stops = new int[n][2];
        for(int i = 0; i < n; ++i){
            Arrays.fill(stops[i], Integer.MAX_VALUE);
        }
        
        K++;
        HashMap<Integer, List<int[]>> edges = new HashMap<Integer, List<int[]>>();
        for(int[] flight: flights){
            edges.computeIfAbsent(flight[0], key -> new ArrayList<int[]>()).add(new int[]{flight[1], flight[2]});
        }
        
        PriorityQueue<Stop> minHeap = new PriorityQueue<Stop>((x, y) -> (x.price - y.price));
        minHeap.add(new Stop(src, 0, 0));
        while(!minHeap.isEmpty()){
            Stop current = minHeap.poll();
            if(current.position == dst){
                return current.price;
            }
            if(current.count == K || !edges.containsKey(current.position)){
                continue;
            }
            
            for(int[] next: edges.get(current.position)){
                int nextStop =  next[0];
                int price = next[1];
                if((current.price + price) < stops[nextStop][0] || (current.count + 1) < stops[nextStop][1]){
                    minHeap.add(new Stop(nextStop, (current.price + price), (current.count + 1)));
                    stops[nextStop][0] = current.price + price;
                    stops[nextStop][1] = current.count + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[][] flights = {{0, 1, 100},{1, 2, 100},{0, 2, 500}};
        int n = 3;
        int src = 0;
        int dst = 2;
        int k = 1;

        Solution sol = new Solution();
        System.out.println("flights: ");
        for(int[] row: flights){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("n: " + n + ", src:" + src + ", dst:" + dst + ", k:" + k);
        System.out.println("cheapest price: " + sol.findCheapestPrice(n, flights, src, dst, k));
    }
}
