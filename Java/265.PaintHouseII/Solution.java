/* Dynamic programming + Maximum heap: O(n*k)
 * 1. Construct a 2-D array to represent the minimum cost with color non-x on house y
 * 2. Get the minium cost with color x on house y, and put the cost into maximum heap
 * 3. Get the minium cost with color non-x on house by retrieve the top of the maximum heap
 * 4. Repeat step 2 and stpe 3 until all the houses are visited
 * 
 * ex: costs = {{1, 2, 3}, {2, 1, 4}, {5, 7, 8}}
 * time[0]: dp = {{2, 1, 1}, {}, {}} (non-x color)
 * time[1]: dp = {{2, 1, 1}, {4, 2, 5}, {}} (x color) => dp = {{2, 1, 1}, {2, 4, 2}, {}} (non-x color)
 * time[2]: dp = {{2, 1, 1}, {2, 4, 2}, {7, 11, 10}} (x color) => dp = {{2, 1, 1}, {2, 4, 2}, {10, 7, 7}} (non-x color)\
 * minCost = 9
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
        private class CostPair{
        int color;
        int cost;
        CostPair(int a, int b){color = a; cost = b;}
    }
    
    private class MaxHeapComparator implements Comparator<CostPair>{   
        public int compare( CostPair x, CostPair y ){
            return y.cost - x.cost;
        }
    }
    
    public int minCostII(int[][] costs) {
        if(costs.length == 0){
            return 0;
        }
        
        int [][] dp = new int[costs.length + 1][costs[0].length];
        PriorityQueue<CostPair> maxHeap = new PriorityQueue<CostPair>(2, new MaxHeapComparator());
        for(int y = 0; y < costs.length; ++y){
            // get the minium cost with color x on house y
            for(int x = 0; x < costs[0].length; ++x){
                dp[y + 1][x] = dp[y][x] + costs[y][x];
                if(maxHeap.size() < 2){
                    maxHeap.add(new CostPair(x, dp[y + 1][x]));
                }
                else{
                    if(maxHeap.peek().cost > dp[y + 1][x]){
                        maxHeap.poll();
                        maxHeap.add(new CostPair(x, dp[y + 1][x]));
                    }
                }
            }
            
            // get the minium cost with color non-x on house y
            CostPair secondMinPair = maxHeap.poll();
            CostPair firstMinPair = maxHeap.poll();
            for(int x = 0; x < costs[0].length; ++x){
                if(firstMinPair != null && firstMinPair.color != x){
                    dp[y + 1][x] = firstMinPair.cost;
                }
                else{
                    dp[y + 1][x] = secondMinPair.cost;
                }
            }
        }
        
        int minCost = dp[costs.length][0];
        for(int x = 1; x < costs[0].length; ++x){
            minCost = Math.min(minCost, dp[costs.length][x]);
        }
        return minCost;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int [][] costs = {{1, 2, 3},
                          {2, 1, 4},
                          {5, 7, 8}};

        System.out.println("costs: ");
        for(int[] cost: costs){
            System.out.println(Arrays.toString(cost));
        }
        System.out.println("");
        System.out.println("minCost: " + sol.minCostII(costs));
    }
}
