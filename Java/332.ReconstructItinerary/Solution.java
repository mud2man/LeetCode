/* Eulerian Path + DFS: Time:O(nlogn), Space:O(n)
 * 1. Have an adjacency list adjList for every airport, and store its successors into a minHeap
 * 2. Always take the smallest sucessor first
 * 3. If the path stucks, put it into the tail
 * 4. Otherwise, it success
 * 5. Reference: https://en.wikipedia.org/wiki/Eulerian_path
 */

import java.util.*;

public class Solution{
    private void dfs(String start, HashMap<String, PriorityQueue<String>> adjList, Deque<String> path){
        while(adjList.containsKey(start) && !adjList.get(start).isEmpty()){
            dfs(adjList.get(start).poll(), adjList, path);
        }
        path.addFirst(start);
    }
    
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> adjList = new HashMap<String, PriorityQueue<String>>();
        Deque<String> path = new LinkedList<String>();
        for(List<String> ticket: tickets){
            String from = ticket.get(0);
            String to = ticket.get(1);
            adjList.putIfAbsent(from, new PriorityQueue<>());
            adjList.get(from).add(to);
        }
        dfs("JFK", adjList, path);
        return new ArrayList<>(path);
    }

    public static void main(String[] args){
        String[][] tickets = {{"JFK","SFO"}, {"JFK","ATL"}, {"SFO","ATL"}, {"ATL","JFK"}, {"ATL","SFO"}};
        List<List<String>> ticketList = new ArrayList<>();
        Solution sol = new Solution();
        System.out.println("tickets:");
        for(String[] ticket: tickets){
            System.out.println(Arrays.toString(ticket));
            ticketList.add(Arrays.asList(ticket));
        }
        System.out.println("itinerary:" + sol.findItinerary(ticketList));
    }
}
