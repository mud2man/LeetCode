/* Eulerian Path + DFS: Time:O(nlogn), Space:O(n)
 * 1. Have an adjacency list adjList for every airport, and store its successors into a minHeap
 * 2. Always take the smallest sucessor first
 * 3. If the path stucks, put it into the tail
 * 4. Otherwise, it success
 * 5. Reference: https://en.wikipedia.org/wiki/Eulerian_path
 */

import java.util.*;

public class Solution{
    private void dfs(String start, HashMap<String, PriorityQueue<String>> adjList, LinkedList<String> path){
        while(adjList.containsKey(start) && !adjList.get(start).isEmpty()){
            dfs(adjList.get(start).poll(), adjList, path);
        }
        path.addFirst(start);
    }

    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> adjList = new HashMap<String, PriorityQueue<String>>();
        LinkedList<String> path = new LinkedList<String>();
        
        for(String[] ticket: tickets){
            String from = ticket[0];
            String to = ticket[1];
            if(!adjList.containsKey(from)){
                adjList.put(from, new PriorityQueue<String>());
            }
            adjList.get(from).add(to);
        }

        dfs("JFK", adjList, path);
        return path;
    }

    public static void main(String[] args){
        String[][] tickets = {{"JFK","SFO"}, {"JFK","ATL"}, {"SFO","ATL"}, {"ATL","JFK"}, {"ATL","SFO"}};
        Solution sol = new Solution();
        
        System.out.println("tickets:");
        for(String[] ticket: tickets){
            System.out.println(Arrays.toString(ticket));
        }
        
        System.out.println("itinerary:" + sol.findItinerary(tickets));
    }
}
