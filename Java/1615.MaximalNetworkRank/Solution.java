/* Heap + Map: Time:O(e), Space:O(e), where e is roads.length
 * 1. Collect the adjacentList, maxRank, and secondMaxRank
 * 2. If there are more than one cities with maxRank, go through all pais of them to see if there is any pair without edge (maxRank * 2(o), maxRank * 2 - 1(x)) 
 * 3. Or, go through all cities with secondMaxRank to see if any city no edge with cityWithMaxRank (maxRank + secondMaxRank(o), maxRank + secondMaxRank - 1(x))  
 */     

import java.util.*; // Stack

public class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        if(roads.length == 0){
            return 0;
        }
        Map<Integer, Set<Integer>> adjacentList = new HashMap<>();
        int[] ranks = new int[n];
        for(int[] road: roads){
            adjacentList.computeIfAbsent(road[0], key -> new HashSet<>()).add(road[1]);
            adjacentList.computeIfAbsent(road[1], key -> new HashSet<>()).add(road[0]);
            ranks[road[0]]++;
            ranks[road[1]]++;
        }
        
        Map<Integer, List<Integer>> rank2Cities = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int city = 0; city < n; ++city){
            rank2Cities.computeIfAbsent(ranks[city], key -> new ArrayList<>()).add(city);
            if(minHeap.isEmpty() || minHeap.size() < 2){
                minHeap.add(ranks[city]);
            }else if(minHeap.peek() < ranks[city]){
                minHeap.poll();
                minHeap.add(ranks[city]);
            }
        }
        
        int secondMaxRank = minHeap.poll();
        int maxRank = minHeap.isEmpty()? secondMaxRank: minHeap.poll();
        List<Integer> citiesWithMaxRank = rank2Cities.get(maxRank);
        if(citiesWithMaxRank.size() >= 2){
            for(int i = 0; i < citiesWithMaxRank.size() - 1; ++i){
                for(int j = i + 1; j < citiesWithMaxRank.size(); ++j){
                    if(!adjacentList.get(citiesWithMaxRank.get(i)).contains(citiesWithMaxRank.get(j))){
                        return maxRank * 2;
                    }
                }
            }
            return maxRank * 2 - 1;
        }else{
            List<Integer> citiesWithSecondMaxRank = rank2Cities.get(secondMaxRank);
            int cityWithMaxRank = rank2Cities.get(maxRank).get(0);
            for(int i = 0; i < citiesWithSecondMaxRank.size(); ++i){
                if(!adjacentList.get(citiesWithSecondMaxRank.get(i)).contains(cityWithMaxRank)){
                        return maxRank + secondMaxRank;
                }
            }
            return maxRank + secondMaxRank - 1;
        }
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] roads = {{0, 1}, {0, 3}, {1, 2}, {1, 3}};
        int n = 4;
        System.out.println("n:" + n);
        System.out.print("roads:");
        for(int[] road: roads){
            System.out.print(Arrays.toString(road) + ", ");
        }
        System.out.println();
        System.out.println("maximum rank:" + sol.maximalNetworkRank(n, roads));
    }
}
