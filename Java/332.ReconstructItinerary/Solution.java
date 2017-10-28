/* DFS: Time:O(path#) Space:O(ticket#)
 * 1. Have an adjacency list adjList for every airport
 * 2. Sort the sucessors in the ajjList
 * 3. Use DFS to traverse
 * 4. If path found, just return the path, because the sucesors are sorted. The found path is the least lexical order
 */

import java.util.*;

public class Solution{
    private boolean dfs(String start, HashMap<String, LinkedList<String>> adjList, LinkedList<String> path, int remain){
        if(remain == 0){
            return true;
        }

        if(!adjList.containsKey(start)){
            return false;
        }
        
        LinkedList<String> neighbors = adjList.get(start);
        for(int i = 0; i < neighbors.size(); ++i){
            //push
            String next = neighbors.pollFirst();
            path.add(next);
            
            if(dfs(next, adjList, path, remain - 1)){
                return true;
            }
            
            //pop
            neighbors.add(next);
            path.pollLast();
        }
        return false;
    }
    
    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, LinkedList<String>> adjList = new HashMap<String, LinkedList<String>>();
        int ticketNum = tickets.length;
        LinkedList<String> path = new LinkedList<String>();
        path.add("JFK");
        
        for(String[] ticket: tickets){
            String from = ticket[0];
            String to = ticket[1];
            if(!adjList.containsKey(from)){
                adjList.put(from, new LinkedList<String>());
            }
            adjList.get(from).add(to);
        }
        
        for(Map.Entry<String, LinkedList<String>> entry: adjList.entrySet()){
            LinkedList<String> neighbors = entry.getValue();
            Collections.sort(neighbors);
        }
        
        dfs("JFK", adjList, path, ticketNum);
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
